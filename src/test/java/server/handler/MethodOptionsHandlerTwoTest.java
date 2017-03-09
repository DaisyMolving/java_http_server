package server.handler;

import org.junit.Test;
import server.request.MethodNotAllowedRequest;
import server.request.MethodOptionsTwoRequest;

import static org.junit.Assert.assertTrue;

public class MethodOptionsHandlerTwoTest {

    @Test
    public void methodOptionsTwoHandlerSendsMethodOptionsTwoRequestIfMethodIsValid() {
        MethodOptionsTwoHandler methodOptionsTwoHandlerGet = new MethodOptionsTwoHandler("GET", "HTTP/1.1");
        assertTrue(methodOptionsTwoHandlerGet.send() instanceof MethodOptionsTwoRequest);

        MethodOptionsTwoHandler methodOptionsTwoHandlerPost = new MethodOptionsTwoHandler("POST", "HTTP/1.1");
        assertTrue(methodOptionsTwoHandlerPost.send() instanceof MethodOptionsTwoRequest);

        MethodOptionsTwoHandler methodOptionsTwoHandlerPut = new MethodOptionsTwoHandler("PUT", "HTTP/1.1");
        assertTrue(methodOptionsTwoHandlerPut.send() instanceof MethodOptionsTwoRequest);

        MethodOptionsTwoHandler methodOptionsTwoHandlerHead = new MethodOptionsTwoHandler("HEAD", "HTTP/1.1");
        assertTrue(methodOptionsTwoHandlerHead.send() instanceof MethodOptionsTwoRequest);

        MethodOptionsTwoHandler methodOptionsTwoHandlerBogus = new MethodOptionsTwoHandler("KDAJKFJDS", "HTTP/1.1");
        assertTrue(methodOptionsTwoHandlerBogus.send() instanceof MethodNotAllowedRequest);
    }
}
