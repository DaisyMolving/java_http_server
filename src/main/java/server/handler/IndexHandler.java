package server.handler;

import server.request.IndexRequest;
import server.request.MethodNotAllowedRequest;
import server.request.Request;

import java.util.HashMap;

public class IndexHandler implements Handler {

    private final String method;
    private final String protocolVersion;

    public IndexHandler(HashMap<String, String> requestParameters) {
        this.method = requestParameters.get("Method");
        this.protocolVersion = requestParameters.get("Protocol Version");
    }

    public Request send() {
        if (methodExists()) {
            return new IndexRequest(protocolVersion);
        } return new MethodNotAllowedRequest(protocolVersion);
    }

    private boolean methodExists() {
        String possibleMethods = "GET, PUT, POST, OPTIONS, HEAD, DELETE";
        return (possibleMethods.contains(method));
    }
}