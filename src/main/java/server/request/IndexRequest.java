package server.request;

import server.Response;

public class IndexRequest implements Request {

    private String protocolVersion;
    private String method;

    public IndexRequest(String method, String protocolVersion) {
        this.method = method;
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
