package server;

public class PutRequest implements Request {

    private String path;
    private String protocolVersion;

    public PutRequest(String path, String protocolVersion) {
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
        if (path.equals("/form")) {
            return new SuccessResponse(protocolVersion);
        }
        return new MethodNotAllowedResponse(protocolVersion);
    }
}