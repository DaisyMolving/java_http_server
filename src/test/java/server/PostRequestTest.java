package server;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class PostRequestTest {

    @Test
    public void createsAppropriateResponseFromPathForSimplePut() throws IOException {
        Request request = new PostRequest("/form", "HTTP/1.1");
        assertTrue(request.createResponse() instanceof SuccessResponse);
    }

    @Test
    public void createsAppropriateResponseFromPathForMethodNotAllowed() throws IOException {
        Request request = new PostRequest("/", "HTTP/1.1");
        assertTrue(request.createResponse() instanceof MethodNotAllowedResponse);
    }
}