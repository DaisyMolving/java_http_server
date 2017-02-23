package server;

public class PutRequest implements Request {

    private String methodVerb;
    private String path;
    private String protocolVersion;

    public PutRequest(String requestInput) {
        String[] splitRequest = requestInput.split("\\s+");
        this.methodVerb = splitRequest[0];
        this.path = splitRequest[1];
        this.protocolVersion = splitRequest[2];
    }

    public String getMethodVerb() {
        return methodVerb;
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
        return new NotFoundResponse(protocolVersion);
    }
}