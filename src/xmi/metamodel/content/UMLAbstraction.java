package xmi.metamodel.content;

import xmi.metamodel.interfaces.XMIReferenceable;
import xmi.metamodel.interfaces.XMISerializable;

/**
 * Representes a UML abstraction.
 * An UMLAbstraction represented the implementation of an UMLInterface
 * 
 * @author ruicouto
 */
public class UMLAbstraction implements XMIReferenceable, XMISerializable {

    /** Abstraction can be referenced. Temporary field to realize later */
    private String idref;
    /** The id of the abstraction */
    private String id;
    /** Is the abstraction a specification? */
    private boolean isSpecification;
    /** The client, i.e. child class */
    private UMLDependencyClient dependencyClient;
    /** The supplier, i.e. parent class */
    private UMLDependencySupplier  dependencySupplier;
    /** List of model element stereotypes */
    private UMLModelElementStereotype  modelElementStereotype;
    
    /**
     * Use this constructor to create a temporary class that holds a reference to
     * other one.
     * @param idref Concrete class id 
     */
    public UMLAbstraction(String idref) {
        this.idref = idref;
        this.dependencyClient = new UMLDependencyClient();
    }
    
    /**
     * Create a concrete UMLAbstraction instance
     * @param id
     * @param isSpecification 
     */
    public UMLAbstraction(String id, boolean isSpecification) {
        this.id = id;
        this.isSpecification = isSpecification;
        this.dependencyClient = new UMLDependencyClient();
        this.modelElementStereotype = new UMLModelElementStereotype(null);
        this.dependencySupplier = new UMLDependencySupplier();
    }
    
    /**
     * Get the client 
     * @return The child class
     */
    public UMLDependencyClient getDependencyClient() {
        return dependencyClient;
    }

    /**
     * Get the id
     * @return XMI ID
     */
    public String getId() {
        return id;
    }

    /**
     * Is a class a specification?
     * @return 
     */
    public boolean isIsSpecification() {
        return isSpecification;
    }

    /**
     * Define the client
     * @param dependencyClient The child class
     */
    public void setDependencyClient(UMLDependencyClient dependencyClient) {
        this.dependencyClient = dependencyClient;
    }

    /**
     * Define the id
     * @param id XMI ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Is a class a specification?
     * @param isSpecification
     */
    public void setIsSpecification(boolean isSpecification) {
        this.isSpecification = isSpecification;
    }

    /**
     * Get the supplier
     * @return Parent class
     */
    public UMLDependencySupplier getDependencySupplier() {
        return dependencySupplier;
    }

    /**
     * Define the supplier
     * @param dependencySupplier Parent class
     */
    public void setDependencySupplier(UMLDependencySupplier dependencySupplier) {
        this.dependencySupplier = dependencySupplier;
    }
   
    /**
     * Get the model element stereotypes list
     * @return List of stereotypes
     */
    public UMLModelElementStereotype getModelElementStereotype() {
        return modelElementStereotype;
    }

    public void setModelElementStereotype(UMLModelElementStereotype modelElementStereotype) {
        this.modelElementStereotype = modelElementStereotype;
    }

    
    
    @Override
    public String getIdref() {
        return idref;
    }

    @Override
    public void setIdref(String refId) {
        this.idref = refId;
    }

    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        if(idref!=null) {
            sb.append("<UML:Abstraction xmi.idref = '").append(idref).append("'/>\n");
        } else {
            sb.append("<UML:Abstraction xmi.id = '").append(id).append("' isSpecification = '").append(isSpecification).append("'>\n");
            if(modelElementStereotype!=null) {
                sb.append("<UML:ModelElement.stereotype>\n");
                sb.append("<UML:Stereotype xmi.idref = '").append(modelElementStereotype.getUmlStereotype().getIdref()).append("'/>\n");
                sb.append("</UML:ModelElement.stereotype>\n");
            }
            if(dependencyClient != null) {
                sb.append("<UML:Dependency.client>\n");
                sb.append("<UML:Class xmi.idref = '").append(dependencyClient.getUmlclass().getIdref()).append("'/>\n");
                sb.append("</UML:Dependency.client>\n");    
            }
            if(dependencySupplier!=null) {
                sb.append("<UML:Dependency.supplier>\n");
                sb.append("<UML:Interface xmi.idref = '").append(dependencySupplier.getUmlinterface().getIdref()).append("'/>\n");
                sb.append("</UML:Dependency.supplier>\n");   
            }
            sb.append("</UML:Abstraction>\n");
        }
        return sb.toString();
    }
    
}