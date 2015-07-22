package xmi.metamodel.content;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xmi.metamodel.interfaces.XMISerializable;
import xmi.metamodel.interfaces.XMIReferenceable;

public class UMLClass implements XMISerializable, XMIReferenceable {

    private String refId;
    
    private String id;
    private String name;
    private String visibility;
    private boolean isSpecification;
    private boolean isRoot;
    private boolean isLeaf;
    private boolean isAbstract;
    private boolean isActive;
    
    private UMLClassifierFeature classifierFeature;
    
    private List<UMLGeneralizableElementGeneralization> generalizableElementGeneralizations;
    private List<UMLModelElementClientDependency> modelElementClientDependency;
    private Map<String,String> otherProperties;

    public UMLClass(String refId) {
        this.refId = refId;
    }

    @Override
    public String getRefId() {
        return refId;
    }

    @Override
    public void setRefId(String refId) {
        this.refId = refId;
    }

    

    public UMLClass(String id, String name, String visibility, boolean isSpecification, boolean isRoot, boolean isLeaf, boolean isAbstract, boolean isActive) {
        this.id = id;
        this.name = name;
        this.visibility = visibility;
        this.isSpecification = isSpecification;
        this.isRoot = isRoot;
        this.isLeaf = isLeaf;
        this.isAbstract = isAbstract;
        this.isActive = isActive;
        this.otherProperties = new HashMap<>();
        this.generalizableElementGeneralizations = new ArrayList<>();
        this.modelElementClientDependency = new ArrayList<>();
        this.classifierFeature = new UMLClassifierFeature();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public boolean isIsSpecification() {
        return isSpecification;
    }

    public void setIsSpecification(boolean isSpecification) {
        this.isSpecification = isSpecification;
    }

    public boolean isIsRoot() {
        return isRoot;
    }

    public void setIsRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }

    public boolean isIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public boolean isIsAbstract() {
        return isAbstract;
    }

    public void setIsAbstract(boolean isAbstract) {
        this.isAbstract = isAbstract;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Map<String, String> getOtherProperties() {
        return otherProperties;
    }

    public List<UMLGeneralizableElementGeneralization> getGeneralizableElementGeneralizations() {
        return generalizableElementGeneralizations;
    }

    public List<UMLModelElementClientDependency> getModelElementClientDependency() {
        return modelElementClientDependency;
    }

    public UMLClassifierFeature getClassifierFeature() {
        return classifierFeature;
    }

    public void setClassifierFeature(UMLClassifierFeature classifierFeature) {
        this.classifierFeature = classifierFeature;
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public String toXmi() {
        return "";
    }
    
    
}
