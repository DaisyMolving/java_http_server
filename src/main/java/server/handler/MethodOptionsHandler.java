package server.handler;

import server.request.MethodNotAllowedRequest;
import server.request.MethodOptionsRequest;
import server.request.Request;

import java.util.HashMap;

public class MethodOptionsHandler implements Handler {

    private final String method;
    private final String protocolVersion;

    public MethodOptionsHandler(HashMap<String , String> requestParameters) {
        this.method = requestParameters.get("Method");
        this.protocolVersion = requestParameters.get("Protocol Version");
    }

    public Request send() {
        if (methodExists()) {
            return new MethodOptionsRequest(protocolVersion);
        } return new MethodNotAllowedRequest(protocolVersion);
    }

    private boolean methodExists() {
        String possibleMethods = "GET, PUT, POST, OPTIONS, HEAD, DELETE";
        return (possibleMethods.contains(method));
    }
}
