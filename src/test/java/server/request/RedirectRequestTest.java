package server.request;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RedirectRequestTest {

    @Test
    public void redirectRequestRespondsWith302() throws IOException {
        RedirectRequest redirectRequest = new RedirectRequest("HTTP/1.1");
        assertEquals("HTTP/1.1 302 Found\nLocation: http://localhost:5000/\n\n", new String(redirectRequest.respond().generateContent()));
    }

}
