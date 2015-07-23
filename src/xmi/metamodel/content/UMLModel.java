package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMINamespaceOwner;

public class UMLModel implements XMINamespaceOwner {

    public String id;
    public String name;
    public boolean isSpecification;
    public boolean isRoot;
    public boolean isLeaf;
    public boolean isAbstract;

    //public List<UMLPackage> packages;
    private UMLNamespaceOwnedElement ownedElement;

    public UMLModel(String id, String name, boolean isSpecification, boolean isRoot, boolean isLeaf, boolean isAbstract) {
        this.id = id;
        this.name = name;
        this.isSpecification = isSpecification;
        this.isRoot = isRoot;
        this.isLeaf = isLeaf;
        this.isAbstract = isAbstract;
        this.ownedElement = new UMLNamespaceOwnedElement();
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


    @Override
    public UMLNamespaceOwnedElement getNamespaceOwnedElement() {
        return ownedElement;
    }

    @Override
    public void setNamespaceOwnedElement(UMLNamespaceOwnedElement namespaceOwnedElement) {
        this.ownedElement = namespaceOwnedElement;
    }

}
