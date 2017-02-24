package server;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GetRequestTest {

    @Test
    public void createsAppropriateResponseFromPathForSimpleGet() {
        String requestInput = "GET / HTTP/1.1";
        Request request = new GetRequest("/", "HTTP/1.1");
        assertTrue(request.createResponse() instanceof SuccessResponse);
    }

    @Test
    public void createsAppropriateResponseFromPathToValidFiles() {
        Request teaRequest = new GetRequest("/tea", "HTTP/1.1");
        assertTrue(teaRequest.createResponse() instanceof SuccessResponse);

        Request fileRequest = new GetRequest("/file1", "HTTP/1.1");
        assertTrue(fileRequest.createResponse() instanceof SuccessResponse);

        Request textFileRequest = new GetRequest("/text-file.txt", "HTTP/1.1");
        assertTrue(textFileRequest.createResponse() instanceof SuccessResponse);
    }

    @Test
    public void createsAppropriateResponseFromPathForRedirect() {
        Request request = new GetRequest("/redirect", "HTTP/1.1");
        assertTrue(request.createResponse() instanceof RedirectResponse);
    }

    @Test
    public void createsAppropriateResponseFromPathForTeapotFourEighteen() {
        Request request = new GetRequest("/coffee", "HTTP/1.1");
        assertTrue(request.createResponse() instanceof TeapotResponse);
    }

    @Test
    public void createsAppropriateResponseFromPathForNotFound() {
        Request request = new GetRequest("/bogus", "HTTP/1.1");
        assertTrue(request.createResponse() instanceof NotFoundResponse);
    }
}
