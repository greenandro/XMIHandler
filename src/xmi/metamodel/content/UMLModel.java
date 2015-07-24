package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMINamespaceOwner;
import xmi.metamodel.interfaces.XMISerializable;

public class UMLModel implements XMINamespaceOwner, XMISerializable {

    private String id;
    private String name;
    private boolean isSpecification;
    private boolean isRoot;
    private boolean isLeaf;
    private boolean isAbstract;

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

    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<UML:Model xmi.id = '").append(id).append("' ")
                .append("name = '").append(name).append("' isSpecification = '").append(isSpecification)
                .append("' isRoot = '").append(isRoot).append("' isLeaf = '").append(isLeaf)
                .append("isAbstract = '").append(isAbstract).append("'>\n");
        
        for(UMLAbstraction a : ownedElement.getAbstractions()) {
            sb.append(a.toXmi());
        }
        
        for(UMLAssociation a : ownedElement.getAssociations()) {
            sb.append(a.toXmi());
        }
        
        for(UMLClass c : ownedElement.getClasses()) {
            sb.append(c.toXmi());
        }
        
        for(UMLGeneralization g : ownedElement.getGeneratlizations()) {
            sb.append(g.toXmi());
        }
        
        for(UMLInterface i : ownedElement.getInterfaces()) {
            sb.append(i.toXmi());
        }
        
        for(UMLPackage p : ownedElement.getPackages()) {
            
        }
        
        for(UMLStereotype s : ownedElement.getStereotypes()) {
            
        }
        sb.append("</UML:Model>\n");
        return sb.toString();
    }
    
}
