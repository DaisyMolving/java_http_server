package server;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SuccessResponseTest {

    @Test
    public void createsAppropriateResponseContent() throws IOException {
        SuccessResponse response = new SuccessResponse("HTTP/1.1");
        assertEquals(
                createAppropriateHead("", ""),
                new String(response.generateHead()));

        assertEquals("", new String(response.generateBody()));
    }

    @Test
    public void createsAppropriateResponseContentForOptions() throws IOException {
        SuccessResponse response = new SuccessResponse("HTTP/1.1", "/method_options");
        assertEquals(
                createAppropriateHead("GET,POST,OPTIONS,HEAD,PUT", ""),
                new String(response.generateHead()));

        assertEquals("", new String(response.generateBody()));
    }

    @Test
    public void createsAppropriateResponseContentForOptionsTwo() throws IOException {
        SuccessResponse response = new SuccessResponse("HTTP/1.1", "/method_options2");
        assertEquals(
                createAppropriateHead("GET,OPTIONS", ""),
                new String(response.generateHead()));

        assertEquals("", new String(response.generateBody()));
    }

    @Test
    public void createsAppropriateResponseContentForJPEG() throws IOException {
        SuccessResponse response = new SuccessResponse("HTTP/1.1", "/image.jpeg");
        assertEquals(
                createAppropriateHead("", "image/jpeg"),
                new String(response.generateHead()));

        assertTrue(isNotEmpty(response.generateBody()));
    }

    @Test
    public void createsAppropriateResponseContentForGIF() throws IOException {
        SuccessResponse response = new SuccessResponse("HTTP/1.1", "/image.gif");
        assertEquals(
                createAppropriateHead("", "image/gif"),
                new String(response.generateHead()));

        assertTrue(isNotEmpty(response.generateBody()));
    }

    @Test
    public void createsAppropriateResponseContentForPNG() throws IOException {
        SuccessResponse response = new SuccessResponse("HTTP/1.1", "/image.png");
        assertEquals(
                createAppropriateHead("", "image/png"),
                new String(response.generateHead()));

        assertTrue(isNotEmpty(response.generateBody()));
    }

    @Test
    public void createsAppropriateResponseContentForTextFile() throws IOException {
        SuccessResponse response = new SuccessResponse("HTTP/1.1", "/text-file.txt");
        assertEquals(
                createAppropriateHead("", "text/plain"),
                new String(response.generateHead()));

        assertEquals("file1 contents", new String(response.generateBody()));
    }

    @Test
    public void createsAppropriateResponseContentForFile() throws IOException {
        SuccessResponse response = new SuccessResponse("HTTP/1.1", "/file1");
        assertEquals(
                createAppropriateHead("", ""),
                new String(response.generateHead()));

        assertEquals("file1 contents", new String(response.generateBody()));
    }

    private String createAppropriateHead(String allow, String contentType) {
        return "HTTP/1.1 200 OK\nLocation: \nAllow: " + allow + "\nContent-Type: " + contentType + "\n\n";
    }

    private boolean isNotEmpty(byte[] responseBody) {
        return new String(responseBody) != "";
    }

}
