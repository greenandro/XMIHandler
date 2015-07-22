/*
 * Parser
 * ruicouto in 20/jul/2015
 */
package main;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
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
import xmi.metamodel.content.UMLOperation;
import xmi.metamodel.content.UMLPackage;
import xmi.metamodel.content.UMLParameter;
import xmi.metamodel.content.UMLParameterType;
import xmi.metamodel.content.UMLStereotype;
import xmi.metamodel.content.UMLStereotypeBaseClass;
import xmi.metamodel.content.UMLStructuralFeatureMulticiply;
import xmi.metamodel.content.UMLStructuralFeatureType;

/**
 *
 * @author ruicouto
 */
public class Parser {

    /**
     * This method provides ad-hoc testing
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        SAXParser parser = parserFactor.newSAXParser();
        SAXHandler handler = new SAXHandler();
        //parser.parse(new File("XMI.xmi"), handler);
        parser.parse(new File("testXmi.xmi"), handler);
        //TODO: After parsing, it lacks to resolve references

        //parser.parse(new File("xmiMetamodel.xmi"), handler);
        XMI xmi = handler.getXmi();

        xmi.getContent().getModels().forEach(m -> {
            m.getPackages().forEach(p -> {
                p.getPackages().forEach(pn -> {
                    pn.getPackages().forEach(p1 -> {
                        System.out.println("package: " + p1.getName());
                        
                        ///***/// classes
                        p1.getClasses().forEach(c -> {
                            System.out.println("Class: " + c.getName());                            
                            c.getClassifierFeature().getAttributes().forEach(a -> {
                                UMLMultiplicityRange r = a.getStructuralFeatureMultiplicity().getMultiplicity().getMultiplicityRange();
                                System.out.println("  (" + r.getLower() + "," + r.getUpper() + ")" + a.getName());
                            });
                            System.out.println("  ------");
                            c.getClassifierFeature().getOperations().forEach(a -> {
                                System.out.print("  ");
                                a.getBehavioralFeatureParameter().getParameters().forEach((UMLParameter pa) -> {
                                    if (pa.getKind().equals("return")) {
                                        System.out.print(pa.getName() + " ");
                                    }
                                });
                                System.out.print(a.getName());
                                System.out.print("(");
                                if (a.getBehavioralFeatureParameter() != null) {
                                    a.getBehavioralFeatureParameter().getParameters().forEach((UMLParameter pa) -> {
                                        if (pa.getKind().equals("in")) {
                                            System.out.print(pa.getKind() + " " + pa.getName() + ", ");
                                        }
                                    });
                                }
                                System.out.println(");");
                            });
                            System.out.println("\n<><><><><><><><><>\n");
                        });
                        
                        ///***/// Associations
                        p1.getAssociations().forEach(a -> {
                            System.out.println(a.getName());
                        });
                        
                        System.out.println("--- Generalizations ---");
                        ///***/// Generalizations
                        p1.getGeneratlizations().forEach(g -> {
                            System.out.println(g.getChild().getUmlClass().getIdRef() + " -> " + g.getParent().getUmlClass().getIdRef());
                        });
                        
                        
                        System.out.println("---Intefaces---");
                        ///***/// Interfaces
                        p1.getInterfaces().forEach(i -> {
                            System.out.println(i.getName());
                        });
                        
                        System.out.println("---Abstractions---");
                        ///***/// Abstractions
                        p1.getAbstractions().forEach(a -> {
                            System.out.println(a.getDependencyClient().getUmlclass().getIdRef() + ", " + 
                                    a.getDependencySupplier().get(0).getUmlinterface().getRefId());
                        });
                        
                        System.out.println("---Stereotypes---");
                        ///***/// Abstractions
                        p1.getStereotypes().forEach(a -> {
                            System.out.println(a.getName());
                            System.out.println(a.getStereotypeBaseClasses().getContent());
                        });
                    });
                });
            });
        });
    }
}



/**
 * The Handler for SAX for a XMI file
 * @author ruicouto
 */
class SAXHandler extends DefaultHandler {

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
    
    private String lastTag = "";
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
                break;

