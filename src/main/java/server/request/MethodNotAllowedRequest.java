package server.request;

import server.Response;

public class MethodNotAllowedRequest implements Request {

    private String protocolVersion;

    public MethodNotAllowedRequest(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public Response respond() {
        return new Response(
                protocolVersion + " 405 Method Not Allowed",
                "",
                "",
                "",
                "",
                "",
                "");
    }
}
