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
        if (path.equals("/method_options")) {
            return new SuccessResponse(protocolVersion, "/method_options");
        } else {
            return new SuccessResponse(protocolVersion, "/method_options2");
        }
    }
}