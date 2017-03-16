package server.request;

import server.Response;

import java.util.ArrayList;
import java.util.List;

public class MethodOptionsRequest implements Request {

    private String protocolVersion;

    public MethodOptionsRequest(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public Response respond() {

        List<String> headerFields = new ArrayList<>();
        headerFields.add(protocolVersion + " 200 OK");
        headerFields.add("Allow: GET,HEAD,POST,OPTIONS,PUT");

        return new Response(headerFields, "".getBytes());
    }

}
