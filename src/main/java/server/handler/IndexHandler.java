package server.handler;

import server.request.IndexRequest;
import server.request.MethodNotAllowedRequest;
import server.request.Request;

public class IndexHandler implements Handler {

    private final String method;
    private final String protocolVersion;

    public IndexHandler(String method, String protocolVersion) {
        this.method = method;
        this.protocolVersion = protocolVersion;
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