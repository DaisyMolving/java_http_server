package server;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PutRequestTest {

    @Test
    public void createsAppropriateResponseFromPathForSimplePut() {
        String requestInput = "PUT /form HTTP/1.1";
        Request request = new PutRequest(requestInput);
        assertTrue(request.createResponse() instanceof SuccessResponse);
    }

    @Test
    public void createsAppropriateResponseFromPathForMethodNotAllowed() {
        String requestInput = "PUT / HTTP/1.1";
        Request request = new PutRequest(requestInput);
        assertTrue(request.createResponse() instanceof MethodNotAllowedResponse);
    }
}
