package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMISerializable;

/**
 * TODO: See UMLGeneralizationChild
 * @author ruicouto
 */
public class UMLGeneralizationParent implements XMISerializable {

    private UMLClass umlClass;

    public UMLGeneralizationParent(UMLClass umlClass) {
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
        sb.append("<UML:Generalization.parent>");
        sb.append(umlClass.toXmi());
        sb.append("</UML:Generalization.parent>");
        return sb.toString();
    }
    
}