package server.handler;

import server.request.BogusRequest;
import server.request.Request;

public class ErrorHandler implements Handler {

    private final String protocolVersion;
    private final String method;

    public ErrorHandler(String method, String protocolVersion) {
        this.method = method;
        this.protocolVersion = protocolVersion;
    }

    public Request send() {
        return new BogusRequest(protocolVersion);
    }
}
