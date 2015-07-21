package xmi.metamodel.content;

public class UMLMultiplicity {

    public UMLMultiplicityRange  multiplicityRange;

    public UMLMultiplicity(UMLMultiplicityRange multiplicityRange) {
        this.multiplicityRange = multiplicityRange;
    }

    public UMLMultiplicityRange getMultiplicityRange() {
        return multiplicityRange;
    }

    public void setMultiplicityRange(UMLMultiplicityRange multiplicityRange) {
        this.multiplicityRange = multiplicityRange;
    }
      
  
}