package server;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TeapotResponseTest {


    @Test
    public void createsAppropriateResponseContent() {
        Request request = new GetRequest("/coffee", "HTTP/1.1");
        Response response = new TeapotResponse(request.getProtocolVersion());
        String appropriateResponse = "HTTP/1.1 418 I'm a teapot\nLocation: \nAllow: \n\nI'm a teapot";
        assertEquals(appropriateResponse, response.generateContent());
    }
}
