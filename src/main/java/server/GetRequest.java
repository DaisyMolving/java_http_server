package server;

import java.util.HashMap;

public class GetRequest implements Request {

    private String path;
    private String protocolVersion;
    private final HashMap<String, Response> pathToResponse = new HashMap<>();

    public GetRequest(String path, String protocolVersion) {
        this.path = path;
        this.protocolVersion = protocolVersion;

        pathToResponse.put("/", new SuccessResponse(protocolVersion));
        pathToResponse.put("/file1", new SuccessResponse(protocolVersion));
        pathToResponse.put("/text-file.txt", new SuccessResponse(protocolVersion));
        pathToResponse.put("/redirect", new RedirectResponse(protocolVersion));
        pathToResponse.put("/tea", new SuccessResponse(protocolVersion));
        pathToResponse.put("/coffee", new TeapotResponse(protocolVersion));
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