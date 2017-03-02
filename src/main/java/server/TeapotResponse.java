package server;

public class TeapotResponse implements Response{

    public String protocolVersion;
    public StringBuilder responseContent = new StringBuilder();

    public TeapotResponse (String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public byte[] generateContent() {
        responseContent.append(createHead("", ""));
        responseContent.append(createBody("I'm a teapot"));
        return responseContent.toString().getBytes();
    }

    public String createHead(String location, String allow) {
        return protocolVersion + " " + "418 I'm a teapot\n".concat(
                "Location: " + location + "\n").concat(
                "Allow: " + allow + "\n").concat(
                "\n");
    }

    private String createBody(String bodyContent) {
        return bodyContent;
    }
}
