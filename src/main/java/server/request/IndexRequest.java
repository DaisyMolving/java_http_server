package server.request;

import server.Response;

public class IndexRequest implements Request {

    private String protocolVersion;

    public IndexRequest(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public Response respond() {
        return new Response(
                protocolVersion + " 200 OK",
                "",
                "",
                "",
                "",
                "",
                "");
    }

}
