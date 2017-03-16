package server.request;

import server.Response;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileRequest implements Request {

    private final String protocolVersion;
    private final String fileName;
    private final String contentRange;
    private final String ifMatch;
    private final String data;

    public FileRequest(String protocolVersion, String fileName, String contentRange, String ifMatch, String data) {
        this.protocolVersion = protocolVersion;
        this.fileName = fileName;
        this.contentRange = contentRange;
        this.ifMatch = ifMatch;
        this.data = data;
    }

    public Response respond() throws IOException {

        byte[] bodyContent;
        List<String> headerFields = new ArrayList<>();

        if (ifMatch != null) {

            headerFields.add(protocolVersion + " 204 No Content");
            headerFields.add("Content-Type: " + getContentType(fileName));
            bodyContent = readFile();
            patchFile(data);

        } else if (fileExists(fileName) && contentRange == null) {

            headerFields.add(protocolVersion + " 200 OK");
            headerFields.add("Content-Type: " + getContentType(fileName));
            bodyContent = readFile();

        } else if (fileExists(fileName) && contentRange !=null) {

            headerFields.add(protocolVersion + " 206 Partial Content");
            headerFields.add("Content-Type: " + getContentType(fileName));
            bodyContent = readPartialFile();

        } else {

            headerFields.add(protocolVersion + " 404 Not Found");
            bodyContent = "404 Not Found".getBytes();

        }

        return new Response(headerFields, bodyContent);
    }

    public byte[] readFile() throws IOException {
        String contentPath = "/Users/daisymolving/Documents/Apprenticeship/cob_spec/public/";
        Path path = Paths.get(contentPath, fileName);
        byte[] data = Files.readAllBytes(path);
        return data;
    }

    public byte[] readPartialFile() throws IOException {
        int length = readFile().length;
        int start = getRange(contentRange, length)[0];
        int end = getRange(contentRange, length - 1)[1];
        return Arrays.copyOfRange(readFile(), start, end + 1);
    }

    public void patchFile(String data) throws IOException {
        String contentPath = "/Users/daisymolving/Documents/Apprenticeship/cob_spec/public/";
        Path path = Paths.get(contentPath, fileName);
        Charset charset = StandardCharsets.UTF_8;
        String content = new String(Files.readAllBytes(path), charset);
        content = content.replaceAll(new String(readFile()), data);
        Files.write(path, content.getBytes(charset));
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

    private int[] getRange(String contentRange, int bodyLength) {
        String numberRange = isolateNumberRange(contentRange);
        int[] byteRange = new int[2];
        int beginning = Integer.valueOf(beginningOfByteRange(numberRange));
        int end = Integer.valueOf(endOfByteRange(numberRange));
        if (numberRange.endsWith("-")) {
            return setBeginningAndEndOfRange(byteRange, beginning, bodyLength);
        } else if (numberRange.startsWith("-")) {
            return setBeginningAndEndOfRange(byteRange, bodyLength - end, bodyLength);
        } return setBeginningAndEndOfRange(byteRange, beginning, end);
    }

    private int[] setBeginningAndEndOfRange(int[] byteRange, int beginning, int end) {
        byteRange[0] = beginning;
        byteRange[1] = end;
        return byteRange;
    }

    private String beginningOfByteRange(String numberRange) {
        int start = 0;
        int sizeFromStart = numberRange.indexOf("-");
        String beginning = "".valueOf(numberRange.toCharArray(), start, sizeFromStart);
        if (!beginning.equals("")) {
            return beginning;
        } return "0";
    }

    private String endOfByteRange(String numberRange) {
        int start = numberRange.indexOf("-") + 1;
        int sizeFromStart = numberRange.length() - start;
        String end = "".valueOf(numberRange.toCharArray(), start, sizeFromStart);
        if (!end.equals("")) {
            return end;
        } return "0";
    }

    private String isolateNumberRange(String contentRange) {
        String numberRange = "";
        int start = contentRange.indexOf("=") + 1;
        int sizeFromStart = contentRange.length() - start;
        return numberRange.valueOf(contentRange.toCharArray(), start, sizeFromStart);
    }
}
