package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMISerializable;

/**
 * Probably could contain an interface too. TODO!
 * @author ruicouto
 */
public class UMLGeneralizationChild implements XMISerializable {

    private UMLClass umlClass;

    public UMLGeneralizationChild(UMLClass umlClass) {
        this.umlClass = umlClass;
    }

    public UMLClass getUmlClass() {
        return umlClass;
    }

    public void setUmlClass(UMLClass umlClass) {
        this.umlClass = umlClass;
    }
    
    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<UML:Generalization.child>");
        sb.append(umlClass.toXmi());
        sb.append("</UML:Generalization.child>");
        return sb.toString();
    }     
    
    @Override
    public String toEcore() {
        return "";
    }
}