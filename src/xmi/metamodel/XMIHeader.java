package xmi.metamodel;

import xmi.metamodel.interfaces.XMISerializable;

public class XMIHeader implements XMISerializable {

    private XMIDocumentation documentation;
    private XMIMetamodel metamodel;

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

    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<XMI.header>");
        sb.append("<XMI.documentation>");
        sb.append(documentation.toXmi());
        sb.append("</XMI.documentation>");
        sb.append(metamodel.toXmi());
        sb.append("</XMI.header>");
        return sb.toString();
    }
    
}