package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMISerializable;

public class UMLDependencyClient implements XMISerializable {

    private UMLClass umlclass;

    public UMLDependencyClient() {
        this.umlclass = null;
    }

    public UMLClass getUmlclass() {
        return umlclass;
    }

    public void setUmlclass(UMLClass umlclass) {
        this.umlclass = umlclass;
    }
    
    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<UML:Dependency.client>\n");
        sb.append(umlclass.toXmi());
        sb.append("</UML:Dependency.client>\n");
        return sb.toString();
    }
    
    @Override
    public String toEcore() {
        return "";
    }

}