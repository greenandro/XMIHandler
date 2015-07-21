package xmi.metamodel.content;

import java.util.ArrayList;
import java.util.List;


public class UMLParameter {

    public String id;
    public String name;
    public boolean isSpecification;
    public String kind;
    public UMLParameterType parameterType;

    public UMLParameter(String id, String name, boolean isSpecification, String kind) {
        this.id = id;
        this.name = name;
        this.isSpecification = isSpecification;
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsSpecification() {
        return isSpecification;
    }

    public void setIsSpecification(boolean isSpecification) {
        this.isSpecification = isSpecification;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public UMLParameterType getParameterType() {
        return parameterType;
    }

    public void setParameterType(UMLParameterType parameterType) {
        this.parameterType = parameterType;
    }

    
    
    
    

}
