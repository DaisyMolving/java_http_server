package server.handler;

import server.request.IndexRequest;
import server.request.Request;

public class IndexHandler implements Handler {

    private final String method;
    private final String protocolVersion;

    public IndexHandler(String method, String protocolVersion) {
        this.method = method;
        this.protocolVersion = protocolVersion;
    }

    public Request send() {
        return new IndexRequest(method, protocolVersion);
    }
}
