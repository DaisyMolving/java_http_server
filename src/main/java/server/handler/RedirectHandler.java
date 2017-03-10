package server.handler;

import server.request.BogusRequest;
import server.request.MethodNotAllowedRequest;
import server.request.RedirectRequest;
import server.request.Request;

import java.util.HashMap;

public class RedirectHandler implements Handler {

    private final String method;
    private final String protocolVersion;

    public RedirectHandler(HashMap<String, String> requestParameters) {
        this.method = requestParameters.get("Method");
        this.protocolVersion = requestParameters.get("Protocol Version");
    }

    public Request send() {
        if (method.equals("GET")) {
            return new RedirectRequest(protocolVersion);
        } else if (methodExists()) {
            return new BogusRequest(protocolVersion);
        } return new MethodNotAllowedRequest(protocolVersion);
    }

    private boolean methodExists() {
        String possibleMethods = "GET, PUT, POST, OPTIONS, HEAD, DELETE";
        return (possibleMethods.contains(method));
    }
}

