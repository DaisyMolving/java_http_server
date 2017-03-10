package server.handler;

import org.junit.Test;
import server.request.MethodNotAllowedRequest;
import server.request.MethodOptionsTwoRequest;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class MethodOptionsHandlerTwoTest {

    @Test
    public void methodOptionsTwoHandlerSendsMethodOptionsTwoRequestIfMethodIsValid() {
        MethodOptionsTwoHandler methodOptionsTwoHandlerGet = new MethodOptionsTwoHandler(setParameters("GET", "HTTP/1.1"));
        assertTrue(methodOptionsTwoHandlerGet.send() instanceof MethodOptionsTwoRequest);

        MethodOptionsTwoHandler methodOptionsTwoHandlerPost = new MethodOptionsTwoHandler(setParameters("POST", "HTTP/1.1"));
        assertTrue(methodOptionsTwoHandlerPost.send() instanceof MethodOptionsTwoRequest);

        MethodOptionsTwoHandler methodOptionsTwoHandlerPut = new MethodOptionsTwoHandler(setParameters("PUT", "HTTP/1.1"));
        assertTrue(methodOptionsTwoHandlerPut.send() instanceof MethodOptionsTwoRequest);

        MethodOptionsTwoHandler methodOptionsTwoHandlerHead = new MethodOptionsTwoHandler(setParameters("HEAD", "HTTP/1.1"));
        assertTrue(methodOptionsTwoHandlerHead.send() instanceof MethodOptionsTwoRequest);

        MethodOptionsTwoHandler methodOptionsTwoHandlerBogus = new MethodOptionsTwoHandler(setParameters("KDAJKFJDS", "HTTP/1.1"));
        assertTrue(methodOptionsTwoHandlerBogus.send() instanceof MethodNotAllowedRequest);
    }

    private HashMap<String, String> setParameters(String method, String protocolVersion) {
        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("Method", method);
        requestParameters.put("Protocol Version", protocolVersion);
        return requestParameters;
    }
}
