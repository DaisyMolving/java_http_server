package server.request;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IndexRequestTest {

    @Test
    public void indexRequestRespondsWith200() throws IOException {
        IndexRequest indexRequest = new IndexRequest("HTTP/1.1");
        assertEquals("HTTP/1.1 200 OK", indexRequest.respond().startLine);
    }

    @Test
    public void indexPageContainsHTMLLinksForAllPublicFiles() {
        IndexRequest indexRequest = new IndexRequest("HTTP/1.1");
        assertTrue(new String(indexRequest.addLinks()).contains("<a href=\"/file1\">file1</a>"));
        assertTrue(new String(indexRequest.addLinks()).contains("<a href=\"/file2\">file2</a>"));
        assertTrue(new String(indexRequest.addLinks()).contains("<a href=\"/image.jpeg\">image.jpeg</a>"));
        assertTrue(new String(indexRequest.addLinks()).contains("<a href=\"/image.gif\">image.gif</a>"));
        assertTrue(new String(indexRequest.addLinks()).contains("<a href=\"/image.png\">image.png</a>"));
        assertTrue(new String(indexRequest.addLinks()).contains("<a href=\"/text-file.txt\">text-file.txt</a>"));
    }

}
