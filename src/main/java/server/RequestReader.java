package server;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestReader {

    private BufferedReader requestInput;
    private String methodVerb;
    private String path;
    private String protocolVersion;
    private String methodVerbAndPath;
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

    public String getMethodVerbAndPath() {
        return methodVerbAndPath;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    private String readBody() throws IOException {
        String[] splitRequest = splitHeader(header);
        if (hasBodyContent(header)) {
            int numberOfBytes = Integer.valueOf(splitRequest[4]);
            return readBytes(numberOfBytes);
        } return "";
    }

    private String readBytes(int numberOfBytes) throws IOException {
        char[] body = new char[numberOfBytes];
        requestInput.read(body, 0, numberOfBytes);
        return String.valueOf(body);
    }

    private void assignStartLineComponents() throws IOException {
        String[] splitRequest = splitHeader(header);
        this.methodVerb = splitRequest[0];
        this.path = splitRequest[1];
        this.protocolVersion = splitRequest[2];
        methodVerbAndPath = methodVerb + " " + path;
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
