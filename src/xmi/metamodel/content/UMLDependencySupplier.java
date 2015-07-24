package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMISerializable;


public class UMLDependencySupplier implements XMISerializable{
    
    private UMLInterface umlinterface;

    public UMLDependencySupplier() {
    }

    public UMLDependencySupplier(UMLInterface umlinterface) {
        this.umlinterface = umlinterface;
    }

    public UMLInterface getUmlinterface() {
        return umlinterface;
    }

    public void setUmlinterface(UMLInterface umlinterface) {
        this.umlinterface = umlinterface;
    }

    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<UML:Dependency.supplier>\n");
        sb.append(umlinterface.toXmi());
        sb.append("</UML:Dependency.supplier>\n");
        return sb.toString();
    }
    
}