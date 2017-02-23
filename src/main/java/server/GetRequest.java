package server;

public class GetRequest implements Request {

    private String methodVerb;
    private String path;
    private String protocolVersion;

    public GetRequest(String requestInput) {
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
        if (path.equals("/")) {
            return new SuccessResponse(protocolVersion);
        } else if (path.equals("/file1")) {
            return new SuccessResponse(protocolVersion);
        } else if (path.equals("/text-file.txt")) {
            return new SuccessResponse(protocolVersion);
        } else if (path.equals("/redirect")) {
            return new RedirectResponse(protocolVersion);
        } else if (path.equals("/tea")) {
            return new SuccessResponse(protocolVersion);
        } else if (path.equals("/coffee")) {
            return new TeapotResponse(protocolVersion);
        } else {
            return new NotFoundResponse(protocolVersion);
        }
    }
}