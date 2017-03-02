package server;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class RequestFilterTest {

    @Test
    public void requestFilterCreatesCorrectRequestDependingOnInputForGet() throws IOException {
        RequestFilter requestFilter = new RequestFilter("GET / HTTP/1.1");
        assertTrue(requestFilter.createByType() instanceof GetRequest);
    }

    @Test
    public void requestFilterCreatesCorrectRequestDependingOnInputForPost() throws IOException {
        RequestFilter requestFilter = new RequestFilter("POST /form HTTP/1.1");
        assertTrue(requestFilter.createByType() instanceof PostRequest);
    }

    @Test
    public void requestFilterCreatesCorrectRequestDependingOnInputForPut() throws IOException {
        RequestFilter requestFilter = new RequestFilter("PUT /form HTTP/1.1");
        assertTrue(requestFilter.createByType() instanceof PutRequest);
    }

    @Test
    public void requestFilterCreatesCorrectRequestDependingOnInputForHead() throws IOException {
        RequestFilter requestFilter = new RequestFilter("HEAD /form HTTP/1.1");
        assertTrue(requestFilter.createByType() instanceof GetRequest);
    }

    @Test
    public void requestFilterCreatesCorrectRequestDependingOnInputForOptions() throws IOException {
        RequestFilter requestFilter = new RequestFilter("OPTIONS /form HTTP/1.1");
        assertTrue(requestFilter.createByType() instanceof OptionRequest);
    }

    @Test
    public void requestFilterCreatesCorrectRequestDependingOnInputForBogus() throws IOException {
        RequestFilter requestFilter = new RequestFilter("JKWERA /form HTTP/1.1");
        assertTrue(requestFilter.createByType() instanceof BogusRequest);
    }

}
