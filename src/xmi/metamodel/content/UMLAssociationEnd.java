package xmi.metamodel.content;

public class UMLAssociationEnd {

    public String id;
    public String visibility;
    public boolean isSpecification;
    public boolean isNavigable;
    public String ordering;
    public String aggregation;
    public String targetScope;
    public String changeability;

    private UMLAssociationEndParticipant associationEndParticipants;    
    private UMLAssociationEndMultiplicity associationEndMultiplicities;

    public UMLAssociationEnd(String id, String visibility, boolean isSpecification, boolean isNavigable, String ordering, String aggregation, String targetScope, String changeability) {
        this.id = id;
        this.visibility = visibility;
        this.isSpecification = isSpecification;
        this.isNavigable = isNavigable;
        this.ordering = ordering;
        this.aggregation = aggregation;
        this.targetScope = targetScope;
        this.changeability = changeability;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isIsNavigable() {
        return isNavigable;
    }

    public void setIsNavigable(boolean isNavigable) {
        this.isNavigable = isNavigable;
    }

    public String getOrdering() {
        return ordering;
    }

    public void setOrdering(String ordering) {
        this.ordering = ordering;
    }

    public String getAggregation() {
        return aggregation;
    }

    public void setAggregation(String aggregation) {
        this.aggregation = aggregation;
    }

    public String getTargetScope() {
        return targetScope;
    }

    public void setTargetScope(String targetScope) {
        this.targetScope = targetScope;
    }

    public String getChangeability() {
        return changeability;
    }

    public void setChangeability(String changeability) {
        this.changeability = changeability;
    }

    public UMLAssociationEndParticipant getAssociationEndParticipants() {
        return associationEndParticipants;
    }

    public void setAssociationEndParticipants(UMLAssociationEndParticipant associationEndParticipants) {
        this.associationEndParticipants = associationEndParticipants;
    }

    public UMLAssociationEndMultiplicity getAssociationEndMultiplicities() {
        return associationEndMultiplicities;
    }

    public void setAssociationEndMultiplicities(UMLAssociationEndMultiplicity associationEndMultiplicities) {
        this.associationEndMultiplicities = associationEndMultiplicities;
    }

    

}
