package server.request;

import server.Response;

import java.io.IOException;

public class MethodOptionsRequest implements Request {

    private String protocolVersion;

    public MethodOptionsRequest(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public Response respond() throws IOException {
        return new Response(
                protocolVersion + " 200 OK",
                "/",
                "GET,HEAD,POST,OPTIONS,PUT",
                "",
                "".getBytes());
    }

}
