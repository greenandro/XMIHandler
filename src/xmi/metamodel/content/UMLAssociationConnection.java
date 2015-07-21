package xmi.metamodel.content;

public class UMLAssociationConnection {

    public UMLAssociationEnd associationEnd2;
    public UMLAssociationEnd associationEnd1;

    public UMLAssociationConnection(UMLAssociationEnd associationEnd2, UMLAssociationEnd associationEnd1) {
        this.associationEnd2 = associationEnd2;
        this.associationEnd1 = associationEnd1;
    }

    public UMLAssociationEnd getAssociationEnd2() {
        return associationEnd2;
    }

    public void setAssociationEnd2(UMLAssociationEnd associationEnd2) {
        this.associationEnd2 = associationEnd2;
    }

    public UMLAssociationEnd getAssociationEnd1() {
        return associationEnd1;
    }

    public void setAssociationEnd1(UMLAssociationEnd associationEnd1) {
        this.associationEnd1 = associationEnd1;
    }
    
}