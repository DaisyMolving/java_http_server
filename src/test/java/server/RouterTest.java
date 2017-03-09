package server;

import org.junit.Test;
import server.handler.*;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class RouterTest {

    private Router router = new Router();

    @Test
    public void routesToIndexHandler() throws IOException {
        Handler routeToHandlerForGet = router.routeNewRequest("GET", "/", "", "HTTP/1.1");
        assertTrue(routeToHandlerForGet instanceof IndexHandler);

        Handler routeToHandlerForHead = router.routeNewRequest("HEAD", "/", "", "HTTP/1.1");
        assertTrue(routeToHandlerForHead instanceof IndexHandler);
    }

    @Test
    public void routesToFormHandler() throws IOException {

        Handler routeToHandlerForPost = router.routeNewRequest("POST", "/form", "data=fatcat", "HTTP/1.1");
        assertTrue(routeToHandlerForPost instanceof FormHandler);

        Handler routeToHandlerForPut = router.routeNewRequest("PUT", "/form", "data=heathcliff", "HTTP/1.1");
        assertTrue(routeToHandlerForPut instanceof FormHandler);
    }

    @Test
    public void routesToRedirectHandler() throws IOException {
        Handler routeToHandler = router.routeNewRequest("GET", "/redirect", "", "HTTP/1.1");
        assertTrue(routeToHandler instanceof RedirectHandler);
    }

    @Test
    public void routesToFileHandler() throws IOException {
        Handler routeToHandlerForJPEG = router.routeNewRequest("GET", "/image.jpeg", "", "HTTP/1.1");
        assertTrue(routeToHandlerForJPEG instanceof FileHandler);

        Handler routeToHandlerForPNG = router.routeNewRequest("GET", "/image.png", "", "HTTP/1.1");
        assertTrue(routeToHandlerForPNG instanceof FileHandler);

        Handler routeToHandlerForGIF = router.routeNewRequest("GET", "/image.gif", "", "HTTP/1.1");
        assertTrue(routeToHandlerForGIF instanceof FileHandler);

        Handler routeToHandlerForText = router.routeNewRequest("GET", "/text-file.txt", "", "HTTP/1.1");
        assertTrue(routeToHandlerForText instanceof FileHandler);

        Handler routeToHandlerForFile = router.routeNewRequest("GET", "/file1", "", "HTTP/1.1");
        assertTrue(routeToHandlerForFile instanceof FileHandler);
    }

    @Test
    public void routesToCoffeeHandler() throws IOException {
        Handler routeToHandlerForCoffee = router.routeNewRequest("GET", "/coffee", "", "HTTP/1.1");
        assertTrue(routeToHandlerForCoffee instanceof CoffeeHandler);

        Handler routeToHandlerForTea = router.routeNewRequest("GET", "/tea", "", "HTTP/1.1");
        assertTrue(routeToHandlerForTea instanceof IndexHandler);
    }


    @Test
    public void routesToMethodOptionsHandler() throws IOException {
        Handler routeToHandler = router.routeNewRequest("OPTIONS", "/method_options", "", "HTTP/1.1");
        assertTrue(routeToHandler instanceof MethodOptionsHandler);
    }

    @Test
    public void routesToMethodOptionsTwoHandler() throws IOException {
        Handler routeToHandler = router.routeNewRequest("FKJDASFRWO", "/method_options2", "", "HTTP/1.1");
        assertTrue(routeToHandler instanceof MethodOptionsTwoHandler);
    }

    @Test
    public void routesToErrorHandler() throws IOException {
        Handler erroneousRouteToHandler = router.routeNewRequest("HEAD", "/foobar", "", "HTTP/1.1");
        assertTrue(erroneousRouteToHandler instanceof ErrorHandler);
    }

}