package server.handler;

import server.request.BogusRequest;
import server.request.FormRequest;
import server.request.Request;

public class FormHandler implements Handler {

    private final String method;
    private final String protocolVersion;
    private final String requestBody;

    public FormHandler(String method, String protocolVersion, String requestBody) {
        System.out.println(method + ": " + requestBody);
        this.method = method;
        this.protocolVersion = protocolVersion;
        this.requestBody = requestBody;
    }

    public Request send() {
        if (method.equals("GET")) {
            return new FormRequest(protocolVersion, requestBody);
        } else if (method.equals("POST")) {
            return new FormRequest(protocolVersion, requestBody);
        } else if (method.equals("PUT")) {
            return new FormRequest(protocolVersion, requestBody);
        } else if (method.equals("DELETE")) {
            return new FormRequest(protocolVersion, "");
        } return new BogusRequest(protocolVersion);
    }

}
