package server.request;

import server.Response;

public class CoffeeRequest implements Request {

    private String protocolVersion;

    public CoffeeRequest(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public Response respond() {
        return new Response(
                protocolVersion + " 418 I'm a teapot",
                "",
                "",
                "",
                "I'm a teapot",
                "",
                "");
    }
}
