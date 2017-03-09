package server.handler;

import server.request.BogusRequest;
import server.request.CoffeeRequest;
import server.request.MethodNotAllowedRequest;
import server.request.Request;

public class CoffeeHandler implements Handler {

    private final String method;
    private final String protocolVersion;

    public CoffeeHandler(String method, String protocolVersion) {
        this.method = method;
        this.protocolVersion = protocolVersion;
    }

    public Request send() {
        if (method.equals("GET")) {
            return new CoffeeRequest(protocolVersion);
        } else if (methodExists()) {
            return new BogusRequest(protocolVersion);
        } return new MethodNotAllowedRequest(protocolVersion);
    }

    private boolean methodExists() {
        String possibleMethods = "GET, PUT, POST, OPTIONS, HEAD, DELETE";
        return (possibleMethods.contains(method));
    }
}
