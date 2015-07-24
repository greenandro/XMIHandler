package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMISerializable;

public class UMLStereotypeBaseClass implements XMISerializable {

    private String content;

    public UMLStereotypeBaseClass(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<UML:Stereotype.baseClass>").append(content).append("</UML:Stereotype.baseClass>");
        
        return sb.toString();
    }
}
