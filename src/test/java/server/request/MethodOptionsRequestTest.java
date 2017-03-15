package server.request;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MethodOptionsRequestTest {

    @Test
    public void methodOptionsRequestRespondsWith200() throws IOException {
        MethodOptionsRequest methodOptionsRequest = new MethodOptionsRequest("HTTP/1.1");
        assertEquals("HTTP/1.1 200 OK\nAllow: GET,HEAD,POST,OPTIONS,PUT\n\n", new String(methodOptionsRequest.respond().generateContent()));
    }

}
