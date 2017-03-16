package server;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestLogStoreTest {

    private RequestLogStore requestLog = new RequestLogStore();

    @Test
    public void requestLogDataCanBeRead() {
        assertEquals("", requestLog.read());
    }

    @Test
    public void requestLogDataCanBeAddedTo() {
        requestLog.add("data=hello\n");
        assertEquals("data=hello\n", requestLog.read());
        requestLog.add("data=hi\n");
        assertEquals("data=hello\ndata=hi\n", requestLog.read());
    }
}
