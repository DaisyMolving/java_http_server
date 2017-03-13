package server.handler;

import server.DataStore;
import server.request.CookieRequest;
import server.request.MethodNotAllowedRequest;
import server.request.Request;

import java.util.HashMap;

public class CookieHandler implements Handler{

    private String path;
    private String method;
    private String protocolVersion;
    private DataStore dataStore;

    public CookieHandler(HashMap<String, String> requestParameters, DataStore dataStore) {
        this.path = requestParameters.get("Path");
        this.method = requestParameters.get("Method");
        this.protocolVersion = requestParameters.get("Protocol Version");
        this.dataStore = dataStore;
    }

    public Request send() {
        if (method.equals("GET")) {
            return new CookieRequest(protocolVersion, isolateCookie(), dataStore);
        } return new MethodNotAllowedRequest(protocolVersion);
    }

    private String isolateCookie() {
        if (pathContainsParameterValues()) {
            return getParameterToStore();
        } return "";
    }

    private boolean pathContainsParameterValues() {
        return path.contains("?");
    }

    private String getParameterToStore() {
        String numberRange = "";
        int start = path.indexOf("=") + 1;
        int sizeFromStart = path.length() - start;
        return numberRange.valueOf(path.toCharArray(), start, sizeFromStart);
    }
}
