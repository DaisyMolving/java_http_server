package server.handler;

import server.request.MethodNotAllowedRequest;
import server.request.Request;
import server.request.FileRequest;

import java.util.HashMap;

public class FileHandler implements Handler {

    private final String method;
    private final String protocolVersion;
    private final String fileName;
    private final String range;
    private final String requestBody;
    private final String ifMatch;

    public FileHandler(HashMap<String, String> requestParameters) {
        this.method = requestParameters.get("Method");
        this.fileName = getFileName(requestParameters.get("Path"));
        this.protocolVersion = requestParameters.get("Protocol Version");
        this.range = requestParameters.get("Range");
        this.requestBody = requestParameters.get("Request Body");
        this.ifMatch = requestParameters.get("If Match");
    }

    public Request send() {
        if (method.equals("GET")) {
            return new FileRequest(protocolVersion, fileName, range, ifMatch, requestBody);
        } else if (method.equals("PATCH")) {
            return new FileRequest(protocolVersion, fileName, range, ifMatch, requestBody);
        } return new MethodNotAllowedRequest(protocolVersion);
    }

    public String getFileName(String path) {
        return path.replace("/", "");
    }

}