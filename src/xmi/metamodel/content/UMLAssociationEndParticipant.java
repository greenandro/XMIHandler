package xmi.metamodel.content;

public class UMLAssociationEndParticipant {

    private UMLClass umlClass;
    private UMLInterface umlInterface;

    public UMLAssociationEndParticipant(UMLClass umlClass, UMLInterface umlInterface) {
        this.umlClass = umlClass;
        this.umlInterface = umlInterface;
    }

    public UMLClass getUmlClass() {
        return umlClass;
    }

    public void setUmlClass(UMLClass umlClass) {
        this.umlClass = umlClass;
    }

    public UMLInterface getUmlInterface() {
        return umlInterface;
    }

    public void setUmlInterface(UMLInterface umlInterface) {
        this.umlInterface = umlInterface;
    }
    
}