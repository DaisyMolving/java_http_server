package server;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BogusRequestTest {

    @Test
    public void createsAppropriateResponseForBogusRequest() {
        String requestInput = "WJKES / HTTP/1.1";
        Request request = new BogusRequest(requestInput);
        assertTrue(request.createResponse() instanceof MethodNotAllowedResponse);
    }
}
