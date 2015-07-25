package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMIReferenceable;
import xmi.metamodel.interfaces.XMISerializable;

public class UMLStereotype implements XMIReferenceable, XMISerializable {

    private String id;
    private String name;
    private boolean isSpecification;
    private boolean isRoot;
    private boolean isLeaf;
    private boolean isAbstract;
    
    private String idref;

    private UMLStereotypeBaseClass stereotypeBaseClasses;

    public UMLStereotype(String idref) {
        this.idref = idref;
    }
        
    public UMLStereotype(String id, String name, boolean isSpecification, boolean isRoot, boolean isLeaf, boolean isAbstract, UMLStereotypeBaseClass stereotypeBaseClasses) {
        this.id = id;
        this.name = name;
        this.isSpecification = isSpecification;
        this.isRoot = isRoot;
        this.isLeaf = isLeaf;
        this.isAbstract = isAbstract;
        this.stereotypeBaseClasses = stereotypeBaseClasses;
    }

    @Override
    public String getIdref() {
        return idref;
    }

    @Override
    public void setIdref(String idref) {
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

    public UMLStereotypeBaseClass getStereotypeBaseClasses() {
        return stereotypeBaseClasses;
    }

    public void setStereotypeBaseClasses(UMLStereotypeBaseClass stereotypeBaseClasses) {
        this.stereotypeBaseClasses = stereotypeBaseClasses;
    }
    
    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        if(idref!=null) {
            sb.append("<UML:Stereotype xmi.idref = '").append(idref).append("'/>\n");
        } else {
            sb.append("<UML:Stereotype xmi.id = '").append(id).append("' name = '").append(name)
                    .append("' isSpecification = '").append(isSpecification).append("' isRoot = '").append(isRoot)
                    .append("' isLeaf = '").append(isLeaf).append("' isAbstract = '").append(isAbstract).append("'>\n");
            sb.append(stereotypeBaseClasses.toXmi());
            sb.append("</UML:Stereotype>\n");
        }
        return sb.toString();
    }
    
    

}
