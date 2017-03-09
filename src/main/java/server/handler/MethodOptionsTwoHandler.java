package server.handler;

import server.request.MethodNotAllowedRequest;
import server.request.Request;
import server.request.MethodOptionsTwoRequest;

public class MethodOptionsTwoHandler implements Handler {

    private final String method;
    private final String protocolVersion;

    public MethodOptionsTwoHandler(String method, String protocolVersion) {
        this.method = method;
        this.protocolVersion = protocolVersion;
    }

    public Request send() {
        if (methodExists()) {
            return new MethodOptionsTwoRequest(protocolVersion);
        } return new MethodNotAllowedRequest(protocolVersion);
    }

    private boolean methodExists() {
        String possibleMethods = "GET, PUT, POST, OPTIONS, HEAD, DELETE";
        return (possibleMethods.contains(method));
    }
}
