package xmi.metamodel;

import java.util.ArrayList;
import java.util.List;
import xmi.metamodel.content.UMLModel;

public class XMIContent {

    /**
     * 
     * @element-type UMLModel
     */
    private final List<UMLModel>  models;

    public XMIContent() {
        models = new ArrayList<>();
    }

    public List<UMLModel> getModels() {
        return models;
    }

}