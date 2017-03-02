package server;

import java.io.IOException;
import java.util.HashMap;

public class GetRequest implements Request {

    private String path;
    private String protocolVersion;
    private static final HashMap<String, Response> pathToResponse = new HashMap<>();


    public GetRequest(String path, String protocolVersion) throws IOException {
        this.path = path;
        this.protocolVersion = protocolVersion;
        populatePathToResponse();
    }

    public Response createResponse() {
        if (pathToResponse.containsKey(path)) {
            return pathToResponse.get(path);
        } return new NotFoundResponse(protocolVersion);
    }

    private void populatePathToResponse() throws IOException{
        pathToResponse.put("/", new SuccessResponse(protocolVersion));
        pathToResponse.put("/file1", new SuccessResponse(protocolVersion));
        pathToResponse.put("/redirect", new RedirectResponse(protocolVersion));
        pathToResponse.put("/tea", new SuccessResponse(protocolVersion));
        pathToResponse.put("/coffee", new TeapotResponse(protocolVersion));
        pathToResponse.put("/text-file.txt", new SuccessResponse(protocolVersion, path));
        pathToResponse.put("/image.jpeg", new SuccessResponse(protocolVersion, path));
        pathToResponse.put("/image.png", new SuccessResponse(protocolVersion, path));
        pathToResponse.put("/image.gif", new SuccessResponse(protocolVersion, path));

    }

}