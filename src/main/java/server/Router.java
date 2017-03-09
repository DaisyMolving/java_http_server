package server;

import server.handler.*;

import java.util.HashMap;

public class Router {

    private HashMap<String, Handler> requestTypes = new HashMap<>();

    public Handler routeNewRequest(String method, String path, String requestBody, String protocolVersion) {
        requestTypes.put("/", new IndexHandler(method, protocolVersion));
        requestTypes.put("/form", new FormHandler(method, protocolVersion, requestBody));
        requestTypes.put("/redirect", new RedirectHandler(method, protocolVersion));
        requestTypes.put("/file1", new FileHandler(method, protocolVersion, path));
        requestTypes.put("/text-file.txt", new FileHandler(method, protocolVersion, path));
        requestTypes.put("/image.jpeg", new FileHandler(method, protocolVersion, path));
        requestTypes.put("/image.png", new FileHandler(method, protocolVersion, path));
        requestTypes.put("/image.gif", new FileHandler(method, protocolVersion, path));
        requestTypes.put("/tea", new IndexHandler(method, protocolVersion));
        requestTypes.put("/coffee", new CoffeeHandler(method, protocolVersion));
        requestTypes.put("/method_options", new MethodOptionsHandler(method, protocolVersion));
        requestTypes.put("/method_options2", new MethodOptionsTwoHandler(method, protocolVersion));

        if (requestTypes.containsKey(path)) {
            return requestTypes.get(path);
        } return new ErrorHandler(method, protocolVersion);
    }

}
