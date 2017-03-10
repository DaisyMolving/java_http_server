package server.request;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FormRequestTest {

    @Test
    public void formRequestRespondsWith200() throws IOException {
        FormRequest formRequest = new FormRequest("HTTP/1.1", "data=dog");
        assertEquals("HTTP/1.1 200 OK", formRequest.respond().startLine);
    }

}
