/*
 * XMINamespaceOwner
 * ruicouto in 23/jul/2015
 */
package xmi.metamodel.interfaces;

import xmi.metamodel.content.UMLNamespaceOwnedElement;

/**
 *
 * @author ruicouto
 */
public interface XMINamespaceOwner {
    public UMLNamespaceOwnedElement getNamespaceOwnedElement();
    public void setNamespaceOwnedElement(UMLNamespaceOwnedElement namespaceOwnedElement);
}
