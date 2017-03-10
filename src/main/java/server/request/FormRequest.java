package server.request;

import server.Response;

import java.io.IOException;

public class FormRequest implements Request {

    private String protocolVersion;
    private String data;

    public FormRequest(String protocolVersion, String data) {
        this.protocolVersion = protocolVersion;
        this.data = data;
    }

    public Response respond() throws IOException {
        return new Response(
                protocolVersion + " 200 OK",
                "/form",
                "",
                "",
                "",
                data,
                "",
                "");
    }

}
