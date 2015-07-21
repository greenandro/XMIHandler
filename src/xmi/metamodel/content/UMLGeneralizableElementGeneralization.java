package xmi.metamodel.content;

import java.util.ArrayList;
import java.util.List;

public class UMLGeneralizableElementGeneralization {

    private List<UMLGeneralization>  generalizations;

    public UMLGeneralizableElementGeneralization() {
        generalizations = new ArrayList<>();
    }

    public List<UMLGeneralization> getGeneralizations() {
        return generalizations;
    }
    
}