            case "UML:Package":
                UMLPackage p = new UMLPackage(attributes.getValue("xmi.id"),
                        attributes.getValue("name"),
                        Boolean.parseBoolean(attributes.getValue("isSpecification")),
                        Boolean.parseBoolean(attributes.getValue("isRoot")),
                        Boolean.parseBoolean(attributes.getValue("isLeaf")),
                        Boolean.parseBoolean(attributes.getValue("isAbstract")));
                if (mpackage == null) {
                    mpackage = p;
                    model.getPackages().add(mpackage);
                } else {
                    mpackage.getPackages().add(p);
                    mpackage = p;
                }
                break;

            case "UML:Class":
                if (attributes.getLength() > 1) {
                    lastTag = "umlclass";
                    //is class definition
                    mclass = new UMLClass(attributes.getValue("xmi.id"),
                            attributes.getValue("name"),
                            attributes.getValue("visibility"),
                            Boolean.parseBoolean(attributes.getValue("isSpecification")),
                            Boolean.parseBoolean(attributes.getValue("isRoot")),
                            Boolean.parseBoolean(attributes.getValue("isLeaf")),
                            Boolean.parseBoolean(attributes.getValue("isAbstract")),
                            Boolean.parseBoolean(attributes.getValue("active")));
                    mpackage.getClasses().add(mclass);
                } else {
                    lastTag = "umlclassref";
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
                mpackage.getAssociations().add(mAssociation);
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
                if(mAssociation.getAssociationConnections().getAssociationEnd1() == null) {
                    mAssociation.getAssociationConnections().setAssociationEnd1(mAssociationEnd);
                } else {
                    mAssociation.getAssociationConnections().setAssociationEnd2(mAssociationEnd);
                }
                break;
                
            case "UML:Generalization":
                if(mclass!=null) {
                    UMLGeneralizableElementGeneralization g = new UMLGeneralizableElementGeneralization();
                    UMLGeneralization gn = new UMLGeneralization(attributes.getValue("xmi.idref"));
                    g.setGeneralizations(gn);
                    mclass.getGeneralizableElementGeneralizations().add(g);
                } else {
                    mGeneralization = new UMLGeneralization(null, null);
                    mpackage.getGeneratlizations().add(mGeneralization);
                }
                break;
            
            case "UML:Abstraction":
                if(mclass!=null) {
                    UMLAbstraction a = new UMLAbstraction(attributes.getValue("xmi.idref"));
                    UMLModelElementClientDependency cd = new UMLModelElementClientDependency(a);
                    mclass.getModelElementClientDependency().add(cd);
                } else {
                    mAbstraction = new UMLAbstraction(attributes.getValue("xmi.id"), Boolean.parseBoolean(attributes.getValue("isSpecification")));
                    mpackage.getAbstractions().add(mAbstraction);
                }
                break;
            
            case "UML:Interface":
                mInterface = new UMLInterface( attributes.getValue("xmi.id"), 
                            attributes.getValue("name"), 
                            attributes.getValue("visibility"), 
                            Boolean.parseBoolean(attributes.getValue("isSpecification")), 
                            Boolean.parseBoolean(attributes.getValue("isRoot")), 
                            Boolean.parseBoolean(attributes.getValue("isLeaf")), 
                            Boolean.parseBoolean(attributes.getValue("isAbstract")));
                if(mAbstraction==null) {
                    mpackage.getInterfaces().add(mInterface);
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
                    mpackage.getStereotypes().add(mStereotype);
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
                mAssociationEnd.setAssociationEndParticipants(new UMLAssociationEndParticipant(new UMLClass(refId), null));
                break;
                
                
            case "UML:Multiplicity":
                mMultiplicity = null;
                break;               
                
            case "UML:Class":
                if(lastTag.equals("umlclass")) {
                    mclass = null;
                } else if(lastTag.equals("umlclassref")) {
                    lastTag = "umlclass";
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
                mAbstraction.getModelElementStereotypes().add(s);
                break;
                
                
                
            case "UML:Dependency.client":
                UMLClass c = new UMLClass(refId);
                mAbstraction.getDependencyClient().setUmlclass(c);
                break;
                
                
            case "UML:Dependency.supplier":
                UMLInterface i = new UMLInterface(refId);
                mAbstraction.getDependencySupplier().add(new UMLDependencySupplier(i));
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
    public XMI getXmi() {
        return xmi;
    }

}
