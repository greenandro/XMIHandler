package xmi.metamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xmi.metamodel.content.UMLAbstraction;
import xmi.metamodel.content.UMLAssociation;
import xmi.metamodel.content.UMLAssociationEndParticipant;
import xmi.metamodel.content.UMLClass;
import xmi.metamodel.content.UMLInterface;
import xmi.metamodel.content.UMLModel;
import xmi.metamodel.content.UMLOperation;
import xmi.metamodel.interfaces.XMISerializable;

public class XMIContent implements XMISerializable {

    /** The UML models. Currently only class diagrams are supported */
    private final List<UMLModel>  models;

    public XMIContent() {
        models = new ArrayList<>();
    }
    
    public List<UMLModel> getModels() {
        return models;
    }

    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<XMI.content>\n");
        for(UMLModel m : models) {
            sb.append(m.toXmi());
        }
        sb.append("</XMI.content>\n");
        return sb.toString();
    }

    @Override
    public String toEcore() {
        StringBuilder sb = new StringBuilder();
        for(UMLModel m : models) {
            for(UMLClass c : m.getNamespaceOwnedElement().getClasses()) {
                sb.append("<eClassifiers xsi:type=\"ecore:EClass\" name=\"").append(c.getName()).append("\"");
                //todo: abstraction
                //eSuperTypes="#//TheInterface"
                
                
                c.getModelElementClientDependency().forEach(mdc -> {
                    UMLAbstraction abs = mdc.getUmlAbstraction();
                    if(abs.getDependencySupplier() != null) {
                        String idi = abs.getDependencySupplier().getUmlinterface().getIdref();
                        UMLInterface sup = models.get(0).getNamespaceOwnedElement().getInterfaces().stream().filter(ti -> ti.getId().equals(idi)).findFirst().get();
                        sb.append(" eSuperTypes=\"#//").append(sup.getName()).append("\"");
                        System.out.println("appended!");
                    }
                });
                
                
                
                
                sb.append(" >\n");
                sb.append(c.toEcore());
                for(UMLAssociation a : m.getNamespaceOwnedElement().getAssociations()) {
                    UMLAssociationEndParticipant p1 = a.getAssociationConnection().getAssociationEnd1().getAssociationEndParticipants();
                    UMLAssociationEndParticipant p2 = a.getAssociationConnection().getAssociationEnd2().getAssociationEndParticipants();
                                        
                    if(p1.getUmlClass()!=null && p1.getUmlClass().getIdref().equals(c.getId())) {
                        String aname = a.getName();
                        String cname = "";
                        if(p2.getUmlClass()!=null) {
                            UMLClass c2 = models.get(0).getNamespaceOwnedElement().getClasses().stream().filter(tc -> tc.getId().equals(p2.getUmlClass().getIdref())).findFirst().get();
                            cname = c2.getName();
                        } else if(p2.getUmlInterface()!=null && p1.getUmlClass().getIdref().equals(c.getId())) {
                            UMLInterface c2 = models.get(0).getNamespaceOwnedElement().getInterfaces().stream().filter(tc -> tc.getId().equals(p2.getUmlClass().getId())).findFirst().get();
                            cname = c2.getName();
                        }
                        if(aname.isEmpty()) {
                            aname = cname.toLowerCase();
                        }
                        
                        sb.append("<eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"").append(aname).append("\" eType=\"#//").append(cname).append("\"/>");
                    }
                }
                sb.append("</eClassifiers>");
            }
            
            for(UMLInterface i : m.getNamespaceOwnedElement().getInterfaces()) {
                sb.append("<eClassifiers xsi:type=\"ecore:EClass\" name=\"").append(i.getName()).append("\" abstract=\"true\" interface=\"true\">");
                for(UMLOperation o : i.getClassifierFeature().getOperations()) {
                    sb.append(o.toEcore());
                }
                sb.append("</eClassifiers>");                
            }
            
        }
        return sb.toString();
    }
}