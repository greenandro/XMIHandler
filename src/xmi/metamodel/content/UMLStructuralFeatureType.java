package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMISerializable;

public class UMLStructuralFeatureType implements XMISerializable{

    private UMLDataType dataType;
    private UMLClass umlclasse;
    
    public String holder;

    public UMLDataType getDataType() {
        return dataType;
    }

    public void setDataType(UMLDataType dataType) {
        this.dataType = dataType;
    }

    public UMLClass getUmlclass() {
        return umlclasse;
    }

    public void setUmlclass(UMLClass umlclasses) {
        this.umlclasse = umlclasses;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<UML:StructuralFeature.type>");
        if(dataType!=null) {
            sb.append(dataType.toXmi());
        } else if(umlclasse!=null) {
            sb.append(umlclasse.toXmi());    
        }
        sb.append("</UML:StructuralFeature.type>");
        return sb.toString();
    }
    
    @Override
    public String toEcore() {
        return "";
    }

}