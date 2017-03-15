package server.request;

import server.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BogusRequest implements Request {

    private String protocolVersion;

    public BogusRequest(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public Response respond() {
        List<String> headerFields = new ArrayList<>();
        headerFields.add(protocolVersion + " 404 Not Found");
        byte[] bodyContent = "404 Not Found".getBytes();
        return new Response(headerFields, bodyContent);
    }
}
