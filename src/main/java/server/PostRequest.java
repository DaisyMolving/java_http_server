package server;

public class PostRequest implements Request {

    private String path;
    private String protocolVersion;

    public PostRequest(String path, String protocolVersion) {
        this.path = path;
        this.protocolVersion = protocolVersion;
    }

    public Response createResponse() {
        if (path.equals("/form")) {
            return new SuccessResponse(protocolVersion);
        }
            return new MethodNotAllowedResponse(protocolVersion);
    }
}
