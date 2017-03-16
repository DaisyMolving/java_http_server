package server.request;

import server.Response;

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
        return new Response(headerFields, "404 Not Found");
    }
}
