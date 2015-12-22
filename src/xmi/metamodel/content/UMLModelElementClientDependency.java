package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMISerializable;

public class UMLModelElementClientDependency implements XMISerializable {

    private UMLAbstraction umlAbstraction;

    public UMLModelElementClientDependency(UMLAbstraction umlAbstraction) {
        this.umlAbstraction = umlAbstraction;
    }

    public UMLAbstraction getUmlAbstraction() {
        return umlAbstraction;
    }

    public void setUmlAbstraction(UMLAbstraction umlAbstraction) {
        this.umlAbstraction = umlAbstraction;
    }
  
    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<UML:ModelElement.clientDependency>\n");
        sb.append(umlAbstraction.toXmi());
        sb.append("</UML:ModelElement.clientDependency>\n");
        return sb.toString();
    }    
    
    @Override
    public String toEcore() {
        return "";
    }
}