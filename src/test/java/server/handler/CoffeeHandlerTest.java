package server.handler;

import org.junit.Test;
import server.request.BogusRequest;
import server.request.CoffeeRequest;
import server.request.MethodNotAllowedRequest;

import static org.junit.Assert.assertTrue;

public class CoffeeHandlerTest {

    @Test
    public void coffeeHandlerSendsCoffeeRequestIfMethodIsValid() {
        CoffeeHandler coffeeHandlerGet = new CoffeeHandler("GET", "HTTP/1.1");
        assertTrue(coffeeHandlerGet.send() instanceof CoffeeRequest);

        CoffeeHandler coffeeHandlerPost = new CoffeeHandler("POST", "HTTP/1.1");
        assertTrue(coffeeHandlerPost.send() instanceof BogusRequest);

        CoffeeHandler coffeeHandlerPut = new CoffeeHandler("PUT", "HTTP/1.1");
        assertTrue(coffeeHandlerPut.send() instanceof BogusRequest);

        CoffeeHandler coffeeHandlerHead = new CoffeeHandler("HEAD", "HTTP/1.1");
        assertTrue(coffeeHandlerHead.send() instanceof BogusRequest);

        CoffeeHandler coffeeHandlerBogus = new CoffeeHandler("KDAJKFJDS", "HTTP/1.1");
        assertTrue(coffeeHandlerBogus.send() instanceof MethodNotAllowedRequest);
    }

}
