package server.handler;

import org.junit.Test;
import server.request.BogusRequest;
import server.request.MethodNotAllowedRequest;

import static org.junit.Assert.assertTrue;

public class ErrorHandlerTest {

    @Test
    public void errorHandlerSendsBogusRequestIfMethodIsValid() {
        ErrorHandler errorHandlerGet = new ErrorHandler("GET", "HTTP/1.1");
        assertTrue(errorHandlerGet.send() instanceof BogusRequest);

        ErrorHandler errorHandlerPost = new ErrorHandler("POST", "HTTP/1.1");
        assertTrue(errorHandlerPost.send() instanceof BogusRequest);

        ErrorHandler errorHandlerPut = new ErrorHandler("PUT", "HTTP/1.1");
        assertTrue(errorHandlerPut.send() instanceof BogusRequest);

        ErrorHandler errorHandlerHead = new ErrorHandler("HEAD", "HTTP/1.1");
        assertTrue(errorHandlerHead.send() instanceof BogusRequest);

        ErrorHandler errorHandlerBogus = new ErrorHandler("KDAJKFJDS", "HTTP/1.1");
        assertTrue(errorHandlerBogus.send() instanceof MethodNotAllowedRequest);
    }
}