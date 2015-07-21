package xmi.metamodel.content;

import java.util.ArrayList;
import java.util.List;

public class UMLAssociationEndMultiplicity {

    public List<UMLMultiplicity> multiplicity;

    public UMLAssociationEndMultiplicity() {
        multiplicity = new ArrayList<>();
    }

    public List<UMLMultiplicity> getMultiplicity() {
        return multiplicity;
    }    
}