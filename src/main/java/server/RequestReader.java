package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RequestReader {

    private BufferedReader requestInput;
    private String method;
    private String path;
    private String protocolVersion;
    private String header;
    private String body;

    public RequestReader(BufferedReader requestInput) throws IOException {
        this.requestInput = requestInput;
        this.header = readHeader();
        assignStartLineComponents();
        this.body = readBody();
    }

    public String getBody() {
        return body;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    private String readBody() throws IOException {
        String[] splitRequest = splitHeader(header);
        if (hasBodyContent(header)) {
            return readBytes(numberOfBytes(splitRequest));
        } return "";
    }

    private String readBytes(int numberOfBytes) throws IOException {
        char[] body = new char[numberOfBytes];
        requestInput.read(body, 0, numberOfBytes);
        return String.valueOf(body);
    }

    private int numberOfBytes(String[] splitRequest) {
        List<String> componentsList = Arrays.asList(splitRequest);
        int indexOfByteAmount = componentsList.indexOf("Content-Length:") + 1;
        return Integer.valueOf(splitRequest[indexOfByteAmount]);
    }

    private void assignStartLineComponents() throws IOException {
        String[] splitRequest = splitHeader(header);
        this.method = splitRequest[0];
        this.path = splitRequest[1];
        this.protocolVersion = splitRequest[2];
    }

    private String readHeader() throws IOException {
        StringBuilder buffer = new StringBuilder();
        String line;
        while(!(line = requestInput.readLine()).equals("")) {
            buffer.append(line + "\n");
        } return buffer.toString();
    }

    private String[] splitHeader(String header) throws IOException {
        return header.split("\\s+");
    }

    private boolean hasBodyContent(String header) throws IOException {
        return header.contains("Content-Length");
    }
}
