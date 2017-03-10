package server.request;

import server.Response;

import java.io.IOException;

public class MethodNotAllowedRequest implements Request {

    private String protocolVersion;

    public MethodNotAllowedRequest(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public Response respond() throws IOException {
        return new Response(
                protocolVersion + " 405 Method Not Allowed",
                "",
                "",
                "",
                "".getBytes());
    }
}
