package server;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RedirectResponseTest {

@Test
public void createsAppropriateResponseContent() throws IOException {
        Request request = new GetRequest("/redirect", "HTTP/1.1");
        RedirectResponse response = new RedirectResponse("HTTP/1.1");
        String appropriateResponseElements = "HTTP/1.1 302 Found\nLocation: http://localhost:5000/\nAllow: \n\n";
        assertEquals(appropriateResponseElements, new String(response.generateContent()));
        }
}
