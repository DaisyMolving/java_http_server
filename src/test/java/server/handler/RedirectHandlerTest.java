package server.handler;

import org.junit.Test;
import server.request.BogusRequest;
import server.request.MethodNotAllowedRequest;
import server.request.RedirectRequest;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class RedirectHandlerTest {

    @Test
    public void redirectHandlerSendsRedirectRequestIfMethodIsValid() {
        RedirectHandler redirectHandlerGet = new RedirectHandler(setParameters("GET", "HTTP/1.1"));
        assertTrue(redirectHandlerGet.send() instanceof RedirectRequest);

        RedirectHandler redirectHandlerPost = new RedirectHandler(setParameters("POST", "HTTP/1.1"));
        assertTrue(redirectHandlerPost.send() instanceof BogusRequest);

        RedirectHandler redirectHandlerPut = new RedirectHandler(setParameters("PUT", "HTTP/1.1"));
        assertTrue(redirectHandlerPut.send() instanceof BogusRequest);

        RedirectHandler redirectHandlerHead = new RedirectHandler(setParameters("HEAD", "HTTP/1.1"));
        assertTrue(redirectHandlerHead.send() instanceof BogusRequest);

        RedirectHandler redirectHandlerBogus = new RedirectHandler(setParameters("KDAJKFJDS", "HTTP/1.1"));
        assertTrue(redirectHandlerBogus.send() instanceof MethodNotAllowedRequest);
    }

    private HashMap<String, String> setParameters(String method, String protocolVersion) {
        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("Method", method);
        requestParameters.put("Protocol Version", protocolVersion);
        return requestParameters;
    }
}
