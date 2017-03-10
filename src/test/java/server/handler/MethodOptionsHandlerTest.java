package server.handler;

import org.junit.Test;
import server.request.MethodNotAllowedRequest;
import server.request.MethodOptionsRequest;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class MethodOptionsHandlerTest {

    @Test
    public void methodOptionsHandlerSendsMethodOptionsRequestIfMethodIsValid() {
        MethodOptionsHandler methodOptionsHandlerGet = new MethodOptionsHandler(setParameters("GET", "HTTP/1.1"));
        assertTrue(methodOptionsHandlerGet.send() instanceof MethodOptionsRequest);

        MethodOptionsHandler methodOptionsHandlerPost = new MethodOptionsHandler(setParameters("POST", "HTTP/1.1"));
        assertTrue(methodOptionsHandlerPost.send() instanceof MethodOptionsRequest);

        MethodOptionsHandler methodOptionsHandlerPut = new MethodOptionsHandler(setParameters("PUT", "HTTP/1.1"));
        assertTrue(methodOptionsHandlerPut.send() instanceof MethodOptionsRequest);

        MethodOptionsHandler methodOptionsHandlerHead = new MethodOptionsHandler(setParameters("HEAD", "HTTP/1.1"));
        assertTrue(methodOptionsHandlerHead.send() instanceof MethodOptionsRequest);

        MethodOptionsHandler methodOptionsHandlerBogus = new MethodOptionsHandler(setParameters("KDAJKFJDS", "HTTP/1.1"));
        assertTrue(methodOptionsHandlerBogus.send() instanceof MethodNotAllowedRequest);
    }

    private HashMap<String, String> setParameters(String method, String protocolVersion) {
        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("Method", method);
        requestParameters.put("Protocol Version", protocolVersion);
        return requestParameters;
    }

}
