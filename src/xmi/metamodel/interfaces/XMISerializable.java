/*
 * XMISerializable
 * ruicouto in 21/jul/2015
 */
package xmi.metamodel.interfaces;

/**
 * This interface indicates that a class can be serialized to XMI
 * @author ruicouto
 */
public interface XMISerializable {
    /**
     * Convert the current object to XMI
     * @return 
     */
    public String toXmi();
}
