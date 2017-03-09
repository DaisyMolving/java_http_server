package server.handler;

import server.request.BogusRequest;
import server.request.MethodNotAllowedRequest;
import server.request.Request;

public class ErrorHandler implements Handler {

    private final String protocolVersion;
    private final String method;

    public ErrorHandler(String method, String protocolVersion) {
        this.method = method;
        this.protocolVersion = protocolVersion;
    }

    public Request send() {
        if (methodExists()) {
            return new BogusRequest(protocolVersion);
        } return new MethodNotAllowedRequest(protocolVersion);
    }

    private boolean methodExists() {
        String possibleMethods = "GET, PUT, POST, OPTIONS, HEAD, DELETE";
        return (possibleMethods.contains(method));
    }
}
