package server.request;

import server.Response;

public class FormRequest implements Request {

    private String protocolVersion;
    private String data;

    public FormRequest(String protocolVersion, String data) {
        this.protocolVersion = protocolVersion;
        this.data = data;
    }

    public Response respond() {
        return new Response(
                protocolVersion + " 200 OK",
                "/form",
                "",
                "",
                data,
                "",
                "");
    }

}
