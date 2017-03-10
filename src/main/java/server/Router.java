package server;

import server.handler.*;

import java.util.HashMap;

public class Router {

    private HashMap<String, Handler> requestTypes = new HashMap<>();

    public Handler routeNewRequest(HashMap<String, String> requestParameters, DataStore dataStore) {
        String method = requestParameters.get("Method");
        String path = requestParameters.get("Path");
        String protocolVersion = requestParameters.get("Protocol Version");

        requestTypes.put("/", new IndexHandler(requestParameters));
        requestTypes.put("/form", new FormHandler(requestParameters, dataStore));
        requestTypes.put("/redirect", new RedirectHandler(requestParameters));
        requestTypes.put("/file1", new FileHandler(requestParameters));
        requestTypes.put("/text-file.txt", new FileHandler(requestParameters));
        requestTypes.put("/image.jpeg", new FileHandler(requestParameters));
        requestTypes.put("/image.png", new FileHandler(requestParameters));
        requestTypes.put("/image.gif", new FileHandler(requestParameters));
        requestTypes.put("/partial_content.txt", new FileHandler(requestParameters));
        requestTypes.put("/tea", new IndexHandler(requestParameters));
        requestTypes.put("/coffee", new CoffeeHandler(requestParameters));
        requestTypes.put("/method_options", new MethodOptionsHandler(requestParameters));
        requestTypes.put("/method_options2", new MethodOptionsTwoHandler(requestParameters));

        if (requestTypes.containsKey(path)) {
            return requestTypes.get(path);
        } return new ErrorHandler(method, protocolVersion);
    }

}
