package xmi.metamodel.content;

import java.util.ArrayList;
import java.util.List;
import xmi.metamodel.interfaces.XMISerializable;

public class UMLBehavioralFeatureParameter implements XMISerializable {

    private List<UMLParameter>  parameters;

    public UMLBehavioralFeatureParameter() {
        parameters = new ArrayList<>();
    }
    
    public List<UMLParameter> getParameters() {
        return parameters;
    }
    
    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<UML:BehavioralFeature.parameter>\n");
        for(UMLParameter p : parameters) {
            sb.append(p.toXmi());
        }
        sb.append("</UML:BehavioralFeature.parameter>\n");
        return sb.toString();
    }
    
    @Override
    public String toEcore() {
        return "";
    }
    
}