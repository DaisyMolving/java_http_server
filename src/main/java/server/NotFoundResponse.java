package server;

public class NotFoundResponse implements Response{

    public String protocolVersion;
    public StringBuilder responseContent = new StringBuilder();

    public NotFoundResponse (String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public byte[] generateContent() {
        responseContent.append(createHead("", ""));
        responseContent.append(createBody(""));
        return responseContent.toString().getBytes();
    }

    public String createHead(String location, String allow) {
        return protocolVersion + " " + "404 Not Found\n".concat(
                "Location: " + location + "\n").concat(
                "Allow: " + allow + "\n").concat(
                "\n");
    }

    private String createBody(String bodyContent) {
        return bodyContent;
    }
}