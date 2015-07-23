/*
 * Parser
 * ruicouto in 20/jul/2015
 */
package main;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import xmi.metamodel.XMI;
import xmi.metamodel.content.UMLMultiplicityRange;
import xmi.metamodel.content.UMLParameter;

/**
 * This class allows to parse a XMI file
 * @author ruicouto
 */
public class Parser {
    
    /**
     * Parse a XMI file.
     * 
     * @param fileName The path of the file
     * @return The XMI instance
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException 
     */
    public static XMI parse(String fileName, DefaultHandler handler) throws IOException, ParserConfigurationException, SAXException{
        return parse(new File(fileName), handler);
    }
    
    /**
     * Parse a XMI file.
     * 
     * @param file The file instance
     * @param handler
     * @return The XMI instance
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException 
     */
    public static XMI parse(File file, DefaultHandler handler) throws IOException, ParserConfigurationException, SAXException{
        SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        SAXParser parser = parserFactor.newSAXParser();
        parser.parse(file, handler);
        //TODO: After parsing, resolve references
        return ((XMIHandler) handler).getXMI();
    }
    
    
    /**
     * This method provides ad-hoc testing
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        XMI xmi = Parser.parse("testXmi.xmi", HandlerFactory.getHandler(HandlerFactory.ARGOUML));
        //XMI xmi = Parser.parse("xmiMetamodel.xmi");
        //XMI xmi = Parser.parse("XMI.xmi");
        System.out.println("--");
        xmi.getContent().getModels().forEach(m -> {
            m.getNamespaceOwnedElement().getPackages().forEach(p -> {
                p.getNamespaceOwnedElement().getPackages().forEach(pn -> {
                    pn.getNamespaceOwnedElement().getPackages().forEach(p1 -> {
                        System.out.println("package: " + p1.getName());
                        
                        ///***/// classes
                        p1.getNamespaceOwnedElement().getClasses().forEach(c -> {
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
                        p1.getNamespaceOwnedElement().getAssociations().forEach(a -> {
                            System.out.println(a.getName());
                        });
                        
                        System.out.println("--- Generalizations ---");
                        ///***/// Generalizations
                        p1.getNamespaceOwnedElement().getGeneratlizations().forEach(g -> {
                            System.out.println(g.getChild().getUmlClass().getRefId() + " -> " + g.getParent().getUmlClass().getRefId());
                        });
                        
                        
                        System.out.println("---Intefaces---");
                        ///***/// Interfaces
                        p1.getNamespaceOwnedElement().getInterfaces().forEach(i -> {
                            System.out.println(i.getName());
                        });
                        
                        System.out.println("---Abstractions---");
                        ///***/// Abstractions
                        p1.getNamespaceOwnedElement().getAbstractions().forEach(a -> {
                            System.out.println(a.getDependencyClient().getUmlclass().getRefId()+ ", " + 
                                    a.getDependencySupplier().getUmlinterface().getRefId());
                        });
                        
                        System.out.println("---Stereotypes---");
                        ///***/// Abstractions
                        p1.getNamespaceOwnedElement().getStereotypes().forEach(a -> {
                            System.out.println(a.getName());
                            System.out.println(a.getStereotypeBaseClasses().getContent());
                        });
                    });
                });
            });
        });
    }
}
