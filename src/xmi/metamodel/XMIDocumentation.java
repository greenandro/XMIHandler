package xmi.metamodel;

public class XMIDocumentation {

    private String exporter;
    private  String exporterVersion;

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
  
}