package server;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MethodNotAllowedResponseTest {

    @Test
    public void createsAppropriateResponseContent() {
        Request request = new BogusRequest("/anything", "HTTP/1.1");
        Response response = new MethodNotAllowedResponse(request.getProtocolVersion());
        String appropriateResponse = "HTTP/1.1 405 Method Not Allowed\nLocation: \nAllow: \n\n";
        assertEquals(appropriateResponse, response.generateContent());
    }
}
