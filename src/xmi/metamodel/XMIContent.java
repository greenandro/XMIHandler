package xmi.metamodel;

import java.util.ArrayList;
import java.util.List;
import xmi.metamodel.content.UMLModel;
import xmi.metamodel.interfaces.XMISerializable;

public class XMIContent implements XMISerializable {

    /** The UML models. Currently only class diagrams are supported */
    private final List<UMLModel>  models;

    public XMIContent() {
        models = new ArrayList<>();
    }
    
    public List<UMLModel> getModels() {
        return models;
    }

    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<XMI.content>");
        for(UMLModel m : models) {
            sb.append(m.toXmi());
        }
        sb.append("</XMI.content>");
        return sb.toString();
    }
    
}