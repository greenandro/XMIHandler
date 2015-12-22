package xmi.metamodel.content;

import java.util.ArrayList;
import java.util.List;
import xmi.metamodel.interfaces.XMISerializable;

public class UMLClassifierFeature implements XMISerializable {

    private List<UMLAttribute> attributes;
    private List<UMLOperation> operations;

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
    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<UML:Classifier.feature>\n");
        for(UMLAttribute a : attributes) {
            sb.append(a.toXmi());
        }
        for(UMLOperation o : operations) {
            sb.append(o.toXmi());
        }
        sb.append("</UML:Classifier.feature>\n");
        return sb.toString();
    }
    
    @Override
    public String toEcore() {
        return "";
    }
    
}