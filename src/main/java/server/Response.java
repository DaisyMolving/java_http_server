package server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Response {

    public String startLine;
    private String location = "";
    private String allow = "";
    private String contentType = "";
    private String contentPath = "";
    private String fileName = "";

    public Response(String startLine, String location, String allow, String contentType, String contentPath, String fileName) {
        this.startLine = startLine;
        this.location = location;
        this.allow = allow;
        this.contentType = contentType;
        this.contentPath = contentPath;
        this.fileName = fileName;
    }

    public Response(String startLine) {
        this.startLine = startLine;
    }

    public byte[] generateContent() throws IOException {
        byte[] head = createHead();
        byte[] body = createBody();
        int headLength = head.length;
        int bodyLength = body.length;
        byte[] content = new byte[headLength + bodyLength];
        System.arraycopy(head, 0, content, 0,  headLength);
        System.arraycopy(body, 0, content, headLength, bodyLength);
        return content;
    }

    private byte[] createHead() {
        return (startLine +
                "Location: " + location + "\n" +
                "Allow: " + allow + "\n" +
                "Content-Type: " + contentType + "\n" +
                "\n").getBytes();
    }

    private byte[] createBody() throws IOException {
        if (contentPath.isEmpty()) {
            return "I'm a teapot".getBytes();
        } else {
            Path path = Paths.get(contentPath, fileName);
            byte[] data = Files.readAllBytes(path);
            return data;
        }
    }

}
