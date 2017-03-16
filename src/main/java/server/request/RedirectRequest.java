package server.request;

import server.Response;

import java.util.ArrayList;
import java.util.List;

public class RedirectRequest implements Request {

    private String protocolVersion;

    public RedirectRequest(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public Response respond() {


        List<String> headerFields = new ArrayList<>();
        headerFields.add(protocolVersion + " 302 Found");
        headerFields.add("Location: " + "http://localhost:5000/");

        return new Response(headerFields, "");
    }
}
