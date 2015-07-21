package xmi.metamodel.content;

public class UMLMultiplicityRange {
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
    
}