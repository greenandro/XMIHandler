package xmi.metamodel.content;

public class UMLModelElementClientDependency {

    private UMLAbstraction umlAbstraction;

    public UMLModelElementClientDependency(UMLAbstraction umlAbstraction) {
        this.umlAbstraction = umlAbstraction;
    }

    public UMLAbstraction getUmlAbstraction() {
        return umlAbstraction;
    }

    public void setUmlAbstraction(UMLAbstraction umlAbstraction) {
        this.umlAbstraction = umlAbstraction;
    }
  
}