package server;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class NotFoundResponseTest {

    @Test
    public void createsAppropriateResponseContent() throws IOException {
        Request request = new GetRequest("/nowhere", "HTTP/1.1");
        NotFoundResponse response = new NotFoundResponse("HTTP/1.1");
        String appropriateResponse = "HTTP/1.1 404 Not Found\nLocation: \nAllow: \n\n";
        assertEquals(appropriateResponse, new String(response.generateContent()));
    }
}
