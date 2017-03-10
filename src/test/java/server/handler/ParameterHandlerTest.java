package server.handler;

import org.junit.Test;
import server.request.BogusRequest;
import server.request.MethodNotAllowedRequest;
import server.request.ParameterRequest;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParameterHandlerTest {

    @Test
    public void parameterHandlerSendsParameterRequestIfMethodIsValid() {
        ParameterHandler parameterHandlerGet = new ParameterHandler(setParameters("GET", "HTTP/1.1", "/parameters?this=hi"));
        assertTrue(parameterHandlerGet.send() instanceof ParameterRequest);
        ParameterHandler parameterHandlerPost = new ParameterHandler(setParameters("POST", "HTTP/1.1", "/parameters?this=hi"));
        assertTrue(parameterHandlerPost.send() instanceof BogusRequest);
        ParameterHandler parameterHandlerPut = new ParameterHandler(setParameters("PUT", "HTTP/1.1", "/parameters?this=hi"));
        assertTrue(parameterHandlerPut.send() instanceof BogusRequest);
        ParameterHandler parameterHandlerHead = new ParameterHandler(setParameters("HEAD", "HTTP/1.1", "/parameters?this=hi"));
        assertTrue(parameterHandlerHead.send() instanceof BogusRequest);
        ParameterHandler parameterHandlerBogus = new ParameterHandler(setParameters("KDAJKFJDS", "HTTP/1.1", "/parameters?this=hi"));
        assertTrue(parameterHandlerBogus.send() instanceof MethodNotAllowedRequest);
    }

    @Test
    public void takesURLParametersFromPath() {
        ParameterHandler parameterHandlerGet = new ParameterHandler(setParameters("GET", "HTTP/1.1", "/parameters?this=fivehundred&SEVENTY\"?\""));
        assertEquals("this=fivehundred&SEVENTY\"?\"", parameterHandlerGet.getURLParametersFromPath("/parameters?this=fivehundred&SEVENTY\"?\""));

    }

    private HashMap<String, String> setParameters(String method, String protocolVersion, String path) {
        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("Method", method);
        requestParameters.put("Protocol Version", protocolVersion);
        requestParameters.put("Path", path);
        return requestParameters;
    }
}
