package server.handler;

import org.junit.Test;
import server.request.MethodNotAllowedRequest;
import server.request.MethodOptionsRequest;

import static org.junit.Assert.assertTrue;

public class MethodOptionsHandlerTest {

    @Test
    public void methodOptionsHandlerSendsMethodOptionsRequestIfMethodIsValid() {
        MethodOptionsHandler methodOptionsHandlerGet = new MethodOptionsHandler("GET", "HTTP/1.1");
        assertTrue(methodOptionsHandlerGet.send() instanceof MethodOptionsRequest);

        MethodOptionsHandler methodOptionsHandlerPost = new MethodOptionsHandler("POST", "HTTP/1.1");
        assertTrue(methodOptionsHandlerPost.send() instanceof MethodOptionsRequest);

        MethodOptionsHandler methodOptionsHandlerPut = new MethodOptionsHandler("PUT", "HTTP/1.1");
        assertTrue(methodOptionsHandlerPut.send() instanceof MethodOptionsRequest);

        MethodOptionsHandler methodOptionsHandlerHead = new MethodOptionsHandler("HEAD", "HTTP/1.1");
        assertTrue(methodOptionsHandlerHead.send() instanceof MethodOptionsRequest);

        MethodOptionsHandler methodOptionsHandlerBogus = new MethodOptionsHandler("KDAJKFJDS", "HTTP/1.1");
        assertTrue(methodOptionsHandlerBogus.send() instanceof MethodNotAllowedRequest);
    }
}
