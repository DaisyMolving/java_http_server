package server;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GetRequestTest {

    @Test
    public void createsAppropriateResponseFromPathForSimpleGet() {
        String requestInput = "GET / HTTP/1.1";
        Request request = new GetRequest(requestInput);
        assertTrue(request.createResponse() instanceof SuccessResponse);
    }

    @Test
    public void createsAppropriateResponseFromPathToValidFiles() {
        String teaRequestInput = "GET /tea HTTP/1.1";
        Request teaRequest = new GetRequest(teaRequestInput);
        assertTrue(teaRequest.createResponse() instanceof SuccessResponse);

        String fileRequestInput = "GET /file1 HTTP/1.1";
        Request fileRequest = new GetRequest(fileRequestInput);
        assertTrue(fileRequest.createResponse() instanceof SuccessResponse);

        String textFileRequestInput = "GET /text-file.txt HTTP/1.1";
        Request textFileRequest = new GetRequest(textFileRequestInput);
        assertTrue(textFileRequest.createResponse() instanceof SuccessResponse);
    }

    @Test
    public void createsAppropriateResponseFromPathForRedirect() {
        String requestInput = "GET /redirect HTTP/1.1";
        Request request = new GetRequest(requestInput);
        assertTrue(request.createResponse() instanceof RedirectResponse);
    }

    @Test
    public void createsAppropriateResponseFromPathForTeapotFourEighteen() {
        String requestInput = "GET /coffee HTTP/1.1";
        Request request = new GetRequest(requestInput);
        assertTrue(request.createResponse() instanceof TeapotResponse);
    }

    @Test
    public void createsAppropriateResponseFromPathForNotFound() {
        String requestInput = "GET /bogus HTTP/1.1";
        Request request = new GetRequest(requestInput);
        assertTrue(request.createResponse() instanceof NotFoundResponse);
    }
}
