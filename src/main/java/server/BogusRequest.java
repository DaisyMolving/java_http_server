package server;

public class BogusRequest implements Request{

    private String path;
    private String protocolVersion;

    public BogusRequest(String path, String protocolVersion) {
        this.path = path;
        this.protocolVersion = protocolVersion;
    }

    public String getPath() {
        return path;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public Response createResponse() {
        return new MethodNotAllowedResponse(protocolVersion);
    }
}
