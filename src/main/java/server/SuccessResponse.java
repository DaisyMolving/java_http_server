package server;

import java.util.HashMap;

public class SuccessResponse implements Response{

    public String protocolVersion;
    public StringBuilder responseContent = new StringBuilder();
    public String optionsPath;
    public HashMap<String, String> responseContentOptions = new HashMap<>();

    public SuccessResponse(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public SuccessResponse(String protocolVersion, String optionsPath) {
        this.protocolVersion = protocolVersion;
        this.optionsPath = optionsPath;
        responseContentOptions.put("/method_options", writeToResponseBody("GET,POST,OPTIONS,HEAD,PUT"));
        responseContentOptions.put("/method_options2", writeToResponseBody("GET,OPTIONS"));
    }

    public String generateContent() {
        if (responseContentOptions.containsKey(optionsPath)) {
            return responseContentOptions.get(optionsPath);
        } return writeToResponseBody("");
    }

    private String writeToResponseBody(String allowOptions) {
        responseContent.append(createHead("http://localhost:5000/Users/daisymolving/Documents/Apprenticeship/cob_spec/public/", allowOptions));
        responseContent.append(createBody(""));
        return responseContent.toString();
    }

    private String createHead(String location, String allow) {
        return protocolVersion + " " + "200 OK\n" +
                "Location: " + location + "\n" +
                "Allow: " + allow + "\n" +
                "\n";
    }

    private String createBody(String bodyContent) {
        return bodyContent;
    }
}