/*
 * XMIHandler
 * ruicouto in 22/jul/2015
 */
package main;

import xmi.metamodel.XMI;

/**
 * A XMI handler provides a XMI object.
 * This interface states such. 
 * @author ruicouto
 */
public interface XMIHandler {
    /**
     * Get the XMI instance.
     * @return 
     */
    public XMI getXMI();
}
