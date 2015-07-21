package xmi.metamodel.content;

import java.util.ArrayList;
import java.util.List;

public class UMLParameterType {

    public List<UMLDataType> dataTypes;
    public List<UMLClass>  umlclasses;

    public UMLParameterType(UMLClass umlclass) {
        dataTypes = new ArrayList<>();
        umlclasses = new ArrayList<>();
        umlclasses.add(umlclass);
    }
    
    public UMLParameterType(UMLDataType dataType) {
        dataTypes = new ArrayList<>();
        umlclasses = new ArrayList<>();
        dataTypes.add(dataType);
    }
    
    public UMLParameterType() {
        dataTypes = new ArrayList<>();
        umlclasses = new ArrayList<>();
    }

    public List<UMLDataType> getDataTypes() {
        return dataTypes;
    }

    public List<UMLClass> getUmlclasses() {
        return umlclasses;
    }
    
    
    
    

}