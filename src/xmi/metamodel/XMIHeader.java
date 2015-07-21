package xmi.metamodel;

public class XMIHeader {

    public XMIDocumentation documentation;
    public XMIMetamodel metamodel;

    public XMIHeader() {
    }

    public XMIHeader(XMIDocumentation documentation, XMIMetamodel metamodel) {
        this.documentation = documentation;
        this.metamodel = metamodel;
    }

    public XMIDocumentation getDocumentation() {
        return documentation;
    }

    public void setDocumentation(XMIDocumentation documentation) {
        this.documentation = documentation;
    }

    public XMIMetamodel getMetamodel() {
        return metamodel;
    }

    public void setMetamodel(XMIMetamodel metamodel) {
        this.metamodel = metamodel;
    }   
}