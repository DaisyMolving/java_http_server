package server.request;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MethodNotAllowedRequestTest {

    @Test
    public void methodNotAllowedRequestRespondsWith405() throws IOException {
        MethodNotAllowedRequest methodNotAllowedRequest = new MethodNotAllowedRequest("HTTP/1.1");
        assertEquals("HTTP/1.1 405 Method Not Allowed", methodNotAllowedRequest.respond().startLine);
    }

}
