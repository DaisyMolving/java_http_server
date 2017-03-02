package server;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MethodNotAllowedResponseTest {

    @Test
    public void createsAppropriateResponseContent() throws IOException {
        Request request = new BogusRequest("/anything", "HTTP/1.1");
        MethodNotAllowedResponse response = new MethodNotAllowedResponse("HTTP/1.1");
        String appropriateResponse = "HTTP/1.1 405 Method Not Allowed\nLocation: \nAllow: \n\n";
        assertEquals(appropriateResponse, new String(response.generateContent()));
    }
}
