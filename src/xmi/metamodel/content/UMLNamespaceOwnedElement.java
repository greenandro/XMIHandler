package xmi.metamodel.content;

import java.util.ArrayList;
import java.util.List;
import xmi.metamodel.interfaces.XMISerializable;

/*
 * UMLNamespaceOwnedElement
 * ruicouto in 23/jul/2015
 */

/**
 *
 * @author ruicouto
 */
public class UMLNamespaceOwnedElement implements XMISerializable {
    
    private List<UMLPackage> packages;
    private List<UMLClass> classes;
    private List<UMLAssociation> associations;
    private List<UMLGeneralization> generatlizations;
    private List<UMLInterface> interfaces;
    private List<UMLAbstraction> abstractions;
    private List<UMLStereotype> stereotypes;

    public UMLNamespaceOwnedElement() {
        this.classes = new ArrayList<>();
        this.packages = new ArrayList<>();
        this.packages = new ArrayList<>();
        this.classes = new ArrayList<>();
        this.associations = new ArrayList<>();
        this.generatlizations = new ArrayList<>();
        this.interfaces = new ArrayList<>();
        this.abstractions = new ArrayList<>();
        this.stereotypes = new ArrayList<>();
    }

    /**
     * Check if the class with the given name exists
     * @param className
     * @return 
     */
    public boolean containsClass(String className) {
        return getClass(className) != null;
    }
    
    /**
     * Get a class for a given name
     * @param className
     * @return 
     */
    public UMLClass getClass(String className) {
        for(UMLClass c : classes) {
            if(c.getName().equals(className)) {
                return c;
            }
        }
        return null;
    }
    
    /**
     * Check if the interface with the given name exists
     * @param interfaceName
     * @return 
     */
    public boolean containsInterface(String interfaceName) {
        return getInterface(interfaceName) != null;
    }
    
    /**
     * Get an interface for a given name
     * @param interfaceName
     * @return 
     */
    public UMLInterface getInterface(String interfaceName) {
        for(UMLInterface i : interfaces) {
            if(i.getName().equals(interfaceName)) {
                return i;
            }
        }
        return null;
    }
    
    public List<UMLClass> getClasses() {
        return classes;
    }

    public List<UMLPackage> getPackages() {
        return packages;
    }

    public List<UMLAbstraction> getAbstractions() {
        return abstractions;
    }

    public List<UMLAssociation> getAssociations() {
        return associations;
    }

    public List<UMLGeneralization> getGeneratlizations() {
        return generatlizations;
    }

    public List<UMLInterface> getInterfaces() {
        return interfaces;
    }

    public List<UMLStereotype> getStereotypes() {
        return stereotypes;
    }
   
    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<UML:Namespace.ownedElement>\n");
        for(UMLPackage p : packages) {
            sb.append(p.toXmi());
        }
        for(UMLClass c : classes) {
            sb.append(c.toXmi());
        }
        for(UMLAssociation a : associations) {
            sb.append(a.toXmi());
        }
        for(UMLGeneralization g : generatlizations) {
            sb.append(g.toXmi());
        }
        for(UMLInterface i : interfaces) {
            sb.append(i.toXmi());
        }
        for(UMLAbstraction a : abstractions) {
            sb.append(a.toXmi());
        }
        for(UMLStereotype s : stereotypes ) {
            sb.append(s.toXmi());
        }
        sb.append("</UML:Namespace.ownedElement>\n");
        return sb.toString();
    }
    
    
    
            
}
