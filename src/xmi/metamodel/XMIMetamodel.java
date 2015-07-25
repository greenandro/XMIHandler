package xmi.metamodel;

import xmi.metamodel.interfaces.XMISerializable;


public class XMIMetamodel implements XMISerializable {

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

    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<XMI.metamodel xmi.name=\"").append(xmiName).append("\" xmi.version=\"").append(xmiVersion).append("\"/>\n");
        return sb.toString();
    }
    
}