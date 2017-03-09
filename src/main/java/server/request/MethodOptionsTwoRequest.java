package server.request;

import server.Response;

public class MethodOptionsTwoRequest implements Request {

    private String protocolVersion;

    public MethodOptionsTwoRequest(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public Response respond() {
        return new Response(
                protocolVersion + " 200 OK",
                "/",
                "GET,OPTIONS",
                "",
                "",
                "",
                "");
    }
}
