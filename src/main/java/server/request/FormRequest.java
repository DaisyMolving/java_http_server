package server.request;

import server.Response;

import java.util.ArrayList;
import java.util.List;

public class FormRequest implements Request {

    private String protocolVersion;
    private String data;

    public FormRequest(String protocolVersion, String data) {
        this.protocolVersion = protocolVersion;
        this.data = data;
    }

    public Response respond() {
        List<String> headerFields = new ArrayList<>();
        headerFields.add(protocolVersion + " 200 OK");
        return new Response(headerFields, data.getBytes());
    }

}
