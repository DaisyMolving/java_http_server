package server.handler;

import server.request.MethodNotAllowedRequest;
import server.request.Request;
import server.request.MethodOptionsTwoRequest;

import java.util.HashMap;

public class MethodOptionsTwoHandler implements Handler {

    private final String method;
    private final String protocolVersion;

    public MethodOptionsTwoHandler(HashMap<String, String> requestParameters) {
        this.method = requestParameters.get("Method");
        this.protocolVersion = requestParameters.get("Protocol Version");
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
