package server;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SuccessResponseTest {

    @Test
    public void createsAppropriateResponseContent() throws IOException {
        Request request = new GetRequest("/", "HTTP/1.1");
        SuccessResponse response = new SuccessResponse("HTTP/1.1");
        String appropriateResponseHead = "HTTP/1.1 200 OK\nLocation: \nAllow: \nContent-Type: \n\n";
        String appropriateResponseBody = "";
        assertEquals(appropriateResponseHead, new String(response.generateHead()));
        assertEquals(appropriateResponseBody, new String(response.generateBody()));
    }

    @Test
    public void createsAppropriateResponseContentForOptions() throws IOException {
        Request request = new OptionRequest("/method_options", "HTTP/1.1");
        SuccessResponse response = new SuccessResponse("HTTP/1.1", "/method_options");
        String appropriateResponseHead = "HTTP/1.1 200 OK\nLocation: \nAllow: GET,POST,OPTIONS,HEAD,PUT\nContent-Type: \n\n";
        String appropriateResponseBody = "";
        assertEquals(appropriateResponseHead, new String(response.generateHead()));
        assertEquals(appropriateResponseBody, new String(response.generateBody()));
    }

    @Test
    public void createsAppropriateResponseContentForOptionsTwo() throws IOException {
        Request request = new OptionRequest("/method_options2", "HTTP/1.1");
        SuccessResponse response = new SuccessResponse("HTTP/1.1", "/method_options2");
        String appropriateResponseHead = "HTTP/1.1 200 OK\nLocation: \nAllow: GET,OPTIONS\nContent-Type: \n\n";
        String appropriateResponseBody = "";
        assertEquals(appropriateResponseHead, new String(response.generateHead()));
        assertEquals(appropriateResponseBody, new String(response.generateBody()));
    }

    @Test
    public void createsAppropriateResponseContentForJPEG() throws IOException {
        Request request = new GetRequest("/image.jpeg", "HTTP/1.1");
        SuccessResponse response = new SuccessResponse("HTTP/1.1", "/image.jpeg");
        String appropriateResponseHead = "HTTP/1.1 200 OK\nLocation: \nAllow: \nContent-Type: image/jpeg\n\n";
        String appropriateResponseBody = "";
        assertEquals(appropriateResponseHead, new String(response.generateHead()));
        assertFalse(new String(response.generateBody()) == appropriateResponseBody);
    }

    @Test
    public void createsAppropriateResponseContentForGIF() throws IOException {
        Request request = new GetRequest("/image.gif", "HTTP/1.1");
        SuccessResponse response = new SuccessResponse("HTTP/1.1", "/image.gif");
        String appropriateResponseHead = "HTTP/1.1 200 OK\nLocation: \nAllow: \nContent-Type: image/gif\n\n";
        String appropriateResponseBody = "";
        assertEquals(appropriateResponseHead, new String(response.generateHead()));
        assertFalse(new String(response.generateBody()) == appropriateResponseBody);
    }

    @Test
    public void createsAppropriateResponseContentForPNG() throws IOException {
        Request request = new GetRequest("/image.png", "HTTP/1.1");
        SuccessResponse response = new SuccessResponse("HTTP/1.1", "/image.png");
        String appropriateResponseHead = "HTTP/1.1 200 OK\nLocation: \nAllow: \nContent-Type: image/png\n\n";
        String appropriateResponseBody = "";
        assertEquals(appropriateResponseHead, new String(response.generateHead()));
        assertFalse(new String(response.generateBody()) == appropriateResponseBody);
    }

    @Test
    public void createsAppropriateResponseContentForTextFile() throws IOException {
        Request request = new GetRequest("/text-file.txt", "HTTP/1.1");
        SuccessResponse response = new SuccessResponse("HTTP/1.1", "/text-file.txt");
        String appropriateResponseHead = "HTTP/1.1 200 OK\nLocation: \nAllow: \nContent-Type: text/plain\n\n";
        String appropriateResponseBody = "file1 contents";
        assertEquals(appropriateResponseHead, new String(response.generateHead()));
        assertEquals(appropriateResponseBody, new String(response.generateBody()));
    }

    @Test
    public void createsAppropriateResponseContentForFile() throws IOException {
        Request request = new GetRequest("/file1", "HTTP/1.1");
        SuccessResponse response = new SuccessResponse("HTTP/1.1", "/file1");
        String appropriateResponseHead = "HTTP/1.1 200 OK\nLocation: \nAllow: \nContent-Type: \n\n";
        String appropriateResponseBody = "file1 contents";
        assertEquals(appropriateResponseHead, new String(response.generateHead()));
        assertEquals(appropriateResponseBody, new String(response.generateBody()));
    }
}