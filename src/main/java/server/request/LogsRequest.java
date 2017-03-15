package server.request;

import server.RequestLogStore;
import server.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class LogsRequest implements Request {

    private final String protocolVersion;
    private final String credentials;
    private final RequestLogStore requestLogStore;

    public LogsRequest(String protocolVersion, String credentials, RequestLogStore requestLogStore) {
        this.protocolVersion = protocolVersion;
        this.credentials = credentials;
        this.requestLogStore = requestLogStore;
    }

    public Response respond() throws IOException {

        List<String> headerFields = new ArrayList<>();
        byte[] bodyContent;

        if (credentialsAreValid(credentials)) {

            headerFields.add(protocolVersion + " 200 OK");
            bodyContent = requestLogStore.read().getBytes();

        } else {

            headerFields.add(protocolVersion + " 401 Unauthorized");
            headerFields.add("WWW-Authenticate: Basic realm=\"My Server\"");
            bodyContent = "".getBytes();
        }

        return new Response(headerFields, bodyContent);
    }

    private boolean credentialsAreValid(String credentials) {
        if (credentials != null) {
            return new String(Base64.getDecoder().decode(credentials)).equals("admin:hunter2");
        } else return false;
    }

}
