/*
 * SAXHandler
 * ruicouto in 22/jul/2015
 */
package main;

import java.util.Stack;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import xmi.metamodel.XMI;
import xmi.metamodel.XMIContent;
import xmi.metamodel.XMIDocumentation;
import xmi.metamodel.XMIHeader;
import xmi.metamodel.XMIMetamodel;
import xmi.metamodel.content.UMLAbstraction;
import xmi.metamodel.content.UMLAssociation;
import xmi.metamodel.content.UMLAssociationEnd;
import xmi.metamodel.content.UMLAssociationEndParticipant;
import xmi.metamodel.content.UMLAttribute;
import xmi.metamodel.content.UMLClass;
import xmi.metamodel.content.UMLDataType;
import xmi.metamodel.content.UMLDependencySupplier;
import xmi.metamodel.content.UMLGeneralizableElementGeneralization;
import xmi.metamodel.content.UMLGeneralization;
import xmi.metamodel.content.UMLGeneralizationChild;
import xmi.metamodel.content.UMLGeneralizationParent;
import xmi.metamodel.content.UMLInterface;
import xmi.metamodel.content.UMLModel;
import xmi.metamodel.content.UMLModelElementClientDependency;
import xmi.metamodel.content.UMLModelElementStereotype;
import xmi.metamodel.content.UMLMultiplicity;
import xmi.metamodel.content.UMLMultiplicityRange;
import xmi.metamodel.content.UMLNamespaceOwnedElement;
import xmi.metamodel.content.UMLOperation;
import xmi.metamodel.content.UMLPackage;
import xmi.metamodel.content.UMLParameter;
import xmi.metamodel.content.UMLParameterType;
import xmi.metamodel.content.UMLStereotype;
import xmi.metamodel.content.UMLStereotypeBaseClass;
import xmi.metamodel.content.UMLStructuralFeatureMulticiply;
import xmi.metamodel.content.UMLStructuralFeatureType;
import xmi.metamodel.interfaces.XMINamespaceOwner;

/**
 *
 * @author ruicouto
 */
class ArgoUMLHandler extends DefaultHandler implements XMIHandler {

    /**
     * The xmi instance
     */
    private XMI xmi;
    
    private UMLModel model;
    private UMLPackage mpackage;
    private UMLClass mclass;
    private UMLAttribute mAttribute;
    private UMLOperation mOperation;
    private UMLParameter mParameter;
    private UMLAssociation mAssociation;
    private UMLAssociationEnd mAssociationEnd;
    private UMLMultiplicity mMultiplicity;
    private UMLGeneralization mGeneralization;
    private UMLAbstraction mAbstraction;
    private UMLInterface mInterface;
    private UMLStereotype mStereotype;
    private UMLNamespaceOwnedElement ownedElement;
    
    private XMINamespaceOwner mNamespaceOwner;
    
    private Stack<String> tags = new Stack<>();
    
