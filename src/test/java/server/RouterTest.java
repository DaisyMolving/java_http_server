package server;

import org.junit.Test;
import server.handler.*;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class RouterTest {

    private Router router = new Router();
    private DataStore dataStore = new DataStore();
    private DataStore cookieStore = new DataStore();
    private RequestLogStore requestLogStore = new RequestLogStore();

    @Test
    public void routesToIndexHandler() throws IOException {
        Handler routeToHandlerForGet = router.routeNewRequest(setParameters("GET", "/", "", "HTTP/1.1"), dataStore, cookieStore, requestLogStore);
        assertTrue(routeToHandlerForGet instanceof IndexHandler);

        Handler routeToHandlerForHead = router.routeNewRequest(setParameters("HEAD", "/", "", "HTTP/1.1"), dataStore, cookieStore, requestLogStore);
        assertTrue(routeToHandlerForHead instanceof IndexHandler);
    }

    @Test
    public void routesToFormHandler() throws IOException {

        Handler routeToHandlerForPost = router.routeNewRequest(setParameters("POST", "/form", "data=fatcat", "HTTP/1.1"), dataStore, cookieStore, requestLogStore);
        assertTrue(routeToHandlerForPost instanceof FormHandler);

        Handler routeToHandlerForPut = router.routeNewRequest(setParameters("PUT", "/form", "data=heathcliff", "HTTP/1.1"), dataStore, cookieStore, requestLogStore);
        assertTrue(routeToHandlerForPut instanceof FormHandler);
    }

    @Test
    public void routesToRedirectHandler() throws IOException {
        Handler routeToHandler = router.routeNewRequest(setParameters("GET", "/redirect", "", "HTTP/1.1"), dataStore, cookieStore, requestLogStore);
        assertTrue(routeToHandler instanceof RedirectHandler);
    }

    @Test
    public void routesToFileHandler() throws IOException {
        Handler routeToHandlerForJPEG = router.routeNewRequest(setParameters("GET", "/image.jpeg", "", "HTTP/1.1"), dataStore, cookieStore, requestLogStore);
        assertTrue(routeToHandlerForJPEG instanceof FileHandler);

        Handler routeToHandlerForPNG = router.routeNewRequest(setParameters("GET", "/image.png", "", "HTTP/1.1"), dataStore, cookieStore, requestLogStore);
        assertTrue(routeToHandlerForPNG instanceof FileHandler);

        Handler routeToHandlerForGIF = router.routeNewRequest(setParameters("GET", "/image.gif", "", "HTTP/1.1"), dataStore, cookieStore, requestLogStore);
        assertTrue(routeToHandlerForGIF instanceof FileHandler);

        Handler routeToHandlerForText = router.routeNewRequest(setParameters("GET", "/text-file.txt", "", "HTTP/1.1"), dataStore, cookieStore, requestLogStore);
        assertTrue(routeToHandlerForText instanceof FileHandler);

        Handler routeToHandlerForFile = router.routeNewRequest(setParameters("GET", "/file1", "", "HTTP/1.1"), dataStore, cookieStore, requestLogStore);
        assertTrue(routeToHandlerForFile instanceof FileHandler);
    }

    @Test
    public void routesToCoffeeHandler() throws IOException {
        Handler routeToHandlerForCoffee = router.routeNewRequest(setParameters("GET", "/coffee", "", "HTTP/1.1"), dataStore, cookieStore, requestLogStore);
        assertTrue(routeToHandlerForCoffee instanceof CoffeeHandler);

        Handler routeToHandlerForTea = router.routeNewRequest(setParameters("GET", "/tea", "", "HTTP/1.1"), dataStore, cookieStore, requestLogStore);
        assertTrue(routeToHandlerForTea instanceof IndexHandler);
    }


    @Test
    public void routesToMethodOptionsHandler() throws IOException {
        Handler routeToHandler = router.routeNewRequest(setParameters("OPTIONS", "/method_options", "", "HTTP/1.1"), dataStore, cookieStore, requestLogStore);
        assertTrue(routeToHandler instanceof MethodOptionsHandler);
    }

    @Test
    public void routesToMethodOptionsTwoHandler() throws IOException {
        Handler routeToHandler = router.routeNewRequest(setParameters("FKJDASFRWO", "/method_options2", "", "HTTP/1.1"), dataStore, cookieStore, requestLogStore);
        assertTrue(routeToHandler instanceof MethodOptionsTwoHandler);
    }

    @Test
    public void routesToErrorHandler() throws IOException {
        Handler erroneousRouteToHandler = router.routeNewRequest(setParameters("HEAD", "/foobar", "", "HTTP/1.1"), dataStore, cookieStore, requestLogStore);
        assertTrue(erroneousRouteToHandler instanceof ErrorHandler);
    }

    private HashMap<String, String> setParameters(String method, String path, String requestBody, String protocolVersion) {
        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("Method", method);
        requestParameters.put("Path", path);
        if (!requestBody.equals("")) {
            requestParameters.put("Request Body", requestBody);
        }
        requestParameters.put("Protocol Version", protocolVersion);
        return requestParameters;
    }
}
