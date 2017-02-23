package server;

public class BogusRequest implements Request{

    private String methodVerb;
    private String path;
    private String protocolVersion;

    public BogusRequest(String requestInput) {
        String[] splitRequest = requestInput.split("\\s+");
        this.methodVerb = splitRequest[0];
        this.path = splitRequest[1];
        this.protocolVersion = splitRequest[2];
    }
    public String getMethodVerb() {
        return null;
    }

    public String getPath() {
        return null;
    }

    public String getProtocolVersion() {
        return null;
    }

    public Response createResponse() {
        return new MethodNotAllowedResponse(protocolVersion);
    }
}
