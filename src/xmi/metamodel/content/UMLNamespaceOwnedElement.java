package xmi.metamodel.content;

import java.util.ArrayList;
import java.util.List;

/*
 * UMLNamespaceOwnedElement
 * ruicouto in 23/jul/2015
 */

/**
 *
 * @author ruicouto
 */
public class UMLNamespaceOwnedElement {
    
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
   
    
    
    
    
            
}
