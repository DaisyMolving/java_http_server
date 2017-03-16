package server;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ResponseTest {

    private List<String> headerFields = new ArrayList<>();

    @Test
    public void generatesResponseContent() throws IOException {
        headerFields.add("HTTP/1.1 200 OK");
        Response response = new Response(headerFields, "file1 contents".getBytes());
        String appropriateContent = "HTTP/1.1 200 OK\n\nfile1 contents";
        assertEquals(appropriateContent, new String(response.generateContent()));
    }

}