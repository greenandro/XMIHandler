package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMIReferenceable;
import xmi.metamodel.interfaces.XMISerializable;

public class UMLGeneralization implements XMIReferenceable, XMISerializable {

    private String idref;
    private String id;
    private boolean isSpecification;
    
    private UMLGeneralizationParent parent;
    private UMLGeneralizationChild child;

    public UMLGeneralization(String idref) {
        this.idref = idref;
    }

    public UMLGeneralization(String id, boolean isSpecification, UMLGeneralizationParent parent, UMLGeneralizationChild child) {
        this.id = id;
        this.isSpecification = isSpecification;
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

    public String getId() {
        return id;
    }

    public boolean isIsSpecification() {
        return isSpecification;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIsSpecification(boolean isSpecification) {
        this.isSpecification = isSpecification;
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

    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        if(idref!=null) {
            sb.append("<UML:Generalization xmi.idref = '").append(idref).append("'/>\n");
        } else {
            sb.append("<UML:Generalization xmi.id = '").append(id).append("' isSpecification = '").append(isSpecification).append("'>\n");
            sb.append("<UML:Generalization.child>\n");
            sb.append("<UML:Class xmi.idref = '").append(child.getUmlClass().getId()).append("'/>\n");
            sb.append("</UML:Generalization.child>\n");
            sb.append("<UML:Generalization.parent>\n");
            sb.append("<UML:Class xmi.idref = '").append(parent.getUmlClass().getId()).append("'/>\n");
            sb.append("</UML:Generalization.parent>\n");
            sb.append("</UML:Generalization>\n");
        }
        return sb.toString();
    }
    
}