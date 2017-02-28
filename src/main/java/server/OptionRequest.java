package server;

public class OptionRequest implements Request {

    private String path;
    private String protocolVersion;

    public OptionRequest(String path, String protocolVersion) {
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
        return new SuccessResponse(protocolVersion, path);
    }
}