package server.request;

import server.RequestLogStore;
import server.Response;

import java.io.IOException;
import java.util.Base64;

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
        System.out.println(credentials);
        String startLine = "";
        byte[] fileData = "".getBytes();
        if (credentialsAreValid(credentials)) {
            System.out.println("authorized");
            startLine = protocolVersion + " 200 OK";
            fileData = requestLogStore.read().getBytes();
        } else {
            System.out.println("unauthorized");
            startLine = protocolVersion + " 401 Unauthorized";
        }
        return new Response(
                startLine,
                "",
                "",
                "",
                fileData);
    }

    private boolean credentialsAreValid(String credentials) {
        if (credentials != null) {
            return new String(Base64.getDecoder().decode(credentials)).equals("admin:hunter2");
        } else return false;
    }

}
