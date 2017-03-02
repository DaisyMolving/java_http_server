package server;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class BogusRequestTest {

    @Test
    public void createsAppropriateResponseForBogusRequest() throws IOException {
        Request request = new BogusRequest("/", "HTTP/1.1");
        assertTrue(request.createResponse() instanceof MethodNotAllowedResponse);
    }
}
