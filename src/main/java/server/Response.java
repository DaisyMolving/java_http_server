package server;

public class Response {

    public Request currentRequest;

    public Response(Request currentRequest) {
        this.currentRequest = currentRequest;
    }

    public String generate() {


        if (currentRequest.getMethodVerb().equals("GET")) {
            System.out.println("get request");
            if (currentRequest.getPath().equals("/")) {
                return currentRequest.getProtocolVersion() + " " + "200 OK";
            } else if (currentRequest.getPath().equals("/text-file.txt")) {
                return currentRequest.getProtocolVersion() + " " + "200 OK";
            } else if (currentRequest.getPath().equals("/redirect")) {
                return currentRequest.getProtocolVersion() + " " + "302 Found" + "Location: http://localhost:5000/";
            } else if (currentRequest.getPath().equals("/tea")) {
                return currentRequest.getProtocolVersion() + " " + "200 OK";
            } else if (currentRequest.getPath().equals("/coffee")) {
                return currentRequest.getProtocolVersion() + " " + "418 I'm a teapot" + "\n" + "I'm a teapot";
            } else {
                return currentRequest.getProtocolVersion() + " " + "404 Not Found";
            }
        }

        else if (currentRequest.getMethodVerb().equals("OPTIONS")) {
            System.out.println("options request");
            if (currentRequest.getPath().equals("/method_options")) {
                return currentRequest.getProtocolVersion() + " " + "200 OK" + "Allow: GET,HEAD,POST,OPTIONS,PUT";
            } else if (currentRequest.getPath().equals("/method_options2")) {
                return currentRequest.getProtocolVersion() + " " + "200 OK" + "Allow: GET,OPTIONS";
            } else {
                return currentRequest.getProtocolVersion() + " " + "200 OK";
            }
        }

        else if (currentRequest.getMethodVerb().equals("POST")) {
            System.out.println("post request");
            if (currentRequest.getPath().equals("/form")) {
                return currentRequest.getProtocolVersion() + " " + "200 OK";
            } else {
                return currentRequest.getProtocolVersion() + " " + "405 Method Not Allowed";
            }
        }

        else if (currentRequest.getMethodVerb().equals("PUT")) {
            System.out.println("put request");
            if (currentRequest.getPath().equals("/form")) {
                return currentRequest.getProtocolVersion() + " " + "200 OK";
            } else {
                return currentRequest.getProtocolVersion() + " " + "405 Method Not Allowed";
            }
        }

        else if (currentRequest.getMethodVerb().equals("HEAD")) {
            System.out.println("head request");
            if (currentRequest.getPath().equals("/")) {
                return currentRequest.getProtocolVersion() + " " + "200 OK";
            } else {
                return currentRequest.getProtocolVersion() + " " + "404 Not Found";
            }
        }

        else {
            System.out.println("bogus request");
            return currentRequest.getProtocolVersion() + " " + "405 Method Not Allowed";
        }
    }
}
