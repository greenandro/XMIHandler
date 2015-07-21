package xmi.metamodel;


public class XMIMetamodel {

    private String xmiName;
    private String xmiVersion;

    public XMIMetamodel() {
    }

    public XMIMetamodel(String xmiName, String xmiVersion) {
        this.xmiName = xmiName;
        this.xmiVersion = xmiVersion;
    }

    public String getXmiName() {
        return xmiName;
    }

    public void setXmiName(String xmiName) {
        this.xmiName = xmiName;
    }

    public String getXmiVersion() {
        return xmiVersion;
    }

    public void setXmiVersion(String xmiVersion) {
        this.xmiVersion = xmiVersion;
    }
  
}