package server.handler;

import org.junit.Test;
import server.request.BogusRequest;
import server.request.MethodNotAllowedRequest;
import server.request.RedirectRequest;

import static org.junit.Assert.assertTrue;

public class RedirectHandlerTest {

    @Test
    public void redirectHandlerSendsRedirectRequestIfMethodIsValid() {
        RedirectHandler redirectHandlerGet = new RedirectHandler("GET", "HTTP/1.1");
        assertTrue(redirectHandlerGet.send() instanceof RedirectRequest);

        RedirectHandler redirectHandlerPost = new RedirectHandler("POST", "HTTP/1.1");
        assertTrue(redirectHandlerPost.send() instanceof BogusRequest);

        RedirectHandler redirectHandlerPut = new RedirectHandler("PUT", "HTTP/1.1");
        assertTrue(redirectHandlerPut.send() instanceof BogusRequest);

        RedirectHandler redirectHandlerHead = new RedirectHandler("HEAD", "HTTP/1.1");
        assertTrue(redirectHandlerHead.send() instanceof BogusRequest);

        RedirectHandler redirectHandlerBogus = new RedirectHandler("KDAJKFJDS", "HTTP/1.1");
        assertTrue(redirectHandlerBogus.send() instanceof MethodNotAllowedRequest);
    }
}