    //private String lastTag = "";
    private String content = null;
    private String refId;

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.out.println("ERROR: ");
        System.out.println(e.toString());
    }
    
    /**
     * Triggered when the start of tag is found.
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws SAXException 
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        tags.push(qName);
        //System.out.println("Push " + tags);
        switch (qName) {
            case "XMI":
                xmi = new XMI();
                xmi.setXmlns(attributes.getValue("xmlns:UML"));
                xmi.setVersion(attributes.getValue("xmi.version"));
                xmi.setTimeStamp(attributes.getValue("timestamp"));
                xmi.setContent(new XMIContent());
                break;

            case "XMI.header":
                XMIHeader h = new XMIHeader();
                h.setDocumentation(new XMIDocumentation());
                h.setMetamodel(new XMIMetamodel());
                xmi.setHeader(h);
                break;

            case "XMI.metamodel":
                XMIMetamodel mm = new XMIMetamodel(attributes.getValue("xmi.name"), attributes.getValue("xmi.version"));
                xmi.getHeader().setMetamodel(mm);
                break;

            case "UML:Model":
                model = new UMLModel(attributes.getValue("xmi.id"),
                        attributes.getValue("name"),
                        Boolean.parseBoolean(attributes.getValue("isSpecification")),
                        Boolean.parseBoolean(attributes.getValue("isRoot")),
                        Boolean.parseBoolean(attributes.getValue("isLeaf")),
                        Boolean.parseBoolean(attributes.getValue("isAbstract")));
                xmi.getContent().getModels().add(model);
                mNamespaceOwner = model;
                break;
                
                
            case "UML:Namespace.ownedElement":
                ownedElement = new UMLNamespaceOwnedElement();
                mNamespaceOwner.setNamespaceOwnedElement(ownedElement);
                break;

            case "UML:Package":
                UMLPackage p = new UMLPackage(attributes.getValue("xmi.id"),
                        attributes.getValue("name"),
                        Boolean.parseBoolean(attributes.getValue("isSpecification")),
                        Boolean.parseBoolean(attributes.getValue("isRoot")),
                        Boolean.parseBoolean(attributes.getValue("isLeaf")),
                        Boolean.parseBoolean(attributes.getValue("isAbstract")));
                mNamespaceOwner = p;
                if (mpackage == null) {
                    mpackage = p;
                    ownedElement.getPackages().add(mpackage);
                } else {
                    mpackage.getNamespaceOwnedElement().getPackages().add(p);
                    mpackage = p;
                }
                break;

            case "UML:Class":
                if (attributes.getLength() > 1) {
                    //lastTag = "umlclass";
                    //is class definition
                    mclass = new UMLClass(attributes.getValue("xmi.id"),
                            attributes.getValue("name"),
                            attributes.getValue("visibility"),
                            Boolean.parseBoolean(attributes.getValue("isSpecification")),
                            Boolean.parseBoolean(attributes.getValue("isRoot")),
                            Boolean.parseBoolean(attributes.getValue("isLeaf")),
                            Boolean.parseBoolean(attributes.getValue("isAbstract")),
                            Boolean.parseBoolean(attributes.getValue("active")));
                        ownedElement.getClasses().add(mclass);
                } else {
                    //lastTag = "umlclassref";
                    tags.pop();
                    tags.push("UML:Class:ref");
                    //is reference
                    refId = attributes.getValue("xmi.idref");
                }
                break;
                
            case "UML:Attribute":
                mAttribute = new UMLAttribute(attributes.getValue("xmi.id"),
                        attributes.getValue("name"),
                        attributes.getValue("visibility"),
                        Boolean.parseBoolean(attributes.getValue("isSpecification")),
                        attributes.getValue("ownerScope"),
                        attributes.getValue("changeability"),
                        attributes.getValue("targetScope"));
                mclass.getClassifierFeature().getAttributes().add(mAttribute);
                break;

            case "UML:Operation":
                mOperation = new UMLOperation(attributes.getValue("xmi.id"),
                        attributes.getValue("name"),
                        attributes.getValue("visibility"),
                        Boolean.parseBoolean(attributes.getValue("isSpecification")),
                        attributes.getValue("ownerScope"),
                        Boolean.parseBoolean(attributes.getValue("isQuery")),
                        attributes.getValue("concurrency"),
                        Boolean.parseBoolean(attributes.getValue("isRoot")),
                        Boolean.parseBoolean(attributes.getValue("isLeaf")),
                        Boolean.parseBoolean(attributes.getValue("isAbstract")));
                if(mclass!=null) {                
                    mclass.getClassifierFeature().getOperations().add(mOperation);
                } else if(mInterface!=null) {
                    mInterface.getClassifierFeature().getOperations().add(mOperation);
                }
                break;
                
            case "UML:Multiplicity":
                mMultiplicity = new UMLMultiplicity(attributes.getValue("xmi.id"), null);
                break;

            case "UML:MultiplicityRange":
                UMLMultiplicityRange mr = new UMLMultiplicityRange(attributes.getValue("xmi.id"),
                            attributes.getValue("lower").charAt(0),
                            attributes.getValue("upper").charAt(0));
                if (mAttribute != null) {
                    mMultiplicity.setMultiplicityRange(mr);
                    UMLStructuralFeatureMulticiply f = new UMLStructuralFeatureMulticiply(mMultiplicity);
                    mAttribute.setStructuralFeatureMultiplicity(f);
                } else if(mMultiplicity!=null) {
                    mMultiplicity.setMultiplicityRange(mr);
                }
                break;

            case "UML:Parameter":
                mParameter = new UMLParameter(attributes.getValue("xmi.id"), attributes.getValue("name"),
                        Boolean.parseBoolean(attributes.getValue("isSpecification")), attributes.getValue("kind"));
                mOperation.getBehavioralFeatureParameter().getParameters().add(mParameter);
                break;

            case "UML:DataType":
                UMLDataType dt = new UMLDataType(attributes.getValue("href"));
                if (mAttribute != null) {
                    mAttribute.getStructuralFeatureType().setDataType(dt);
                } else if (mOperation != null) {
                    mParameter.setParameterType(new UMLParameterType(dt));
                }
                break;
                
            case "UML:Association":
                mAssociation = new UMLAssociation(attributes.getValue("xmi.id"), attributes.getValue("name"), 
                        Boolean.parseBoolean(attributes.getValue("isSpecification")), 
                        Boolean.parseBoolean(attributes.getValue("isRoot")), 
                        Boolean.parseBoolean(attributes.getValue("isLeaf")),  
                        Boolean.parseBoolean(attributes.getValue("isAbstract")));
                ownedElement.getAssociations().add(mAssociation);
                break;
                                
            case "UML:AssociationEnd":
                mAssociationEnd = new UMLAssociationEnd(attributes.getValue("xmi.id"), 
                        attributes.getValue("visibility"),  
                        Boolean.parseBoolean(attributes.getValue("isSpecification")), 
                        Boolean.parseBoolean(attributes.getValue("isNavigable")), 
                        attributes.getValue("ordering"), 
                        attributes.getValue("aggregation"), 
                        attributes.getValue("targetScope"), 
                        attributes.getValue("changeability"));
                if(mAssociation.getAssociationConnection().getAssociationEnd1() == null) {
                    mAssociation.getAssociationConnection().setAssociationEnd1(mAssociationEnd);
                } else {
                    mAssociation.getAssociationConnection().setAssociationEnd2(mAssociationEnd);
                }
                break;
                
            case "UML:Generalization":
                if(mclass!=null) {
                    UMLGeneralizableElementGeneralization g = new UMLGeneralizableElementGeneralization();
                    UMLGeneralization gn = new UMLGeneralization(attributes.getValue("xmi.idref"));
                    g.setGeneralizations(gn);
                    mclass.getGeneralizableElementGeneralizations().add(g);
                } else {
                    mGeneralization = new UMLGeneralization(attributes.getValue("xmi.id"), Boolean.parseBoolean("isSpecification"), null, null);
                    mpackage.getNamespaceOwnedElement().getGeneratlizations().add(mGeneralization);
                }
                break;
            
            case "UML:Abstraction":
                if(mclass!=null) {
                    mAbstraction= new UMLAbstraction(attributes.getValue("xmi.idref"));
                    UMLModelElementClientDependency cd = new UMLModelElementClientDependency(mAbstraction);
                    mclass.getModelElementClientDependency().add(cd);
                } else {
                    mAbstraction = new UMLAbstraction(attributes.getValue("xmi.id"), Boolean.parseBoolean(attributes.getValue("isSpecification")));
                    ownedElement.getAbstractions().add(mAbstraction);
                }
                break;
                            
            case "UML:Interface":
                if (attributes.getLength() > 1) {
                    mInterface = new UMLInterface( attributes.getValue("xmi.id"), 
                                attributes.getValue("name"), 
                                attributes.getValue("visibility"), 
                                Boolean.parseBoolean(attributes.getValue("isSpecification")), 
                                Boolean.parseBoolean(attributes.getValue("isRoot")), 
                                Boolean.parseBoolean(attributes.getValue("isLeaf")), 
                                Boolean.parseBoolean(attributes.getValue("isAbstract")));
                    if(mAbstraction==null) {
                        //mpackage.getNamespaceOwnedElement().getInterfaces().add(mInterface);
                        ownedElement.getInterfaces().add(mInterface);
                    }
                    //lastTag = "umlinterface";
                    
                } else {
                    //lastTag = "umlinterfaceref";
                    tags.pop();
                    tags.push("UML:Interface:ref");
                    refId = attributes.getValue("xmi.idref");
                }
                break;
                
                
            case "UML:Stereotype":
                if(attributes.getLength()>1) {
                    //class definition
                    mStereotype = new UMLStereotype(attributes.getValue("xmi.id"), 
                            attributes.getValue("name"), 
                            Boolean.parseBoolean(attributes.getValue("isSpecification")), 
                            Boolean.parseBoolean(attributes.getValue("isRoot")), 
                            Boolean.parseBoolean(attributes.getValue("isLeaf")), 
                            Boolean.parseBoolean(attributes.getValue("isAbstract")), null);
                    //mpackage.getNamespaceOwnedElement().getStereotypes().add(mStereotype);
                    ownedElement.getStereotypes().add(mStereotype);
                } else {
                    //reference
                    refId = attributes.getValue("xmi.idref");
                }
                break;
        }
    }

    /**
     * Triggered when the end of tag is found.
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException 
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //System.out.println(qName);
        //System.out.println("POP  " + tags);
        
        switch (qName) {
            case "XMI.exporter":
                xmi.getHeader().getDocumentation().setExporter(content);
                break;
            case "XMI.exporterVersion":
                xmi.getHeader().getDocumentation().setExporterVersion(content);
                break;

            case "UML:StructuralFeature.type":
                UMLStructuralFeatureType t = new UMLStructuralFeatureType();
                t.setHolder(refId);
                mAttribute.setStructuralFeatureType(t);
                break;

            case "UML:Attribute":
                mAttribute = null;
                break;

            case "UML:Operation":
                mOperation = null;
                break;

            case "UML:Parameter.type":
                mParameter.setParameterType(new UMLParameterType(new UMLClass(refId)));
                break;

            case "UML:Parameter":
                mParameter = null;
                break;
                
                
            case "UML:AssociationEnd.participant":
                if(tags.peek().equals("UML:Class:ref")) {
                    mAssociationEnd.setAssociationEndParticipants(new UMLAssociationEndParticipant(new UMLClass(refId), null));
                } else if(tags.peek().equals("UML:Interface:ref")) {
                    mAssociationEnd.setAssociationEndParticipants(new UMLAssociationEndParticipant(null, new UMLInterface(refId)));
                }
                break;
                
                
            case "UML:Multiplicity":
                mMultiplicity = null;
                break;               
                
            case "UML:Class":
                if( (tags.peek().equals("UML:Abstraction") && tags.get(tags.size()-3).equals("UML:Class")) || 
                        tags.peek().equals("UML:Class") ) {
                    mclass = null;
                }
                break;
                
            case "UML:Interface":
                mInterface = null;
                break;
                
            case "UML:Generalization":
                mGeneralization = null;
                break;
                
            case "UML:Generalization.child": {
                UMLClass c = new UMLClass(refId);
                UMLGeneralizationChild gc = new UMLGeneralizationChild(c);
                mGeneralization.setChild(gc);
            } break;
                
            case "UML:Generalization.parent": {
                UMLClass c = new UMLClass(refId);
                UMLGeneralizationParent gp = new UMLGeneralizationParent(c);
                mGeneralization.setParent(gp);
            } break;
                
            case "UML:Abstraction":
                mAbstraction = null;
                break;
                
                
            case "UML:ModelElement.stereotype":
                UMLStereotype st = new UMLStereotype(refId);
                UMLModelElementStereotype s = new UMLModelElementStereotype(st);
                mAbstraction.setModelElementStereotype(s);
                break;
                
                
                
            case "UML:Dependency.client":
                UMLClass c = new UMLClass(refId);
                mAbstraction.getDependencyClient().setUmlclass(c);
                break;
                
                
            case "UML:Dependency.supplier":
                UMLInterface i = new UMLInterface(refId);
                mAbstraction.setDependencySupplier(new UMLDependencySupplier(i));
                break;
                
                
            case "UML:Stereotype.baseClass":
                mStereotype.setStereotypeBaseClasses(new UMLStereotypeBaseClass(content));
                break;
        }
        
    }

    /**
     * Triggered when characters inside a tag are found
     * @param ch
     * @param start
     * @param length
     * @throws SAXException 
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        content = String.copyValueOf(ch, start, length).trim();
    }

    /**
     * Get the result
     * @return The XMI representation
     */
    @Override
    public XMI getXMI() {
        return xmi;
    }

}
