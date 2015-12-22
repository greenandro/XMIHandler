package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMISerializable;

public class UMLAssociationEndParticipant implements XMISerializable{

    private UMLClass umlClass;
    private UMLInterface umlInterface;

    public UMLAssociationEndParticipant(UMLClass umlClass, UMLInterface umlInterface) {
        this.umlClass = umlClass;
        this.umlInterface = umlInterface;
    }

    public UMLClass getUmlClass() {
        return umlClass;
    }

    public void setUmlClass(UMLClass umlClass) {
        this.umlClass = umlClass;
    }

    public UMLInterface getUmlInterface() {
        return umlInterface;
    }

    public void setUmlInterface(UMLInterface umlInterface) {
        this.umlInterface = umlInterface;
    }
    
    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<UML:AssociationEnd.participant>\n");
        sb.append(umlClass!=null?umlClass.toXmi():umlInterface.toXmi());
        sb.append("</UML:AssociationEnd.participant>\n");
        return sb.toString();
    }
 
    @Override
    public String toEcore() {
        return "";
    }
}