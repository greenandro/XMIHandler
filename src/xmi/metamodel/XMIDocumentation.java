package xmi.metamodel;

import xmi.metamodel.interfaces.XMISerializable;

public class XMIDocumentation implements XMISerializable {

    private String exporter;
    private String exporterVersion;

    public XMIDocumentation() {
    }
    
    public XMIDocumentation(String exporter, String exporterVersion) {
        this.exporter = exporter;
        this.exporterVersion = exporterVersion;
    }

    public String getExporter() {
        return exporter;
    }

    public void setExporter(String exporter) {
        this.exporter = exporter;
    }

    public String getExporterVersion() {
        return exporterVersion;
    }

    public void setExporterVersion(String exporterVersion) {
        this.exporterVersion = exporterVersion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toXmi() {
        StringBuilder sb = new StringBuilder();
        sb.append("<XMI.documentation>\n");
        sb.append("<XMI.exporter>").append(exporter).append("</XMI.exporter>");
        sb.append("<XMI.exporterVersion>").append(exporterVersion).append("</XMI.exporterVersion>");
        sb.append("</XMI.documentation>");
        return sb.toString();
    }
  
}