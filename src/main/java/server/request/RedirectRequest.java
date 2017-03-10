package server.request;

import server.Response;

import java.io.IOException;

public class RedirectRequest implements Request {

    private String protocolVersion;

    public RedirectRequest(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public Response respond() throws IOException {
        return new Response(
                protocolVersion + " 302 Found",
                "/",
                "",
                "",
                "",
                "",
                "",
                "");
    }
}
