package server.handler;

import org.junit.Test;
import server.request.BogusRequest;
import server.request.CoffeeRequest;
import server.request.MethodNotAllowedRequest;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class CoffeeHandlerTest {

    @Test
    public void coffeeHandlerSendsCoffeeRequestIfMethodIsValid() {
        CoffeeHandler coffeeHandlerGet = new CoffeeHandler(setParameters("GET", "HTTP/1.1"));
        assertTrue(coffeeHandlerGet.send() instanceof CoffeeRequest);

        CoffeeHandler coffeeHandlerPost = new CoffeeHandler(setParameters("POST", "HTTP/1.1"));
        assertTrue(coffeeHandlerPost.send() instanceof BogusRequest);

        CoffeeHandler coffeeHandlerPut = new CoffeeHandler(setParameters("PUT", "HTTP/1.1"));
        assertTrue(coffeeHandlerPut.send() instanceof BogusRequest);

        CoffeeHandler coffeeHandlerHead = new CoffeeHandler(setParameters("HEAD", "HTTP/1.1"));
        assertTrue(coffeeHandlerHead.send() instanceof BogusRequest);

        CoffeeHandler coffeeHandlerBogus = new CoffeeHandler(setParameters("KDAJKFJDS", "HTTP/1.1"));
        assertTrue(coffeeHandlerBogus.send() instanceof MethodNotAllowedRequest);
    }

    private HashMap<String, String> setParameters(String method, String protocolVersion) {
        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("Method", method);
        requestParameters.put("Protocol Version", protocolVersion);
        return requestParameters;
    }
}
