package server.handler;

import server.request.MethodNotAllowedRequest;
import server.request.Request;
import server.request.FileRequest;

import java.io.File;

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

    private String getContentType(String fileName) {
        if (fileName.contains(".jpeg")) {
            return "image/jpeg";
        } else if (fileName.contains(".png")) {
            return "image/png";
        } else if (fileName.contains(".gif")) {
            return "image/gif";
        } return "text/plain";
    }

    private boolean fileExists(String fileName) {
        File file = new File("/Users/daisymolving/Documents/Apprenticeship/cob_spec/public/" + fileName);
        return (file.exists());
    }

}
