package server.request;

import server.Response;

import java.util.ArrayList;
import java.util.List;

public class CoffeeRequest implements Request {

    private String protocolVersion;

    public CoffeeRequest(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public Response respond() {
        List<String> headerFields = new ArrayList<>();
        headerFields.add(protocolVersion + " 418 I'm a teapot");
        return new Response(headerFields, "I'm a teapot");
    }
}
