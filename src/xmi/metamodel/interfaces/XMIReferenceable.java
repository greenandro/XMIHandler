/*
 * XMIReferenceable
 * ruicouto in 22/jul/2015
 */
package xmi.metamodel.interfaces;

/**
 *
 * @author ruicouto
 */
public interface XMIReferenceable {
    /**
     * If this class is a reference, returns the id which it is referring to.
     * @return The concrete class id
     */
    public String getIdref();
    
    /**
     * Define the id of the concrete class.
     * @param idref The id of the concrete class
     */
    public void setIdref(String idref);
}
