package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMISerializable;

public class UMLDataType implements XMISerializable{

    private String href;

    public UMLDataType(String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
    
    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<UML:DataType href = '").append(href).append("'/>\n");
        return sb.toString();
    }
    
    @Override
    public String toEcore() {
        return "";
    }
}