package xmi.metamodel.content;

import java.util.ArrayList;
import java.util.List;
import xmi.metamodel.interfaces.XMISerializable;

public class UMLParameterType implements XMISerializable {

    private List<UMLDataType> dataTypes;
    private List<UMLClass>  umlclasses;

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
    
    
    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<UML:Parameter.type>");
        for(UMLDataType d : dataTypes) {
            sb.append(d.toXmi());
        }
        for(UMLClass c : umlclasses) {
            sb.append(c.toXmi());
        }
        sb.append("</UML:Parameter.type>");
        return sb.toString();
    }
    
}