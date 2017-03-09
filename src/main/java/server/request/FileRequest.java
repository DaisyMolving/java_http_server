package server.request;

import server.Response;

import java.io.File;

public class FileRequest implements Request {

    private final String protocolVersion;
    private final String fileName;

    public FileRequest(String protocolVersion, String fileName) {
        this.protocolVersion = protocolVersion;
        this.fileName = fileName;
    }

    public Response respond() {
        if (fileExists(fileName)) {
            return new Response(
                    protocolVersion + " 200 OK",
                    fileName,
                    "",
                    getContentType(fileName),
                    "",
                    "/Users/daisymolving/Documents/Apprenticeship/cob_spec/public/",
                    fileName);
        } return new Response(protocolVersion + " 404 Not Found");
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
