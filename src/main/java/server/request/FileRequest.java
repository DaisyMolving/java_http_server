package server.request;

import server.Response;

import java.io.File;
import java.io.IOException;

public class FileRequest implements Request {

    private final String protocolVersion;
    private final String fileName;
    private final String contentRange;

    public FileRequest(String protocolVersion, String fileName, String contentRange) {
        this.protocolVersion = protocolVersion;
        this.fileName = fileName;
        this.contentRange = contentRange;
    }

    public Response respond() throws IOException {
        if (fileExists(fileName) && contentRange == null) {
            return new Response(
                    protocolVersion + " 200 OK",
                    fileName,
                    "",
                    "",
                    getContentType(fileName),
                    "",
                    "/Users/daisymolving/Documents/Apprenticeship/cob_spec/public/",
                    fileName);
        } else if (fileExists(fileName) && contentRange !=null) {
            return new Response(
                    protocolVersion + " 206 Partial Content",
                    fileName,
                    "",
                    contentRange,
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
