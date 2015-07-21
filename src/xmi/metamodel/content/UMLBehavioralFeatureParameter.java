package xmi.metamodel.content;

import java.util.ArrayList;
import java.util.List;

public class UMLBehavioralFeatureParameter {

    public List<UMLParameter>  parameters;

    public UMLBehavioralFeatureParameter() {
        parameters = new ArrayList<>();
    }
    
    public List<UMLParameter> getParameters() {
        return parameters;
    }
    
}