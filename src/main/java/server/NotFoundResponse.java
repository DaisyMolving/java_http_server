package server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotFoundResponse implements Response{

    public String protocolVersion;
    public List<String> responseContent = new ArrayList<String>();

    public NotFoundResponse (String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public List<String> generateContent() {
        responseContent.addAll(createHead("", ""));
        responseContent.addAll(createBody(""));
        return responseContent;
    }

    public List<String> createHead(String location, String allow) {
        return Arrays.asList(
                protocolVersion + " " + "404 Not Found",
                "Location: " + location,
                "Allow: " + allow,
                "\n"
        );
    }

    private List<String> createBody(String bodyContent) {
        return Arrays.asList(bodyContent);
    }
}