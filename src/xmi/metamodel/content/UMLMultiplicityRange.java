package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMISerializable;

public class UMLMultiplicityRange implements XMISerializable {
    private String id;
    private char lower;
    private char upper;

    public UMLMultiplicityRange(String id, char lower, char upper) {
        this.id = id;
        this.lower = lower;
        this.upper = upper;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public char getLower() {
        return lower;
    }

    public void setLower(char lower) {
        this.lower = lower;
    }

    public char getUpper() {
        return upper;
    }

    public void setUpper(char upper) {
        this.upper = upper;
    }
    
    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<UML:Multiplicity.range>\n");
        sb.append("<UML:MultiplicityRange xmi.id = '").append(id).append("' lower = '")
            .append(lower).append("' upper = '").append(upper).append("'/>\n");
        sb.append("</UML:Multiplicity.range>\n");
        return sb.toString();
    }
    
}