package server.handler;

import server.RequestLogStore;
import server.request.LogsRequest;
import server.request.MethodNotAllowedRequest;
import server.request.Request;

import java.util.HashMap;

public class LogsHandler implements Handler {

    private String method;
    private String protocolVersion;
    private String credentials;
    private RequestLogStore requestLogStore;

    public LogsHandler(HashMap<String, String> requestParameters, RequestLogStore requestLogStore) {
        this.method = requestParameters.get("Method");
        this.protocolVersion = requestParameters.get("Protocol Version");
        this.credentials = requestParameters.get("Credentials");
        this.requestLogStore = requestLogStore;
    }

    public Request send() {
        if (method.equals("GET")) {
            return new LogsRequest(protocolVersion, credentials, requestLogStore);
        } return new MethodNotAllowedRequest(protocolVersion);
    }
}
