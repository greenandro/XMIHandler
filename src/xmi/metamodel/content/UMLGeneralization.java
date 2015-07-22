package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMIReferenceable;

public class UMLGeneralization implements XMIReferenceable {

    private String refId;
    
    private UMLGeneralizationParent parent;
    private UMLGeneralizationChild child;

    public UMLGeneralization(String refId) {
        this.refId = refId;
    }

    @Override
    public String getRefId() {
        return this.refId;
    }

    @Override
    public void setRefId(String refId) {
        this.refId = refId;
    }
    

    
    
    public UMLGeneralization(UMLGeneralizationParent parent, UMLGeneralizationChild child) {
        this.parent = parent;
        this.child = child;
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