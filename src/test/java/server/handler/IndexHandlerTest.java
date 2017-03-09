package server.handler;

import org.junit.Test;
import server.request.IndexRequest;
import server.request.MethodNotAllowedRequest;

import static org.junit.Assert.assertTrue;

public class IndexHandlerTest {

    @Test
    public void indexHandlerSendsIndexRequestIfMethodIsValid() {
        IndexHandler indexHandlerGet = new IndexHandler("GET", "HTTP/1.1");
        assertTrue(indexHandlerGet.send() instanceof IndexRequest);

        IndexHandler indexHandlerPost = new IndexHandler("POST", "HTTP/1.1");
        assertTrue(indexHandlerPost.send() instanceof IndexRequest);

        IndexHandler indexHandlerPut = new IndexHandler("PUT", "HTTP/1.1");
        assertTrue(indexHandlerPut.send() instanceof IndexRequest);

        IndexHandler indexHandlerHead = new IndexHandler("HEAD", "HTTP/1.1");
        assertTrue(indexHandlerHead.send() instanceof IndexRequest);

        IndexHandler indexHandlerBogus = new IndexHandler("KDAJKFJDS", "HTTP/1.1");
        assertTrue(indexHandlerBogus.send() instanceof MethodNotAllowedRequest);
    }
}