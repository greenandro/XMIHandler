package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMIReferenceable;


public class UMLInterface implements XMIReferenceable {

    private String id;
    private String name;
    private String visibility;
    private boolean isSpecification;
    private boolean isRoot;
    private boolean isLeaf;
    private boolean isAbstract;
    
    private String idref;

    private UMLClassifierFeature classifierFeature;

    public UMLInterface(String idref) {
        this.idref = idref;
    }
    
    public UMLInterface(String id, String name, String visibility, boolean isSpecification, boolean isRoot, boolean isLeaf, boolean isAbstract) {
        this.id = id;
        this.name = name;
        this.visibility = visibility;
        this.isSpecification = isSpecification;
        this.isRoot = isRoot;
        this.isLeaf = isLeaf;
        this.isAbstract = isAbstract;
        this.classifierFeature = new UMLClassifierFeature();
    }

    @Override
    public String getIdref() {
        return idref;
    }

    @Override
    public void setIdref(String refId) {
        this.idref = idref;
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

    public UMLClassifierFeature getClassifierFeature() {
        return classifierFeature;
    }

    public void setClassifierFeatures(UMLClassifierFeature classifierFeature) {
        this.classifierFeature = classifierFeature;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
