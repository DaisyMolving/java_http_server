package server;

import java.io.IOException;
import java.util.List;

public class Response {

    private List<String> headerFields;
    private String bodyContent = "";

    public Response(List<String> headerFields, String bodyContent) {
        this.headerFields = headerFields;
        this.bodyContent = bodyContent;
    }

    public byte[] generateContent() throws IOException {
        String head = createHead();
        String body = createBody();
        String content = head.concat(body);
        return content.getBytes();
    }

    private String createHead() {
        String header = "";
        for(String field : headerFields) {
            header = header.concat(field + "\n");
        }
        header = header.concat("\n");
        return header;
    }

    private String createBody() throws IOException {
        return bodyContent;
    }
}
