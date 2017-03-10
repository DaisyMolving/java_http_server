package server.request;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MethodOptionsTwoRequestTest {

    @Test
    public void methodOptionsTwoRequestRespondsWith200() throws IOException {
        MethodOptionsTwoRequest methodOptionsTwoRequest = new MethodOptionsTwoRequest("HTTP/1.1");
        assertEquals("HTTP/1.1 200 OK", methodOptionsTwoRequest.respond().startLine);
    }

}
