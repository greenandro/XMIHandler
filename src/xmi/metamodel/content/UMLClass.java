package xmi.metamodel.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xmi.metamodel.interfaces.XMIReferenceable;
import xmi.metamodel.interfaces.XMISerializable;

public class UMLClass implements XMIReferenceable, XMISerializable {

    private String idref;
    
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

    public UMLClass(String idref) {
        this.idref = idref;
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

    @Override
    public String getIdref() {
        return idref;
    }

    @Override
    public void setIdref(String refId) {
        this.idref = refId;
    }
    
    /**
     * Change the class ID
     * @param currentid
     * @param newId 
     */
    public void changeId(String currentid, String newId) {
        if(idref!=null && idref.equals(currentid)) {
            idref = newId;
        } else if(id!=null && id.equals(currentid)) {
            id = newId;
        }
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
        StringBuilder sb = new StringBuilder();
        if(idref!=null) {
            sb.append("<UML:Class xmi.idref = '").append(idref).append("'/>\n");
        } else {
            sb.append("<UML:Class xmi.id = '").append(id)
                    .append("' name = '").append(name)
                    .append("' visibility = '").append(visibility)
                    .append("' isSpecification = '").append(isSpecification).append("' isRoot = '").append(isRoot)
                    .append("' isLeaf = '").append(isLeaf)
                    .append("' isAbstract = '").append(isAbstract).append("' isActive = '").append(isActive).append("'>\n");
            for(UMLGeneralizableElementGeneralization g : generalizableElementGeneralizations) {
                sb.append(g.toXmi());
            }
            for(UMLModelElementClientDependency d : modelElementClientDependency) {
                sb.append(d.toXmi());
            }
            sb.append(classifierFeature.toXmi());
            sb.append("</UML:Class>\n");
        }
        return sb.toString();
    }

    @Override
    public String toEcore() {
        StringBuilder sb = new StringBuilder();
        classifierFeature.getOperations().forEach((UMLOperation a) -> {
            sb.append(a.toEcore()).append("\n");
        });
        classifierFeature.getAttributes().forEach((UMLAttribute a) -> {
            sb.append(a.toEcore()).append("\n");
        });
        return sb.toString();
    }
    
    
    
    
}
