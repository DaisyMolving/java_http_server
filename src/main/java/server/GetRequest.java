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
        pathToResponse.put("/redirect", new RedirectResponse(protocolVersion));
        pathToResponse.put("/tea", new SuccessResponse(protocolVersion));
        pathToResponse.put("/coffee", new TeapotResponse(protocolVersion));
        pathToResponse.put("/text-file.txt", new SuccessResponse(protocolVersion, path));
        pathToResponse.put("/image.jpeg", new SuccessResponse(protocolVersion, path));
        pathToResponse.put("/image.png", new SuccessResponse(protocolVersion, path));
        pathToResponse.put("/image.gif", new SuccessResponse(protocolVersion, path));
//        pathToResponse.put("/method_options", new SuccessResponse(protocolVersion, "/method_options"));
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