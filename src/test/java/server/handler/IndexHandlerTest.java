package server.handler;

import org.junit.Test;
import server.request.IndexRequest;
import server.request.MethodNotAllowedRequest;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class IndexHandlerTest {

    @Test
    public void indexHandlerSendsIndexRequestIfMethodIsValid() {
        IndexHandler indexHandlerGet = new IndexHandler(setParameters("GET", "HTTP/1.1"));
        assertTrue(indexHandlerGet.send() instanceof IndexRequest);

        IndexHandler indexHandlerPost = new IndexHandler(setParameters("POST", "HTTP/1.1"));
        assertTrue(indexHandlerPost.send() instanceof IndexRequest);

        IndexHandler indexHandlerPut = new IndexHandler(setParameters("PUT", "HTTP/1.1"));
        assertTrue(indexHandlerPut.send() instanceof IndexRequest);

        IndexHandler indexHandlerHead = new IndexHandler(setParameters("HEAD", "HTTP/1.1"));
        assertTrue(indexHandlerHead.send() instanceof IndexRequest);

        IndexHandler indexHandlerBogus = new IndexHandler(setParameters("KDAJKFJDS", "HTTP/1.1"));
        assertTrue(indexHandlerBogus.send() instanceof MethodNotAllowedRequest);
    }

    private HashMap<String, String> setParameters(String method, String protocolVersion) {
        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("Method", method);
        requestParameters.put("Protocol Version", protocolVersion);
        return requestParameters;
    }
}