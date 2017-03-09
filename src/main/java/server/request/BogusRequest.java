package server.request;

import server.Response;

public class BogusRequest implements Request {

    private String protocolVersion;

    public BogusRequest(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public Response respond() {
        return new Response(
                protocolVersion + " 404 Not Found",
                "",
                "",
                "",
                "",
                "",
                "");
    }
}
