package server.request;

import server.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MethodNotAllowedRequest implements Request {

    private String protocolVersion;

    public MethodNotAllowedRequest(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public Response respond() throws IOException {

        List<String> headerFields = new ArrayList<>();
        headerFields.add(protocolVersion + " 405 Method Not Allowed");
        byte[] bodyContent = "".getBytes();

        return new Response(headerFields, bodyContent);
    }
}
