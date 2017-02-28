package server;

public class RedirectResponse implements Response{

    public String protocolVersion;
    public StringBuilder responseContent = new StringBuilder();

    public RedirectResponse(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public String generateContent() {
        responseContent.append(createHead("http://localhost:5000/", ""));
        responseContent.append(createBody("")).toString();
        return responseContent.toString();
    }

    public String createHead(String location, String allow) {
        return
                protocolVersion + " " + "302 Found\n".concat(
                        "Location: " + location + "\n").concat(
                        "Allow: " + allow + "\n").concat(
                        "\n"
                );
    }

    private String createBody(String bodyContent) {
        return bodyContent;
    }
}