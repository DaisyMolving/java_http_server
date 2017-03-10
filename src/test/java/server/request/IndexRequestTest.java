package server.request;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class IndexRequestTest {

    @Test
    public void indexRequestRespondsWith200() throws IOException {
        IndexRequest indexRequest = new IndexRequest("HTTP/1.1");
        assertEquals("HTTP/1.1 200 OK", indexRequest.respond().startLine);
    }

}
