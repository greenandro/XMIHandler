package xmi.metamodel.content;

import java.util.ArrayList;
import java.util.List;

public class UMLAbstraction {

    private String id;
    private boolean isSpecification;
    private UMLDependencyClient dependencyClient;
    private List<UMLModelElementStereotype>  modelElementStereotypes;
    private List<UMLDependencySupplier>  dependencySupplier;

    public UMLAbstraction(String id, boolean isSpecification, UMLDependencyClient dependencyClient) {
        this.id = id;
        this.isSpecification = isSpecification;
        this.dependencyClient = dependencyClient;
        this.modelElementStereotypes = new ArrayList<>();
        this.dependencySupplier = new ArrayList<>();
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

}