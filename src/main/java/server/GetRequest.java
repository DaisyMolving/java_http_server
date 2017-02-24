package server;

import java.lang.reflect.Method;
import java.util.HashMap;

public class GetRequest implements Request {

    private String methodVerb;
    private String path;
    private String protocolVersion;
    private final HashMap<String, Response> pathToResponse = new HashMap<>();

    public GetRequest(String requestInput) {
        String[] splitRequest = requestInput.split("\\s+");
        this.methodVerb = splitRequest[0];
        this.path = splitRequest[1];
        this.protocolVersion = splitRequest[2];

        pathToResponse.put("/", new SuccessResponse(protocolVersion));
        pathToResponse.put("/file1", new SuccessResponse(protocolVersion));
        pathToResponse.put("/text-file.txt", new SuccessResponse(protocolVersion));
        pathToResponse.put("/redirect", new RedirectResponse(protocolVersion));
        pathToResponse.put("/tea", new SuccessResponse(protocolVersion));
        pathToResponse.put("/coffee", new TeapotResponse(protocolVersion));
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
        if (pathToResponse.containsKey(path)) {
            return pathToResponse.get(path);
        } return new NotFoundResponse(protocolVersion);
    }
}