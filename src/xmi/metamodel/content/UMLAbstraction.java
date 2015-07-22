package xmi.metamodel.content;

import java.util.ArrayList;
import java.util.List;

public class UMLAbstraction {

    private String idref;
    private String id;
    private boolean isSpecification;
    private UMLDependencyClient dependencyClient;
    private List<UMLModelElementStereotype>  modelElementStereotypes;
    private List<UMLDependencySupplier>  dependencySupplier;

    public UMLAbstraction(String idref) {
        this.idref = idref;
        this.dependencyClient = new UMLDependencyClient(null);
    }
    
    public UMLAbstraction(String id, boolean isSpecification) {
        this.id = id;
        this.isSpecification = isSpecification;
        this.dependencyClient = new UMLDependencyClient(null);
        this.modelElementStereotypes = new ArrayList<>();
        this.dependencySupplier = new ArrayList<>();
    }

    public String getIdref() {
        return idref;
    }

    public void setIdref(String idref) {
        this.idref = idref;
    }
    
    public UMLDependencyClient getDependencyClient() {
        return dependencyClient;
    }

    public String getId() {
        return id;
    }

    public boolean isIsSpecification() {
        return isSpecification;
    }

    public void setDependencyClient(UMLDependencyClient dependencyClient) {
        this.dependencyClient = dependencyClient;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIsSpecification(boolean isSpecification) {
        this.isSpecification = isSpecification;
    }

    public List<UMLDependencySupplier> getDependencySupplier() {
        return dependencySupplier;
    }

    public List<UMLModelElementStereotype> getModelElementStereotypes() {
        return modelElementStereotypes;
    }
    
    

}