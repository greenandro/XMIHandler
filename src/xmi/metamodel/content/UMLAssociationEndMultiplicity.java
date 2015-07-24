package xmi.metamodel.content;

import java.util.ArrayList;
import java.util.List;
import xmi.metamodel.interfaces.XMISerializable;

public class UMLAssociationEndMultiplicity implements XMISerializable {

    public List<UMLMultiplicity> multiplicity;

    public UMLAssociationEndMultiplicity() {
        multiplicity = new ArrayList<>();
    }

    public List<UMLMultiplicity> getMultiplicity() {
        return multiplicity;
    }    
    
    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<UML:AssociationEnd.multiplicity>\n");
        for(UMLMultiplicity m : multiplicity) {
            sb.append(m.toXmi());
        }
        sb.append("</UML:AssociationEnd.multiplicity>\n");
        return sb.toString();
    }
                        
}