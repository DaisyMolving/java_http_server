package server;

import org.junit.Test;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RequestReaderTest {

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
    public void splitsStartLineIntoParts() throws IOException {
        RequestReader requestReader = new RequestReader(createBufferedReader(getRequestString + "\n"));
        assertEquals("HTTP/1.1", requestReader.getProtocolVersion());
        assertEquals("GET", requestReader.getMethod());
    }

    @Test
    public void findsRequestBodyIfItExists() throws IOException {

        RequestReader readRequestWithBody = new RequestReader(createBufferedReader(postRequestString));
        assertEquals("data\nhello", readRequestWithBody.getBody());

        RequestReader readRequestWithoutBody = new RequestReader(createBufferedReader(getRequestString + "\n"));
        assertEquals("", readRequestWithoutBody.getBody());
    }

    private BufferedReader createBufferedReader(String requestString) {
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(requestString.getBytes())));
    }
}
