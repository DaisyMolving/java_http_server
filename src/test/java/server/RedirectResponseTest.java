package server;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RedirectResponseTest {

@Test
public void createsAppropriateResponseContent() {
        Request request = new GetRequest("/redirect", "HTTP/1.1");
        Response response = new RedirectResponse(request.getProtocolVersion());
        String appropriateResponseElements = "HTTP/1.1 302 Found\nLocation: http://localhost:5000/\nAllow: \n\n";
        assertEquals(appropriateResponseElements, response.generateContent());
        }
}
