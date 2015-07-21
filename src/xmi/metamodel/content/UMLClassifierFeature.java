package xmi.metamodel.content;

import java.util.ArrayList;
import java.util.List;

public class UMLClassifierFeature {

    public List<UMLAttribute> attributes;
    public List<UMLOperation> operations;

    protected UMLClassifierFeature() {
        attributes = new ArrayList<>();
        operations = new ArrayList<>();
    }

    public List<UMLAttribute> getAttributes() {
        return attributes;
    }

    public List<UMLOperation> getOperations() {
        return operations;
    }
    
}