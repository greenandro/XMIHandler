package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMISerializable;

public class UMLModelElementStereotype implements XMISerializable {

    private UMLStereotype umlStereotype;

    public UMLModelElementStereotype(UMLStereotype umlStereotype) {
        this.umlStereotype = umlStereotype;
    }

    public UMLStereotype getUmlStereotype() {
        return umlStereotype;
    }

    public void setUmlStereotype(UMLStereotype umlStereotype) {
        this.umlStereotype = umlStereotype;
    }
    
    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<UML:ModelElement.stereotype>\n");
        sb.append(umlStereotype.toXmi());
        sb.append("</UML:ModelElement.stereotype>\n");
        return sb.toString();
    }
                      
}