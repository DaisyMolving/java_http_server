package server;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ResponseTest {

    @Test
    public void createsAppropriateResponseContent() {
        Request request = new Request("GET /coffee HTTP/1.1");
        Response response = new Response(request);
        List<String> appropriateResponseElements = Arrays.asList("HTTP/1.1 418 I'm a teapot", "Location: ", "Allow: ", "\n", "I'm a teapot");
        assertEquals(appropriateResponseElements, response.generate());
    }
}
