package xmi.metamodel;

public class XMI {
    private String version;
    private String xmlns;
    private String timeStamp;
    private XMIHeader header;
    private XMIContent content;

    public XMI() {
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

    
    
}
