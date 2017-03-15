package server;

import java.io.IOException;
import java.util.List;

public class Response {

    private List<String> headerFields;
    private byte[] bodyContent = "".getBytes();

    public Response(List<String> headerFields, byte[] bodyContent) {
        this.headerFields = headerFields;
        this.bodyContent = bodyContent;
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
        String header = "";
        for(String field : headerFields) {
            header = header.concat(field + "\n");
        }
        header = header.concat("\n");
        return header.getBytes();
    }

    private byte[] createBody() throws IOException {
        return bodyContent;
    }
}
