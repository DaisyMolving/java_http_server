package server;

import org.junit.Test;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RequestReaderTest {

    private String getPartialContentString =
            "GET /partial-content.txt HTTP/1.1\n" +
                    "Range: bytes=2-7\n" +
                    "Host: localhost:5000\n" +
                    "Connection: Keep-Alive\n" +
                    "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                    "Accept-Encoding: gzip,deflate\n";

    private String getRequestString =
            "GET / HTTP/1.1\n" +
            "Host: localhost:5000\n" +
            "Connection: Keep-Alive\n" +
            "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
            "Accept-Encoding: gzip,deflate\n";

    private String postRequestString =
            "POST /form HTTP/1.1\n" +
            "Content-Length: 10\n" +
            "Host: localhost:5000\n" +
            "Connection: Keep-Alive\n" +
            "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
            "Accept-Encoding: gzip,deflate\n\n" +
            "data\nhello";

    @Test
    public void splitsRequestIntoParts() throws IOException {
        RequestReader requestReaderGet = new RequestReader(createBufferedReader(getRequestString + "\n"));
        assertTrue(requestReaderGet.getRequestParameters().containsValue("/"));
        assertTrue(requestReaderGet.getRequestParameters().containsValue("HTTP/1.1"));
        assertTrue(requestReaderGet.getRequestParameters().containsValue("GET"));
        assertFalse(requestReaderGet.getRequestParameters().containsKey("Request Body"));

        RequestReader requestReaderPost = new RequestReader(createBufferedReader(postRequestString));
        assertTrue(requestReaderPost.getRequestParameters().containsValue("/form"));
        assertTrue(requestReaderPost.getRequestParameters().containsValue("HTTP/1.1"));
        assertTrue(requestReaderPost.getRequestParameters().containsValue("POST"));
        assertTrue(requestReaderPost.getRequestParameters().containsValue("data\nhello"));
    }

    @Test
    public void findsRequestBodyIfItExists() throws IOException {
        RequestReader readRequestWithBody = new RequestReader(createBufferedReader(postRequestString));
        assertEquals("data\nhello", readRequestWithBody.getRequestParameters().get("Request Body"));

        RequestReader readRequestWithoutBody = new RequestReader(createBufferedReader(getRequestString + "\n"));
        assertEquals(null, readRequestWithoutBody.getRequestParameters().get("Request Body"));
    }

    @Test
    public void findsRangeForPartialContentIfPresent() throws IOException {
        RequestReader readRequestWithRange = new RequestReader(createBufferedReader(getPartialContentString + "\n"));
        assertEquals("bytes=2-7", readRequestWithRange.getRequestParameters().get("Range"));
    }

    private BufferedReader createBufferedReader(String requestString) {
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(requestString.getBytes())));
    }
}