package server;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ResponseTest {

    @Test
    public void generatesResponseContent() throws IOException {
        Response response = new Response("HTTP/1.1 200 OK",
                "",
                "",
                "",
                "file1 contents".getBytes());
        String appropriateContent = "HTTP/1.1 200 OK\nAllow: \nContent-Type: \nLocation: http://localhost:5000\nSet-Cookie: \n\nfile1 contents";
        assertEquals(appropriateContent, new String(response.generateContent()));
    }

}