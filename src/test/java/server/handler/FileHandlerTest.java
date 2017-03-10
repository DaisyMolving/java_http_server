package server.handler;

import org.junit.Test;
import server.request.FileRequest;
import server.request.MethodNotAllowedRequest;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileHandlerTest {

    @Test
    public void indexHandlerSendsIndexRequestIfMethodIsValid() {
        FileHandler fileHandlerGet = new FileHandler(setParameters("GET", "HTTP/1.1", "/file1"));
        assertTrue(fileHandlerGet.send() instanceof FileRequest);

        FileHandler fileHandlerPost = new FileHandler(setParameters("POST", "HTTP/1.1", "/file1"));
        assertTrue(fileHandlerPost.send() instanceof MethodNotAllowedRequest);

        FileHandler fileHandlerPut = new FileHandler(setParameters("PUT", "HTTP/1.1", "/file1"));
        assertTrue(fileHandlerPut.send() instanceof MethodNotAllowedRequest);

        FileHandler fileHandlerHead = new FileHandler(setParameters("HEAD", "HTTP/1.1", "/file1"));
        assertTrue(fileHandlerHead.send() instanceof MethodNotAllowedRequest);

        FileHandler fileHandlerBogus = new FileHandler(setParameters("KDAJKFJDS", "HTTP/1.1", "/file1"));
        assertTrue(fileHandlerBogus.send() instanceof MethodNotAllowedRequest);
    }

    @Test
    public void fileNameIsFoundInPath() {
        FileHandler fileHandlerGet = new FileHandler(setParameters("GET", "HTTP/1.1", "/file1"));
        assertEquals("file1", fileHandlerGet.getFileName("/file1"));
    }

    private HashMap<String, String> setParameters(String method, String protocolVersion, String path) {
        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("Method", method);
        requestParameters.put("Protocol Version", protocolVersion);
        requestParameters.put("Path", path);
        return requestParameters;
    }
}