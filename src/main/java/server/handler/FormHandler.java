package server.handler;

import server.DataStore;
import server.request.FormRequest;
import server.request.MethodNotAllowedRequest;
import server.request.Request;

import java.util.HashMap;

public class FormHandler implements Handler {

    private final String method;
    private final String protocolVersion;
    private final String requestBody;
    private final DataStore dataStore;

    public FormHandler(HashMap<String, String> requestParameters, DataStore dataStore) {
        this.method = requestParameters.get("Method");
        this.protocolVersion = requestParameters.get("Protocol Version");
        this.requestBody = requestParameters.get("Request Body");
        this.dataStore = dataStore;
    }

    public Request send() {
        if (method.equals("GET")) {
            return new FormRequest(protocolVersion, dataStore.read());
        } else if (method.equals("POST")) {
            dataStore.update(requestBody);
            return new FormRequest(protocolVersion, dataStore.read());
        } else if (method.equals("PUT")) {
            dataStore.update(requestBody);
            return new FormRequest(protocolVersion, dataStore.read());
        } else if (method.equals("DELETE")) {
            dataStore.delete();
            return new FormRequest(protocolVersion, dataStore.read());
        } return new MethodNotAllowedRequest(protocolVersion);
    }

}
