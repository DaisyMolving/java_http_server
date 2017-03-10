package server.handler;

import server.request.BogusRequest;
import server.request.MethodNotAllowedRequest;
import server.request.ParameterRequest;
import server.request.Request;

import java.util.HashMap;

public class ParameterHandler implements Handler {

    private final String method;
    private final String protocolVersion;
    private final String urlParameters;

    public ParameterHandler(HashMap<String, String> requestParameters) {
        this.method = requestParameters.get("Method");
        this.urlParameters = getURLParametersFromPath(requestParameters.get("Path"));
        this.protocolVersion = requestParameters.get("Protocol Version");
    }

    public Request send() {
        if (method.equals("GET")) {
            return new ParameterRequest(protocolVersion, urlParameters);
        } else if (methodExists()) {
            return new BogusRequest(protocolVersion);
        } return new MethodNotAllowedRequest(protocolVersion);
    }

    public String getURLParametersFromPath(String path) {
        String numberRange = "";
        int start = path.indexOf("?") + 1;
        int sizeFromStart = path.length() - start;
        return numberRange.valueOf(path.toCharArray(), start, sizeFromStart);
    }

    private boolean methodExists() {
        String possibleMethods = "GET, PUT, POST, OPTIONS, HEAD, DELETE";
        return (possibleMethods.contains(method));
    }
}
