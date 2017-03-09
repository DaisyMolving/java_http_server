package server.handler;

import server.DataStore;
import server.request.BogusRequest;
import server.request.FormRequest;
import server.request.Request;

public class FormHandler implements Handler {

    private final String method;
    private final String protocolVersion;
    private final String requestBody;
    private final DataStore dataStore;

    public FormHandler(String method, String protocolVersion, String requestBody, DataStore dataStore) {
        System.out.println(method + ": " + requestBody);
        this.method = method;
        this.protocolVersion = protocolVersion;
        this.requestBody = requestBody;
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
        } return new BogusRequest(protocolVersion);
    }

}
