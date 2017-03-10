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
                "",
                "",
                "/Users/daisymolving/Documents/Apprenticeship/cob_spec/public/",
                "file1");
        String appropriateContent = "HTTP/1.1 200 OK\nAllow: \nAccept-Range: bytes\nContent-Type: \nLocation: http://localhost:5000\nContent-Range: \n\nfile1 contents";
        assertEquals(appropriateContent, new String(response.generateContent()));
    }

    @Test
    public void findsCorrectRangeWhenEndAndBeginning() throws IOException {
        Response response = new Response("HTTP/1.1 206 Partial Content",
                "",
                "",
                "bytes=0-4",
                "",
                "",
                "/Users/daisymolving/Documents/Apprenticeship/cob_spec/public/",
                "partial_content.txt");
        assertEquals(0, response.getRange(77)[0]);
        assertEquals(4, response.getRange(77)[1]);
    }

    @Test
    public void findsCorrectRangeWhenNoEnd() throws IOException {
        Response response = new Response("HTTP/1.1 206 Partial Content",
                "",
                "",
                "bytes=4-",
                "",
                "",
                "/Users/daisymolving/Documents/Apprenticeship/cob_spec/public/",
                "partial_content.txt");
        assertEquals(4, response.getRange(77)[0]);
        assertEquals(77, response.getRange(77)[1]);
    }

    @Test
    public void findsCorrectRangeWhenNoBeginning() throws IOException {
        Response response = new Response("HTTP/1.1 206 Partial Content",
                "",
                "",
                "bytes=-6",
                "",
                "",
                "/Users/daisymolving/Documents/Apprenticeship/cob_spec/public/",
                "partial_content.txt");
        assertEquals(71, response.getRange(77)[0]);
        assertEquals(77, response.getRange(77)[1]);
    }

    @Test
    public void generatesPartialContent() throws IOException {
        Response response = new Response("HTTP/1.1 200 OK",
                "",
                "",
                "bytes=-6",
                "",
                "",
                "/Users/daisymolving/Documents/Apprenticeship/cob_spec/public/",
                "partial_content.txt");
        String appropriateContent = "HTTP/1.1 200 OK\nAllow: \nAccept-Range: bytes\nContent-Type: \nLocation: http://localhost:5000\nContent-Range: \n\n 206.\n";
        assertEquals(appropriateContent, new String(response.generateContent()));
    }

}
