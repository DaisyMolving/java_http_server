package server;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SuccessResponseTest {

    @Test
    public void createsAppropriateResponseContent() {
        Request request = new GetRequest("GET / HTTP/1.1");
        Response response = new SuccessResponse(request.getProtocolVersion());
        List<String> appropriateResponseElements = Arrays.asList("HTTP/1.1 200 OK", "Location: ", "Allow: ", "\n", "");
        assertEquals(appropriateResponseElements, response.generateContent());
    }
}
