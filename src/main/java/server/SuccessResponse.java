package server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SuccessResponse implements Response{

    public String protocolVersion;
    public List<String> responseContent = new ArrayList<String>();
    public String optionsPath;

    public SuccessResponse(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public SuccessResponse(String procotolVersion, String optionsPath) {
        this.protocolVersion = procotolVersion;
        this.optionsPath = optionsPath;
    }

    public List<String> generateContent() {
        if (optionsPath == "/method_options") {
            responseContent.addAll(createHead("", "GET,HEAD,POST,OPTIONS,PUT"));
            responseContent.addAll(createBody(""));
            return responseContent;
        } else if (optionsPath == "/method_options2") {
            responseContent.addAll(createHead("", "GET,OPTIONS"));
            responseContent.addAll(createBody(""));
            return responseContent;
        }
        responseContent.addAll(createHead("", ""));
        responseContent.addAll(createBody(""));
        return responseContent;
    }

    private List<String> createHead(String location, String allow) {
        return Arrays.asList(
                protocolVersion + " " + "200 OK",
                "Location: " + location,
                "Allow: " + allow,
                "\n"
        );
    }

    private List<String> createBody(String bodyContent) {
        return Arrays.asList(bodyContent);
    }
}
