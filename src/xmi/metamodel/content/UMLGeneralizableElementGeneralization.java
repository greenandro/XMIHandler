package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMISerializable;

public class UMLGeneralizableElementGeneralization implements XMISerializable {

    private UMLGeneralization  generalizations;

    public UMLGeneralizableElementGeneralization() {
    }

    public UMLGeneralization getGeneralizations() {
        return generalizations;
    }

    public void setGeneralizations(UMLGeneralization generalizations) {
        this.generalizations = generalizations;
    }

    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<UML:GeneralizableElement.generalization>\n");
        sb.append(generalizations.toXmi());
        sb.append("</UML:GeneralizableElement.generalization>\n");
        return sb.toString();
    }
}