package xmi.metamodel.content;

public class UMLGeneralization {

    private UMLGeneralizationParent parent;
    private UMLGeneralizationChild child;

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