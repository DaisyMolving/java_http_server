package server;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SuccessResponseTest {

    @Test
    public void createsAppropriateResponseContent() {
        Request request = new GetRequest("/", "HTTP/1.1");
        Response response = new SuccessResponse(request.getProtocolVersion());
        String appropriateResponseElements = "HTTP/1.1 200 OK\nLocation: http://localhost:5000/Users/daisymolving/Documents/Apprenticeship/cob_spec/public/\nAllow: \n\n";
        assertEquals(appropriateResponseElements, response.generateContent());
    }

    @Test
    public void createsAppropriateResponseContentForOption() {
        Request request = new OptionRequest("/method_options", "HTTP/1.1");
        Response response = new SuccessResponse(request.getProtocolVersion(), request.getPath());
        String appropriateResponseElements = "HTTP/1.1 200 OK\nLocation: http://localhost:5000/Users/daisymolving/Documents/Apprenticeship/cob_spec/public/\nAllow: GET,POST,OPTIONS,HEAD,PUT\n\n";
        assertEquals(appropriateResponseElements, response.generateContent());
    }
}