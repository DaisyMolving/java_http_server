package server.request;

import server.Response;

import java.util.ArrayList;
import java.util.List;

public class MethodOptionsTwoRequest implements Request {

    private String protocolVersion;

    public MethodOptionsTwoRequest(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public Response respond() {

        List<String> headerFields = new ArrayList<>();
        headerFields.add(protocolVersion + " 200 OK");
        headerFields.add("Allow: GET,OPTIONS");
        byte[] bodyContent = "".getBytes();

        return new Response(headerFields, bodyContent);
    }
}
