package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RequestReader {

    private BufferedReader requestInput;
    private String method;
    private String path;
    private String protocolVersion;
    private String header;
    private String body;
    private String range;

    public RequestReader(BufferedReader requestInput) throws IOException {
        this.requestInput = requestInput;
        this.header = readHeader();
        assignStartLineComponents();
        this.body = readBody();
    }

    public HashMap<String, String> getRequestParameters() {
        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("Method", method);
        requestParameters.put("Path", path);
        requestParameters.put("Protocol Version", protocolVersion);
        if (!body.equals("")) {
            requestParameters.put("Request Body", body);
        }
        requestParameters.put("Range", range);
        return requestParameters;
    }

    private String readBody() throws IOException {
        String[] splitRequest = splitHeader(header);
        if (hasBodyContent(header)) {
            int amount = Integer.valueOf(getInfo(splitRequest, "Content-Length:"));
            return readBytes(amount);
        } return "";
    }

    private String readBytes(int numberOfBytes) throws IOException {
        char[] body = new char[numberOfBytes];
        requestInput.read(body, 0, numberOfBytes);
        return String.valueOf(body);
    }

    private String getInfo(String[] splitRequest, String headerName) {
        List<String> componentsList = Arrays.asList(splitRequest);
        int indexOfByteAmount = componentsList.indexOf(headerName) + 1;
        return splitRequest[indexOfByteAmount];
    }

    private void assignStartLineComponents() throws IOException {
        String[] splitRequest = splitHeader(header);
        this.method = splitRequest[0];
        this.path = splitRequest[1];
        this.protocolVersion = splitRequest[2];
        if (header.contains("Range:")) {
            this.range = getInfo(splitRequest, "Range:");
        }
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
