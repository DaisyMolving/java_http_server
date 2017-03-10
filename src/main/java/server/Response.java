package server;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Response {

    private String requestRange;
    private String acceptRange;
    public String startLine;
    private URI location;
    private String allow = "";
    private String textContent = "";
    private String contentType = "";
    private String contentPath = "";
    private String fileName = "";

    public Response(String startLine, String location, String allow, String requestRange, String contentType, String textContent, String contentPath, String fileName) throws IOException {
        this.startLine = startLine;
        this.location = URI.create("http://localhost:5000" + location);
        this.acceptRange = "bytes";
        this.requestRange = requestRange;
        this.allow = allow;
        this.textContent = textContent;
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
        if (!requestRange.equals("")) {
            int start = getRange(bodyLength)[0];
            int end = getRange(bodyLength - 1)[1];
            body = Arrays.copyOfRange(body, start, end + 1);
            bodyLength = body.length;
        }
        byte[] content = new byte[headLength + bodyLength];
        System.arraycopy(head, 0, content, 0,  headLength);
        System.arraycopy(body, 0, content, headLength, bodyLength);
        return content;
    }

    private byte[] createHead() {
        return (startLine + "\n" +
                "Allow: " + allow + "\n" +
                "Accept-Range: " + acceptRange + "\n" +
                "Content-Type: " + contentType + "\n" +
                "Location: " + location + "\n" +
                "Content-Range: " + "" + "\n" +
                "\n").getBytes();
    }

    private byte[] createBody() throws IOException {
        if (contentPath.isEmpty()) {
            return textContent.getBytes();
        } else {
            Path path = Paths.get(contentPath, fileName);
            byte[] data = Files.readAllBytes(path);
                return data;
        }
    }

    public int[] getRange(int bodyLength) {
        char[] rangeAsChars = getRangeAsCharacters();
        int[] byteRange = new int[2];
        if (rangeAsChars[rangeAsChars.length - 1] == '-') {
            byteRange[0] = beginningOfByteRange();
            byteRange[1] = bodyLength;
        } else if (rangeAsChars[0] == '-') {
            byteRange[0] = bodyLength - endOfByteRange();
            byteRange[1] = bodyLength;
        } else {
            byteRange[0] = beginningOfByteRange();
            byteRange[1] = endOfByteRange();
        } return byteRange;
    }

    private int endOfByteRange() {
        char[] rangeAsChars = getRangeAsCharacters();
        StringBuilder afterDash = new StringBuilder();
        for (int i = rangeAsChars.length - 1; i >= 0; i--) {
            if (rangeAsChars[i] == '-') {
                break;
            } afterDash.append(rangeAsChars[i]);
        } return Integer.valueOf(afterDash.reverse().toString());
    }

    private int beginningOfByteRange() {
        char[] rangeAsChars = getRangeAsCharacters();
        StringBuilder beforeDash = new StringBuilder();
        for(char character : rangeAsChars) {
            if (character == '-') {
                break;
            } beforeDash.append(character);
        }
        if (!beforeDash.toString().equals("")) {
            return Integer.valueOf(beforeDash.toString());
        } return 0;
    }

    private char[] getRangeAsCharacters() {
        String[] splitRange = requestRange.split("=");
        String numberRange = splitRange[1];
        return numberRange.toCharArray();
    }
}
