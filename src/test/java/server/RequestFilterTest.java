package server;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestFilterTest {

    @Test
    public void requestFilterCreatesCorrectRequestDependingOnInputForGet() throws IOException {
        RequestFilter requestFilter = new RequestFilter("GET /", "HTTP/1.1", "");
        assertTrue(requestFilter.createByType() instanceof Response);
    }

    @Test
    public void requestFilterCreatesCorrectResponseDependingOnInputForGet() throws IOException {
        RequestFilter requestFilter = new RequestFilter("GET /", "HTTP/1.1", "");
        assertEquals("HTTP/1.1 200 OK", requestFilter.createByType().startLine);
    }

    @Test
    public void requestFilterCreatesCorrectRequestDependingOnInputForPost() throws IOException {
        RequestFilter requestFilter = new RequestFilter("POST /form", "HTTP/1.1", "");
        assertEquals("HTTP/1.1 200 OK", requestFilter.createByType().startLine);
    }

    @Test
    public void requestFilterCreatesCorrectRequestDependingOnInputForPut() throws IOException {
        RequestFilter requestFilter = new RequestFilter("PUT /form", "HTTP/1.1", "");
        assertEquals("HTTP/1.1 200 OK", requestFilter.createByType().startLine);
    }

    @Test
    public void requestFilterCreatesCorrectRequestDependingOnInputForHead() throws IOException {
        RequestFilter indexRequestFilter = new RequestFilter("HEAD /", "HTTP/1.1", "");
        assertEquals("HTTP/1.1 200 OK", indexRequestFilter.createByType().startLine);
        RequestFilter notFoundRequestFilter = new RequestFilter("HEAD /foobar", "HTTP/1.1", "");
        assertEquals("HTTP/1.1 404 Not Found", notFoundRequestFilter.createByType().startLine);
    }

    @Test
    public void requestFilterCreatesCorrectRequestDependingOnInputForOptions() throws IOException {
        RequestFilter requestFilter = new RequestFilter("OPTIONS /method_options", "HTTP/1.1", "");
        assertEquals("HTTP/1.1 200 OK", requestFilter.createByType().startLine);
    }

    @Test
    public void requestFilterCreatesCorrectRequestDependingOnInputForBogus() throws IOException {
        RequestFilter requestFilter = new RequestFilter("JEKAFSD /method_options", "HTTP/1.1", "");
        assertEquals("HTTP/1.1 405 Method Not Allowed", requestFilter.createByType().startLine);
    }

    @Test
    public void requestFilterCreatesCorrectRequestDependingOnFileType() throws IOException {
        RequestFilter getJPEGFilter = new RequestFilter("GET /image.jpeg", "HTTP/1.1", "");
        assertEquals("HTTP/1.1 200 OK", getJPEGFilter.createByType().startLine);
    }

    @Test
    public void requestFilterCreatesCorrectRequestDependingOnFileWithNoSuffix() throws IOException {
        RequestFilter getFileFilter = new RequestFilter("GET /text-file.txt", "HTTP/1.1", "");
        assertEquals("HTTP/1.1 200 OK", getFileFilter.createByType().startLine);
    }
}
