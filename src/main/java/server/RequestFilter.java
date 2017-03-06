package server;

import java.io.File;
import java.util.HashMap;

public class RequestFilter {

    private HashMap<String, Response> requestTypes = new HashMap<>();
    public String methodVerb;
    public String methodVerbAndPath;
    private String protocolVersion;
    private HashMap<String, String> responseParameterOptions;

    public RequestFilter(String requestInformation) {
        splitRequestIntoComponents(requestInformation);
        requestTypes.put("GET /", getIndex());
        requestTypes.put("GET /file1", getFile("file1"));
        requestTypes.put("GET /redirect", getRedirect());
        requestTypes.put("GET /tea", new Response(protocolVersion + " 200 OK", "", "", "", "", ""));
        requestTypes.put("GET /coffee", getCoffee());
        requestTypes.put("GET /text-file.txt", getFile("text-file.txt"));
        requestTypes.put("GET /image.jpeg", getFile("image.jpeg"));
        requestTypes.put("GET /image.png", getFile("image.png"));
        requestTypes.put("GET /image.gif", getFile("image.gif"));
        requestTypes.put("POST /form", postInformation());
        requestTypes.put("PUT /form", putInformation());
        requestTypes.put("HEAD /", new Response(protocolVersion + " 200 OK", "", "", "", "", ""));
        requestTypes.put("OPTIONS /method_options", options("GET,POST,OPTIONS,HEAD,PUT"));
        requestTypes.put("OPTIONS /method_options2", options("GET,OPTIONS"));
    }

    public Response createByType() {
        if (requestTypes.containsKey(methodVerbAndPath)) {
            return requestTypes.get(methodVerbAndPath);
        } else if (methodNotCompatible()) {
            return new Response(
                    protocolVersion + " 405 Method Not Allowed");
        } else if (methodDoesNotExist()) {
            return new Response(
                protocolVersion + " 405 Method Not Allowed");
        }
        return new Response(
                    protocolVersion + " 404 Not Found");
    }

    private boolean methodNotCompatible() {
        return (methodVerbAndPath.equals("PUT /file1") || (methodVerbAndPath.equals("POST /text-file.txt")));
    }

    private boolean methodDoesNotExist() {
        String possibleMethods = "GET, PUT, POST, OPTIONS, HEAD";
        return !(possibleMethods.contains(methodVerb));
    }

    private Response getIndex() {
        return new Response(
                protocolVersion + " 200 OK");
    }

    private Response getFile(String fileName) {
        if (fileExists(fileName)) {
            return new Response(
                    protocolVersion + " 200 OK",
                    "",
                    "",
                    getContentType(fileName),
                    "/Users/daisymolving/Documents/Apprenticeship/cob_spec/public/",
                    fileName);
        } return new Response(protocolVersion + " 404 Not Found");
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

    private Response getRedirect() {
        return new Response(
                protocolVersion + " 302 Found",
                "http://localhost:5000/",
                "",
                "",
                "",
                "");
    }

    private Response getCoffee() {
        return new Response(
                protocolVersion + " 418 I'm a teapot");
    }

    private Response postInformation() {
        return new Response(
                protocolVersion + " 200 OK");
    }

    private Response putInformation() {
        return new Response(
                protocolVersion + " 200 OK");
    }

    private Response options(String allowedMethods) {
        return new Response(
                protocolVersion + " 200 OK",
                "",
                allowedMethods,
                "",
                "",
                "");
    }

    private void splitRequestIntoComponents(String requestInformation) {
        String[] splitRequest = requestInformation.split("\\s+");
        this.methodVerb = splitRequest[0];
        String path = splitRequest[1];
        this.protocolVersion = splitRequest[2];
        methodVerbAndPath = methodVerb + " " + path;
    }

}
