package xmi.metamodel;

import xmi.metamodel.interfaces.XMISerializable;

public class XMI implements XMISerializable {
    
    private String version;
    private String xmlns;
    private String timeStamp;
    private XMIHeader header;
    private XMIContent content;

    public XMI() {
        header = new XMIHeader();
        content = new XMIContent();
    }

    
    public XMI(String version, String xmlns, String timeStamp, XMIHeader header, XMIContent content) {
        this.version = version;
        this.xmlns = xmlns;
        this.timeStamp = timeStamp;
        this.header = header;
        this.content = content;
    }

    
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public XMIHeader getHeader() {
        return header;
    }

    public void setHeader(XMIHeader header) {
        this.header = header;
    }

    public XMIContent getContent() {
        return content;
    }

    public void setContent(XMIContent content) {
        this.content = content;
    }

    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version = '1.0' encoding = 'UTF-8' ?>\n");
        sb.append("<XMI xmi.version = '1.2' xmlns:UML = 'org.omg.xmi.namespace.UML' timestamp = 'Fri Jul 24 09:48:50 WEST 2015'>\n");
        sb.append(header.toXmi());
        sb.append(content.toXmi());
        sb.append("</XMI>\n");
        return sb.toString();
    }
    
}
