package server.handler;

import server.request.MethodNotAllowedRequest;
import server.request.Request;
import server.request.FileRequest;

public class FileHandler implements Handler {

    private final String method;
    private final String protocolVersion;
    private final String fileName;

    public FileHandler(String method, String protocolVersion, String path) {
        this.method = method;
        this.protocolVersion = protocolVersion;
        this.fileName = getFileName(path);
    }

    public Request send() {
        if (method.equals("GET")) {
            return new FileRequest(protocolVersion, fileName);
        } return new MethodNotAllowedRequest(protocolVersion);
    }

    public String getFileName(String path) {
        return path.replace("/", "");
    }

}