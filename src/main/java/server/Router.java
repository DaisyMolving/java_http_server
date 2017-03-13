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
        requestTypes.put("/file2", new FileHandler(requestParameters));
        requestTypes.put("/text-file.txt", new FileHandler(requestParameters));
        requestTypes.put("/image.jpeg", new FileHandler(requestParameters));
        requestTypes.put("/image.png", new FileHandler(requestParameters));
        requestTypes.put("/image.gif", new FileHandler(requestParameters));
        requestTypes.put("/partial_content.txt", new FileHandler(requestParameters));
        requestTypes.put("/tea", new IndexHandler(requestParameters));
        requestTypes.put("/coffee", new CoffeeHandler(requestParameters));
        requestTypes.put("/method_options", new MethodOptionsHandler(requestParameters));
        requestTypes.put("/method_options2", new MethodOptionsTwoHandler(requestParameters));
        requestTypes.put("/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff", new ParameterHandler(requestParameters));
        requestTypes.put("/cookie?type=chocolate", new CookieHandler(requestParameters, dataStore));
        requestTypes.put("/eat_cookie", new CookieHandler(requestParameters, dataStore));

        if (requestTypes.containsKey(path)) {
            return requestTypes.get(path);
        } return new ErrorHandler(method, protocolVersion);
    }

}
