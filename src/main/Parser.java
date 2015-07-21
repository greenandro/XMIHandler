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
import org.xml.sax.helpers.DefaultHandler;
import xmi.metamodel.XMI;
import xmi.metamodel.XMIContent;
import xmi.metamodel.XMIDocumentation;
import xmi.metamodel.XMIHeader;
import xmi.metamodel.XMIMetamodel;
import xmi.metamodel.content.UMLAssociation;
import xmi.metamodel.content.UMLAssociationEnd;
import xmi.metamodel.content.UMLAssociationEndParticipant;
import xmi.metamodel.content.UMLAttribute;
import xmi.metamodel.content.UMLClass;
import xmi.metamodel.content.UMLDataType;
import xmi.metamodel.content.UMLModel;
import xmi.metamodel.content.UMLMultiplicity;
import xmi.metamodel.content.UMLMultiplicityRange;
import xmi.metamodel.content.UMLOperation;
import xmi.metamodel.content.UMLPackage;
import xmi.metamodel.content.UMLParameter;
import xmi.metamodel.content.UMLParameterType;
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
    private String refId;
    private UMLParameter mParameter;
    private UMLAssociation mAssociation;
    private UMLAssociationEnd mAssociationEnd;
    private UMLMultiplicity mMultiplicity;

    private String content = null;
    
    /**
     * Triggered when the start of tag is found.
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws SAXException 
     */
    @Override
    public void startElement(String uri, String localName,
            String qName, Attributes attributes)
            throws SAXException {
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
                mclass.getClassifierFeature().getOperations().add(mOperation);
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
    public void endElement(String uri, String localName,
            String qName) throws SAXException {
        //System.out.println("End " + qName);
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
