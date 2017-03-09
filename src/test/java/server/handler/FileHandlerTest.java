package server.handler;

import org.junit.Test;
import server.request.FileRequest;
import server.request.MethodNotAllowedRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileHandlerTest {

    @Test
    public void indexHandlerSendsIndexRequestIfMethodIsValid() {
        FileHandler fileHandlerGet = new FileHandler("GET", "HTTP/1.1", "/file1");
        assertTrue(fileHandlerGet.send() instanceof FileRequest);

        FileHandler fileHandlerPost = new FileHandler("POST", "HTTP/1.1", "/file1");
        assertTrue(fileHandlerPost.send() instanceof MethodNotAllowedRequest);

        FileHandler fileHandlerPut = new FileHandler("PUT", "HTTP/1.1", "/file1");
        assertTrue(fileHandlerPut.send() instanceof MethodNotAllowedRequest);

        FileHandler fileHandlerHead = new FileHandler("HEAD", "HTTP/1.1", "/file1");
        assertTrue(fileHandlerHead.send() instanceof MethodNotAllowedRequest);

        FileHandler fileHandlerBogus = new FileHandler("KDAJKFJDS", "HTTP/1.1", "/file1");
        assertTrue(fileHandlerBogus.send() instanceof MethodNotAllowedRequest);
    }

    @Test
    public void fileNameIsFoundInPath() {
        FileHandler fileHandlerGet = new FileHandler("GET", "HTTP/1.1", "/file1");
        assertEquals("file1", fileHandlerGet.getFileName("/file1"));
    }
}