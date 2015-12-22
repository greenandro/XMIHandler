package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMISerializable;

public class UMLStructuralFeatureMulticiply implements XMISerializable {

    private UMLMultiplicity  multiplicity;

    public UMLStructuralFeatureMulticiply(UMLMultiplicity multiplicity) {
        this.multiplicity = multiplicity;
    }

    public UMLMultiplicity getMultiplicity() {
        return multiplicity;
    }

    public void setMultiplicity(UMLMultiplicity multiplicity) {
        this.multiplicity = multiplicity;
    }

    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<UML:StructuralFeature.multiplicity>");
        sb.append(multiplicity.toXmi());
        sb.append("</UML:StructuralFeature.multiplicity>");
        return sb.toString();
    }
    
    @Override
    public String toEcore() {
        return "";
    }
    
}