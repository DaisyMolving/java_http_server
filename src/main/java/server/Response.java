package server;

import java.io.IOException;
import java.net.URI;

public class Response {

    public String startLine;
    private URI location;
    private String allow = "";
    private String contentType = "";
    public byte[] bodyContent = "".getBytes();

    public Response(String startLine, String location, String allow, String contentType, byte[] bodyContent) {
        this.startLine = startLine;
        this.location = URI.create("http://localhost:5000" + location);
        this.allow = allow;
        this.contentType = contentType;
        this.bodyContent = bodyContent;
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
        return (startLine + "\n" +
                "Allow: " + allow + "\n" +
                "Content-Type: " + contentType + "\n" +
                "Location: " + location + "\n" +
                "Set-Cookie: data=hello" + "\n" +
                "WWW-Authenticate: Basic realm=\"localhost:5000\"" + "\n" +
                "\n").getBytes();
    }

    private byte[] createBody() throws IOException {
        return bodyContent;
    }
}
