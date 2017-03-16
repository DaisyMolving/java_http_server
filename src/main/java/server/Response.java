package server;

import java.io.ByteArrayOutputStream;
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
        byte[] header = createHeader();
        byte[] body = bodyContent;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        outputStream.write(header);
        outputStream.write(body);

        return outputStream.toByteArray();
    }

    private byte[] createHeader() {
        String header = "";
        for(String field : headerFields) {
            header = header.concat(field + "\n");
        }
        header = header.concat("\n");
        return header.getBytes();
    }
}
