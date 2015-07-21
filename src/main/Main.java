/*
 * Main
 * ruicouto in 16/jul/2015
 */
package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import xmi.metamodel.XMI;
import xmi.metamodel.XMIContent;
import xmi.metamodel.XMIDocumentation;
import xmi.metamodel.XMIHeader;
import xmi.metamodel.XMIMetamodel;

/**
 *
 * @author ruicouto
 */
public class Main {

    public static void main(String[] args, int i) throws Exception {
        Main m = new Main();
        m.parseXmiFile("XMI.xmi");
    }
    
    
    public XMI parseXmiFile(String path) throws IOException, ParserConfigurationException, SAXException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(path));

        XMI xmi = new XMI();

        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            switch (node.getNodeName()) {
                case "XMI.header":
                    XMIHeader h = parseHeader(node.getChildNodes());
                    xmi.setHeader(h);
                    break;
                case "XMI.content":
                    XMIContent c = parseContent(node.getChildNodes());
                    xmi.setContent(c);
                    break;
            }
        }
        return xmi;
    }

    /**
     * Parse the header
     *
     * @param nodelist
     * @return
     */
    public XMIHeader parseHeader(NodeList nodelist) {
        System.out.println(nodelist.getLength());
        XMIHeader header = new XMIHeader();
        for (int i = 0; i < nodelist.getLength(); i++) {
            Node n = nodelist.item(i);
            switch (n.getNodeName()) {
                case "XMI.documentation":
                    NodeList nl = n.getChildNodes();
                    String xmiex = "";
                    String xmiev = "";
                    for (int j = 0; j < nl.getLength(); j++) {
                        Node n2 = nl.item(j);
                        switch (n2.getNodeName()) {
                            case "XMI.exporter":
                                xmiex = n2.getTextContent();
                                break;
                            case "XMI.exporterVersion":
                                xmiev = n2.getTextContent();
                                break;
                        }
                    }
                    XMIDocumentation doc = new XMIDocumentation(xmiex, xmiev);
                    header.setDocumentation(doc);
                    break;
                case "XMI.metamodel":

                    XMIMetamodel mm = new XMIMetamodel(n.getAttributes().getNamedItem("xmi.name").getNodeValue(),
                            n.getAttributes().getNamedItem("xmi.version").getNodeValue());
                    header.setMetamodel(mm);
                    break;
            }
        }
        return header;
    }

    
    private XMIContent parseContent(NodeList childNodes) {
        System.out.println("Parse content");
        XMIContent cont = new XMIContent();
        
        for(int i=0;i<childNodes.getLength();i++) {
            Node n = childNodes.item(i);
            
            if(n.getNodeName().equals("UML:Model")) {
                NodeList nl2 = n.getChildNodes();
                for(int j=0;j<nl2.getLength();j++) {
                    System.out.println(nl2.item(j));
                }
            }
        }
        
        
        return cont;
    }

}
