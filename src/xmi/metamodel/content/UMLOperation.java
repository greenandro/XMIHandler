package xmi.metamodel.content;

public class UMLOperation {

    public String id;
    public String name;
    public String visibility;
    public boolean isSpecification;
    public String ownerScope;
    public boolean isQuery;
    public String concurrency;
    public boolean isRoot;
    public boolean isLeaf;
    public boolean isAbstract;
    public UMLBehavioralFeatureParameter behavioralFeatureParameter;

    public UMLOperation(String id, String name, String visibility, boolean isSpecification, String ownerScope, boolean isQuery, String concurrency, boolean isRoot, boolean isLeaf, boolean isAbstract) {
        this.id = id;
        this.name = name;
        this.visibility = visibility;
        this.isSpecification = isSpecification;
        this.ownerScope = ownerScope;
        this.isQuery = isQuery;
        this.concurrency = concurrency;
        this.isRoot = isRoot;
        this.isLeaf = isLeaf;
        this.isAbstract = isAbstract;
        this.behavioralFeatureParameter = new UMLBehavioralFeatureParameter();
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

    public boolean isIsQuery() {
        return isQuery;
    }

    public void setIsQuery(boolean isQuery) {
        this.isQuery = isQuery;
    }

    public String getConcurrency() {
        return concurrency;
    }

    public void setConcurrency(String concurrency) {
        this.concurrency = concurrency;
    }

    public boolean isIsRoot() {
        return isRoot;
    }

    public void setIsRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }

    public boolean isIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public boolean isIsAbstract() {
        return isAbstract;
    }

    public void setIsAbstract(boolean isAbstract) {
        this.isAbstract = isAbstract;
    }

    public UMLBehavioralFeatureParameter getBehavioralFeatureParameter() {
        return behavioralFeatureParameter;
    }

    public void setBehavioralFeatureParameter(UMLBehavioralFeatureParameter behavioralFeatureParameter) {
        this.behavioralFeatureParameter = behavioralFeatureParameter;
    }

}
