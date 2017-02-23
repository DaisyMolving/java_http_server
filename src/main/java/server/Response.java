package server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Response {

    public Request currentRequest;
    public List<String> responseContent = new ArrayList<>();

    public Response(Request currentRequest) {
        this.currentRequest = currentRequest;
    }

    public List<String> generate() {


        if (currentRequest.getMethodVerb().equals("GET")) {
            System.out.println("get request");
            if (currentRequest.getPath().equals("/")) {
                responseContent.addAll(createHead(currentRequest.getProtocolVersion() + " " + "200 OK", "", ""));
                responseContent.addAll(createBody(""));
                return responseContent;
            } else if (currentRequest.getPath().equals("/text-file.txt")) {
                responseContent.addAll(createHead(currentRequest.getProtocolVersion() + " " + "200 OK", "", ""));
                responseContent.addAll(createBody(""));
                return responseContent;
            } else if (currentRequest.getPath().equals("/redirect")) {
                responseContent.addAll(createHead(currentRequest.getProtocolVersion() + " " + "302 Found", "http://localhost:5000/", ""));
                responseContent.addAll(createBody(""));
                return responseContent;
            } else if (currentRequest.getPath().equals("/tea")) {
                responseContent.addAll(createHead(currentRequest.getProtocolVersion() + " " + "200 OK", "", ""));
                responseContent.addAll(createBody(""));
                return responseContent;
            } else if (currentRequest.getPath().equals("/coffee")) {
                responseContent.addAll(createHead(currentRequest.getProtocolVersion() + " " + "418 I'm a teapot", "", ""));
                responseContent.addAll(createBody("I'm a teapot"));
                return responseContent;
            } else {
                responseContent.addAll(createHead(currentRequest.getProtocolVersion() + " " + "404 Not Found", "", ""));
                responseContent.addAll(createBody(""));
                return responseContent;
            }
        }

        else if (currentRequest.getMethodVerb().equals("OPTIONS")) {
            System.out.println("options request");
            if (currentRequest.getPath().equals("/method_options")) {
                responseContent.addAll(createHead(currentRequest.getProtocolVersion() + " " + "200 OK", "", "GET,HEAD,POST,OPTIONS,PUT"));
                responseContent.addAll(createBody(""));
                return responseContent;
            } else if (currentRequest.getPath().equals("/method_options2")) {
                responseContent.addAll(createHead(currentRequest.getProtocolVersion() + " " + "200 OK", "", "GET,OPTIONS"));
                responseContent.addAll(createBody(""));
                return responseContent;
            } else {
                responseContent.addAll(createHead(currentRequest.getProtocolVersion() + " " + "200 OK", "", ""));
                responseContent.addAll(createBody(""));
                return responseContent;
            }
        }

        else if (currentRequest.getMethodVerb().equals("POST")) {
            System.out.println("post request");
            if (currentRequest.getPath().equals("/form")) {
                responseContent.addAll(createHead(currentRequest.getProtocolVersion() + " " + "200 OK", "", ""));
                responseContent.addAll(createBody(""));
                return responseContent;
            } else {
                responseContent.addAll(createHead(currentRequest.getProtocolVersion() + " " + "405 Method Not Allowed", "", ""));
                responseContent.addAll(createBody(""));
                return responseContent;
            }
        }

        else if (currentRequest.getMethodVerb().equals("PUT")) {
            System.out.println("put request");
            if (currentRequest.getPath().equals("/form")) {
                responseContent.addAll(createHead(currentRequest.getProtocolVersion() + " " + "200 OK", "", ""));
                responseContent.addAll(createBody(""));
                return responseContent;
            } else {
                responseContent.addAll(createHead(currentRequest.getProtocolVersion() + " " + "405 Method Not Allowed", "", ""));
                responseContent.addAll(createBody(""));
                return responseContent;
            }
        }

        else if (currentRequest.getMethodVerb().equals("HEAD")) {
            System.out.println("head request");
            if (currentRequest.getPath().equals("/")) {
                responseContent.addAll(createHead(currentRequest.getProtocolVersion() + " " + "200 OK", "", ""));
                responseContent.addAll(createBody(""));
                return responseContent;
            } else {
                responseContent.addAll(createHead(currentRequest.getProtocolVersion() + " " + "404 Not Found", "", ""));
                responseContent.addAll(createBody(""));
                return responseContent;
            }
        }
        responseContent.addAll(createHead(currentRequest.getProtocolVersion() + " " + "405 Method Not Allowed", "", ""));
        responseContent.addAll(createBody(""));
        return responseContent;
    }

    public List<String> createHead(String startLine, String location, String allow) {
        return Arrays.asList(
                startLine,
                "Location: " + location,
                "Allow: " + allow,
                "\n"
        );
    }

    public List<String> createBody(String bodyContent) {
        return Arrays.asList(bodyContent);
    }

}
