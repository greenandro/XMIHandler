package xmi.metamodel.content;

public class UMLMultiplicity {

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
    
}