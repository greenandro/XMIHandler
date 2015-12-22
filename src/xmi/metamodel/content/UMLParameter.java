package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMISerializable;

public class UMLParameter implements XMISerializable {

    private String id;
    private String name;
    private boolean isSpecification;
    private String kind;
    private UMLParameterType parameterType;

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

    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<UML:Parameter xmi.id = '").append(id).append("' name = '").append(name).append("' isSpecification = '").append(isSpecification).append("' kind = '").append(kind).append("'>\n");
        if(parameterType!=null) {
            sb.append(parameterType.toXmi());
        }
        sb.append("</UML:Parameter>\n");
        return sb.toString();
    }
    
    @Override
    public String toEcore() {
        return "";
    }
    
}
