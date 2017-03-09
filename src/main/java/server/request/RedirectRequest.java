package server.request;

import server.Response;

public class RedirectRequest implements Request {

    private String protocolVersion;

    public RedirectRequest(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public Response respond() {
        return new Response(
                protocolVersion + " 302 Found",
                "/",
                "",
                "",
                "",
                "",
                "");
    }
}
