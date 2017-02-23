package server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeapotResponse implements Response{

    public String protocolVersion;
    public List<String> responseContent = new ArrayList<String>();

    public TeapotResponse (String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public List<String> generateContent() {
        responseContent.addAll(createHead("", ""));
        responseContent.addAll(createBody("I'm a teapot"));
        return responseContent;
    }

    public List<String> createHead(String location, String allow) {
        return Arrays.asList(
                protocolVersion + " " + "418 I'm a teapot",
                "Location: " + location,
                "Allow: " + allow,
                "\n"
        );
    }

    private List<String> createBody(String bodyContent) {
        return Arrays.asList(bodyContent);
    }
}
