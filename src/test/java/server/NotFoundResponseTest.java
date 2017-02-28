package server;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NotFoundResponseTest {

    @Test
    public void createsAppropriateResponseContent() {
        Request request = new GetRequest("/nowhere", "HTTP/1.1");
        Response response = new NotFoundResponse(request.getProtocolVersion());
        String appropriateResponse = "HTTP/1.1 404 Not Found\nLocation: \nAllow: \n\n";
        assertEquals(appropriateResponse, response.generateContent());
    }
}
