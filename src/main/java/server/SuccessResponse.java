package server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class SuccessResponse implements Response{

    private String protocolVersion;
    private String path;
    private static final HashMap<String, String> headContentOptions = new HashMap<>();
    public static final HashMap<String, byte[]> bodyContentOptions = new HashMap<>();

    public SuccessResponse(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public SuccessResponse(String protocolVersion, String path) throws IOException {
        this.protocolVersion = protocolVersion;
        this.path = path;
        createHeadOptions();
        createBodyOptions();
    }

    public byte[] generateContent() throws IOException{
        byte[] head = generateHead();
        byte[] body = generateBody();
        int headLength = generateHead().length;
        int bodyLength = generateBody().length;
        byte[] content = new byte[headLength + bodyLength];
        System.arraycopy(head, 0, content, 0,  headLength);
        System.arraycopy(body, 0, content, headLength, bodyLength);
        return content;
    }

    public byte[] generateHead() {
        if (headContentOptions.containsKey(path)) {
            return headContentOptions.get(path).getBytes();
        } return createHead("", "", "").getBytes();
    }

    public byte[] generateBody() throws IOException {
        if (bodyContentOptions.containsKey(path)) {
            return bodyContentOptions.get(path);
        } return createBody("", "");
    }

    private String createHead(String location, String allow, String contentType) {
        return protocolVersion + " " + "200 OK\n" +
                "Location: " + location + "\n" +
                "Allow: " + allow + "\n" +
                "Content-Type: " + contentType + "\n" +
                "\n";
    }

    private byte[] createBody(String contentPath, String fileName) throws IOException {
        if (contentPath.isEmpty()) {
            return "".getBytes();
        } else {
            Path path = Paths.get(contentPath, fileName);
            byte[] data = Files.readAllBytes(path);
            return data;
        }
    }

    private void createHeadOptions() {
        headContentOptions.put("/method_options", createHead("", "GET,POST,OPTIONS,HEAD,PUT", ""));
        headContentOptions.put("/method_options2", createHead("", "GET,OPTIONS", ""));
        headContentOptions.put("/image.jpeg", createHead("", "", "image/jpeg"));
        headContentOptions.put("/image.png", createHead("", "", "image/png"));
        headContentOptions.put("/image.gif", createHead("", "", "image/gif"));
        headContentOptions.put("/text-file.txt", createHead("", "", "text/plain"));
        headContentOptions.put("/file1", createHead("", "", ""));
    }

    private void createBodyOptions() throws IOException {
        bodyContentOptions.put("/method_options", createBody("", ""));
        bodyContentOptions.put("/method_options2", createBody("", ""));
        bodyContentOptions.put("/image.jpeg", createBody("/Users/daisymolving/Documents/Apprenticeship/cob_spec/public/", "image.jpeg"));
        bodyContentOptions.put("/image.png", createBody("/Users/daisymolving/Documents/Apprenticeship/cob_spec/public/", "image.png"));
        bodyContentOptions.put("/image.gif", createBody("/Users/daisymolving/Documents/Apprenticeship/cob_spec/public/", "image.gif"));
        bodyContentOptions.put("/text-file.txt", createBody("/Users/daisymolving/Documents/Apprenticeship/cob_spec/public/", "text-file.txt"));
        bodyContentOptions.put("/file1", createBody("/Users/daisymolving/Documents/Apprenticeship/cob_spec/public/", "file1"));
    }
}