package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMISerializable;


public class UMLAttribute implements XMISerializable {

    private String id;
    private String name;
    private String visibility;
    private boolean isSpecification;
    private String ownerScope;
    private String changeability;
    private String targetScope;
    
    private UMLStructuralFeatureMulticiply structuralFeatureMultiplicity;
    private UMLStructuralFeatureType structuralFeatureType;

    public UMLAttribute(String id, String name, String visibility, boolean isSpecification, String ownerScope, String changeability, String targetScope) {
        this.id = id;
        this.name = name;
        this.visibility = visibility;
        this.isSpecification = isSpecification;
        this.ownerScope = ownerScope;
        this.changeability = changeability;
        this.targetScope = targetScope;
        this.structuralFeatureMultiplicity = null;
        this.structuralFeatureType = new UMLStructuralFeatureType();
    }
   
    
    public UMLAttribute() {
        this.structuralFeatureMultiplicity = null;
        this.structuralFeatureType = null;
    }

    public UMLStructuralFeatureMulticiply getStructuralFeatureMultiplicity() {
        return structuralFeatureMultiplicity;
    }

    public void setStructuralFeatureMultiplicity(UMLStructuralFeatureMulticiply structuralFeatureMultiplicity) {
        this.structuralFeatureMultiplicity = structuralFeatureMultiplicity;
    }

    public UMLStructuralFeatureType getStructuralFeatureType() {
        return structuralFeatureType;
    }

    public void setStructuralFeatureType(UMLStructuralFeatureType structuralFeatureType) {
        this.structuralFeatureType = structuralFeatureType;
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

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public boolean isIsSpecification() {
        return isSpecification;
    }

    public void setIsSpecification(boolean isSpecification) {
        this.isSpecification = isSpecification;
    }

    public String getOwnerScope() {
        return ownerScope;
    }

    public void setOwnerScope(String ownerScope) {
        this.ownerScope = ownerScope;
    }

    public String getChangeability() {
        return changeability;
    }

    public void setChangeability(String changeability) {
        this.changeability = changeability;
    }

    public String getTargetScope() {
        return targetScope;
    }

    public void setTargetScope(String targetScope) {
        this.targetScope = targetScope;
    }
    
    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<UML:Attribute xmi.id = '").append(id)
                .append("' name = '").append(name).append("' visibility = '").append(visibility)
                .append("' isSpecification = '").append(isSpecification).append("' ownerScope = '").append(ownerScope)
                .append("' changeability = '").append(changeability).append("' targetScope = '").append(targetScope).append("'>");
        sb.append(structuralFeatureMultiplicity.toXmi());
        sb.append(structuralFeatureType.toXmi());
        sb.append("</UML:Attribute>");
        return sb.toString();
    }
    
}