package server;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestFilterTest {
//
//    @Test
//    public void requestFilterCreatesCorrectRequestDependingOnInputForGet() throws IOException {
//        String requestHead = "GET / HTTP/1.1\nHost: localhost:5000\nConnection: Keep-Alive\nUser-Agent: Apache-HttpClient/4.3.5 (java 1.5)\nAccept-Encoding: gzip,deflate";
//        RequestFilter requestFilter = new RequestFilter(requestHead);
//        assertTrue(requestFilter.createByType() instanceof Response);
//    }
//
//    @Test
//    public void requestFilterCreatesCorrectResponseDependingOnInputForGet() throws IOException {
//        String requestHead = "GET / HTTP/1.1\nHost: localhost:5000\nConnection: Keep-Alive\nUser-Agent: Apache-HttpClient/4.3.5 (java 1.5)\nAccept-Encoding: gzip,deflate";
//        RequestFilter requestFilter = new RequestFilter(requestHead);
//        assertEquals("HTTP/1.1 200 OK", requestFilter.createByType().startLine);
//    }
//
//    @Test
//    public void requestFilterCreatesCorrectRequestDependingOnInputForPost() throws IOException {
//        String requestHead = "POST /form HTTP/1.1\nHost: localhost:5000\nConnection: Keep-Alive\nUser-Agent: Apache-HttpClient/4.3.5 (java 1.5)\nAccept-Encoding: gzip,deflate";
//        RequestFilter requestFilter = new RequestFilter(requestHead);
//        assertEquals("HTTP/1.1 200 OK", requestFilter.createByType().startLine);
//    }
//
//    @Test
//    public void requestFilterCreatesCorrectRequestDependingOnInputForPut() throws IOException {
//        String requestHead = "PUT /form HTTP/1.1\nHost: localhost:5000\nConnection: Keep-Alive\nUser-Agent: Apache-HttpClient/4.3.5 (java 1.5)\nAccept-Encoding: gzip,deflate";
//        RequestFilter requestFilter = new RequestFilter(requestHead);
//        assertEquals("HTTP/1.1 200 OK", requestFilter.createByType().startLine);
//    }
//
//    @Test
//    public void requestFilterCreatesCorrectRequestDependingOnInputForHead() throws IOException {
//        String requestHeadForIndex = "HEAD / HTTP/1.1\nHost: localhost:5000\nConnection: Keep-Alive\nUser-Agent: Apache-HttpClient/4.3.5 (java 1.5)\nAccept-Encoding: gzip,deflate";
//        RequestFilter indexRequestFilter = new RequestFilter(requestHeadForIndex);
//        assertEquals("HTTP/1.1 200 OK", indexRequestFilter.createByType().startLine);
//        String requestHeadForNotFound = "HEAD /foobar HTTP/1.1\nHost: localhost:5000\nConnection: Keep-Alive\nUser-Agent: Apache-HttpClient/4.3.5 (java 1.5)\nAccept-Encoding: gzip,deflate";
//        RequestFilter notFoundRequestFilter = new RequestFilter(requestHeadForNotFound);
//        assertEquals("HTTP/1.1 404 Not Found", notFoundRequestFilter.createByType().startLine);
//    }
//
//    @Test
//    public void requestFilterCreatesCorrectRequestDependingOnInputForOptions() throws IOException {
//        String requestHead = "OPTIONS /method_options HTTP/1.1\nHost: localhost:5000\nConnection: Keep-Alive\nUser-Agent: Apache-HttpClient/4.3.5 (java 1.5)\nAccept-Encoding: gzip,deflate";
//        RequestFilter requestFilter = new RequestFilter(requestHead);
//        assertEquals("HTTP/1.1 200 OK", requestFilter.createByType().startLine);
//    }
//
//    @Test
//    public void requestFilterCreatesCorrectRequestDependingOnInputForBogus() throws IOException {
//        String requestHead = "JKREWAS /method_options HTTP/1.1\nHost: localhost:5000\nConnection: Keep-Alive\nUser-Agent: Apache-HttpClient/4.3.5 (java 1.5)\nAccept-Encoding: gzip,deflate";
//        RequestFilter requestFilter = new RequestFilter(requestHead);
//        assertEquals("HTTP/1.1 405 Method Not Allowed", requestFilter.createByType().startLine);
//    }
//
//    @Test
//    public void requestFilterCreatesCorrectRequestDependingOnFileType() throws IOException {
//        String requestHead = "GET /image.jpeg HTTP/1.1\nHost: localhost:5000\nConnection: Keep-Alive\nUser-Agent: Apache-HttpClient/4.3.5 (java 1.5)\nAccept-Encoding: gzip,deflate";
//        RequestFilter getJPEGFilter = new RequestFilter(requestHead);
//        assertEquals("HTTP/1.1 200 OK", getJPEGFilter.createByType().startLine);
//    }
//
//    @Test
//    public void requestFilterCreatesCorrectRequestDependingOnFileWithNoSuffix() throws IOException {
//        String requestHead = "GET /file1 HTTP/1.1\nHost: localhost:5000\nConnection: Keep-Alive\nUser-Agent: Apache-HttpClient/4.3.5 (java 1.5)\nAccept-Encoding: gzip,deflate";
//        RequestFilter getFileFilter = new RequestFilter(requestHead);
//        assertEquals("HTTP/1.1 200 OK", getFileFilter.createByType().startLine);
//    }
}
