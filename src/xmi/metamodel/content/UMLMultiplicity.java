package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMISerializable;

public class UMLMultiplicity implements XMISerializable {

    private String id;
    private UMLMultiplicityRange  multiplicityRange;

    public UMLMultiplicity(String id, UMLMultiplicityRange multiplicityRange) {
        this.id = id;
        this.multiplicityRange = multiplicityRange;
    }

    public UMLMultiplicityRange getMultiplicityRange() {
        return multiplicityRange;
    }

    public void setMultiplicityRange(UMLMultiplicityRange multiplicityRange) {
        this.multiplicityRange = multiplicityRange;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<UML:Multiplicity xmi.id = '").append(id).append("'>");
        sb.append(multiplicityRange.toXmi());
        sb.append("</UML:Multiplicity>");
        return sb.toString();
    }
    
}