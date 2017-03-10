package server.request;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RedirectRequestTest {

    @Test
    public void redirectRequestRespondsWith302() throws IOException {
        RedirectRequest redirectRequest = new RedirectRequest("HTTP/1.1");
        assertEquals("HTTP/1.1 302 Found", redirectRequest.respond().startLine);
    }

}
