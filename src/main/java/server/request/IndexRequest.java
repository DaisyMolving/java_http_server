package server.request;

import server.Response;

import java.io.IOException;

public class IndexRequest implements Request {

    private String protocolVersion;

    public IndexRequest(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public Response respond() throws IOException {
        return new Response(
                protocolVersion + " 200 OK",
                "",
                "",
                "",
                "",
                "",
                "",
                "");
    }

}
