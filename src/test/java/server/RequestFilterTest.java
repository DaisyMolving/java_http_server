package server;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestFilterTest {

    @Test
    public void requestFilterCreatesCorrectRequestDependingOnInputForGet() throws IOException {
        RequestFilter requestFilter = new RequestFilter("GET / HTTP/1.1");
        assertTrue(requestFilter.createByType() instanceof Response);
    }

    @Test
    public void requestFilterCreatesCorrectResponseDependingOnInputForGet() throws IOException {
        RequestFilter requestFilter = new RequestFilter("GET / HTTP/1.1");
        assertEquals("HTTP/1.1 200 OK", requestFilter.createByType().startLine);
    }

    @Test
    public void requestFilterCreatesCorrectRequestDependingOnInputForPost() throws IOException {
        RequestFilter requestFilter = new RequestFilter("POST /form HTTP/1.1");
        assertEquals("HTTP/1.1 200 OK", requestFilter.createByType().startLine);
    }

    @Test
    public void requestFilterCreatesCorrectRequestDependingOnInputForPut() throws IOException {
        RequestFilter requestFilter = new RequestFilter("PUT /form HTTP/1.1");
        assertEquals("HTTP/1.1 200 OK", requestFilter.createByType().startLine);
    }

    @Test
    public void requestFilterCreatesCorrectRequestDependingOnInputForHead() throws IOException {
        RequestFilter indexRequestFilter = new RequestFilter("HEAD / HTTP/1.1");
        assertEquals("HTTP/1.1 200 OK", indexRequestFilter.createByType().startLine);
        RequestFilter requestFilter = new RequestFilter("HEAD /foobar HTTP/1.1");
        assertEquals("HTTP/1.1 404 Not Found", requestFilter.createByType().startLine);
    }

    @Test
    public void requestFilterCreatesCorrectRequestDependingOnInputForOptions() throws IOException {
        RequestFilter indexRequestFilter = new RequestFilter("OPTIONS /method_options HTTP/1.1");
        assertEquals("HTTP/1.1 200 OK", indexRequestFilter.createByType().startLine);
    }

    @Test
    public void requestFilterCreatesCorrectRequestDependingOnInputForBogus() throws IOException {
        RequestFilter requestFilter = new RequestFilter("JKWERA /form HTTP/1.1");
        assertEquals("HTTP/1.1 405 Method Not Allowed", requestFilter.createByType().startLine);
    }

    @Test
    public void requestFilterCreatesCorrectRequestDependingOnFileType() throws IOException {
        RequestFilter getJPEGFilter = new RequestFilter("GET /image.jpeg HTTP/1.1");
        assertEquals("HTTP/1.1 200 OK", getJPEGFilter.createByType().startLine);
    }

    @Test
    public void requestFilterCreatesCorrectRequestDependingOnFileWithNoSuffix() throws IOException {
        RequestFilter getFileFilter = new RequestFilter("GET /file1 HTTP/1.1");
        assertEquals("HTTP/1.1 200 OK", getFileFilter.createByType().startLine);
    }
}
