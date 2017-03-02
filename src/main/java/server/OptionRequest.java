package server;

import java.io.IOException;

public class OptionRequest implements Request {

    private String path;
    private String protocolVersion;

    public OptionRequest(String path, String protocolVersion) {
        this.path = path;
        this.protocolVersion = protocolVersion;
    }

    public Response createResponse() throws IOException {
        return new SuccessResponse(protocolVersion, path);
    }
}