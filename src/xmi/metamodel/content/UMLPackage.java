package xmi.metamodel.content;

import java.util.ArrayList;
import java.util.List;

public class UMLPackage {

    private String id;
    private String name;
    private boolean isSpecification;
    private boolean isRoot;
    private boolean isLeaf;
    private boolean isAbstract;
    
    private List<UMLPackage> packages;
    private List<UMLClass> classes;
    private List<UMLAssociation> associations;
    private List<UMLGeneralization> generatlizations;
    private List<UMLInterface> interfaces;
    private List<UMLAbstraction> abstractions;
    private List<UMLStereotype> stereotypes;
    

    public UMLPackage(String id, String name, boolean isSpecification, boolean isRoot, boolean isLeaf, boolean isAbstract) {
        this.id = id;
        this.name = name;
        this.isSpecification = isSpecification;
        this.isRoot = isRoot;
        this.isLeaf = isLeaf;
        this.isAbstract = isAbstract;
        this.packages = new ArrayList<>();
        this.classes = new ArrayList<>();
        this.associations = new ArrayList<>();
        this.generatlizations = new ArrayList<>();
        this.interfaces = new ArrayList<>();
        this.abstractions = new ArrayList<>();
        this.stereotypes = new ArrayList<>();
    }

    
    
    public UMLPackage() {
        this.packages = new ArrayList<>();
        this.classes = new ArrayList<>();
        this.associations = new ArrayList<>();
        this.generatlizations = new ArrayList<>();
        this.interfaces = new ArrayList<>();
        this.abstractions = new ArrayList<>();
        this.stereotypes = new ArrayList<>();
    }

    public List<UMLStereotype> getStereotypes() {
        return stereotypes;
    }
    
    public List<UMLAssociation> getAssociations() {
        return associations;
    }

    public List<UMLClass> getClasses() {
        return classes;
    }

    public List<UMLGeneralization> getGeneratlizations() {
        return generatlizations;
    }

    public List<UMLInterface> getInterfaces() {
        return interfaces;
    }

    public List<UMLPackage> getPackages() {
        return packages;
    }

    public List<UMLAbstraction> getAbstractions() {
        return abstractions;
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
    
    
    
}