package xmi.metamodel.content;

import java.util.ArrayList;
import java.util.List;

public class UMLDependencySupplier {
    
    public List<UMLInterface>  interfaces;

    public UMLDependencySupplier() {
        interfaces = new ArrayList<>();
    }

    public List<UMLInterface> getInterfaces() {
        return interfaces;
    }
    
}