package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMIReferenceable;

public class UMLGeneralization implements XMIReferenceable {

    private String idref;
    
    private UMLGeneralizationParent parent;
    private UMLGeneralizationChild child;

    public UMLGeneralization(String idref) {
        this.idref = idref;
    }

    public UMLGeneralization(UMLGeneralizationParent parent, UMLGeneralizationChild child) {
        this.parent = parent;
        this.child = child;
    }

    @Override
    public String getIdref() {
        return this.idref;
    }
    
    @Override
    public void setIdref(String idref) {
        this.idref = idref;
    }

    public UMLGeneralizationChild getChild() {
        return child;
    }

    public UMLGeneralizationParent getParent() {
        return parent;
    }

    public void setChild(UMLGeneralizationChild child) {
        this.child = child;
    }

    public void setParent(UMLGeneralizationParent parent) {
        this.parent = parent;
    }

    
        
}