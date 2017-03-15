package server.request;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CoffeeRequestTest {

    @Test
    public void coffeeRequestRespondsWith418() throws IOException {
        CoffeeRequest coffeeRequest = new CoffeeRequest("HTTP/1.1");
        assertEquals("HTTP/1.1 418 I'm a teapot\n\nI'm a teapot", new String(coffeeRequest.respond().generateContent()));
    }

}