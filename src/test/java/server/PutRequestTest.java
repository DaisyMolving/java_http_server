package server;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class PutRequestTest {

    @Test
    public void createsAppropriateResponseFromPathForSimplePut() throws IOException {
        String requestInput = "PUT /form HTTP/1.1";
        Request request = new PutRequest("/form", "HTTP/1.1");
        assertTrue(request.createResponse() instanceof SuccessResponse);
    }

    @Test
    public void createsAppropriateResponseFromPathForMethodNotAllowed() throws IOException {
        String requestInput = "PUT / HTTP/1.1";
        Request request = new PutRequest("/", "HTTP/1.1");
        assertTrue(request.createResponse() instanceof MethodNotAllowedResponse);
    }
}
