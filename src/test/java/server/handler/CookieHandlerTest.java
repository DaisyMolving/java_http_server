package server.handler;

import org.junit.Test;
import server.DataStore;
import server.request.CookieRequest;
import server.request.MethodNotAllowedRequest;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class CookieHandlerTest {

    @Test
    public void cookieHandlerSendsCookieRequestIfMethodIsValid() {
        CookieHandler cookieHandlerGet = new CookieHandler(setParameters("GET", "HTTP/1.1", "/cookie?type=chocolate"), new DataStore());
        assertTrue(cookieHandlerGet.send() instanceof CookieRequest);

        CookieHandler cookieHandlerBogus = new CookieHandler(setParameters("KDAJKFJDS", "HTTP/1.1", "/cookie?type=chocolate"), new DataStore());
        assertTrue(cookieHandlerBogus.send() instanceof MethodNotAllowedRequest);
    }

    private HashMap<String, String> setParameters(String method, String protocolVersion, String path) {
        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("Method", method);
        requestParameters.put("Protocol Version", protocolVersion);
        requestParameters.put("Path", path);
        return requestParameters;
    }
}
