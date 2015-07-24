package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMISerializable;

/**
 * Represent an UML association connection with two ends
 * @author ruicouto
 */
public class UMLAssociationConnection implements XMISerializable {

    /** The first end */
    private UMLAssociationEnd associationEnd1;
    /** The second end */
    private UMLAssociationEnd associationEnd2;

    /**
     * Create an empty association.
     * Both ends are null!
     */
    public UMLAssociationConnection() {
    }  
    
    /**
     * Create an UML association by specifying both ends
     *
     * @param associationEnd1 The first association end
     * @param associationEnd2 The second association end
     */
    public UMLAssociationConnection(UMLAssociationEnd associationEnd1, UMLAssociationEnd associationEnd2) {
        this.associationEnd1 = associationEnd1;
        this.associationEnd2 = associationEnd2;
    }

    /**
     * Get the first association end
     * @return The instance if set, null otherwise.
     */
    public UMLAssociationEnd getAssociationEnd1() {
        return associationEnd1;
    }

    /**
     * Set the first association end
     * @param associationEnd1 
     */
    public void setAssociationEnd1(UMLAssociationEnd associationEnd1) {
        this.associationEnd1 = associationEnd1;
    }
    
    /**
     * Get the second association end
     * @return The instance if set, null otherwise.
     */
    public UMLAssociationEnd getAssociationEnd2() {
        return associationEnd2;
    }

    /**
     * Set the second association end
     * @param associationEnd2 
     */
    public void setAssociationEnd2(UMLAssociationEnd associationEnd2) {
        this.associationEnd2 = associationEnd2;
    }

    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<UML:Association.connection>\n");
        sb.append(associationEnd1.toXmi());
        sb.append(associationEnd2.toXmi());
        sb.append("</UML:Association.connection>\n");
        return sb.toString();
    }
    
}