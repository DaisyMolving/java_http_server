package server.request;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class BogusRequestTest {

    @Test
    public void bogusRequestRespondsWith404() throws IOException {
        BogusRequest bogusRequest = new BogusRequest("HTTP/1.1");
        assertEquals("HTTP/1.1 404 Not Found", bogusRequest.respond().startLine);
    }

}
