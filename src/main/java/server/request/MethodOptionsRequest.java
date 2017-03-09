package server.request;

import server.Response;

public class MethodOptionsRequest implements Request {

    private String protocolVersion;

    public MethodOptionsRequest(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public Response respond() {
        return new Response(
                protocolVersion + " 200 OK",
                "/",
                "GET,HEAD,POST,OPTIONS,PUT",
                "",
                "",
                "",
                "");
    }

}
