package server.request;

import org.junit.Test;
import server.RequestLogStore;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LogsRequestTest {


    @Test
    public void ifCredentialsAreValidStatusCodeIs200() throws IOException {
        RequestLogStore requestLogStore = new RequestLogStore();
        LogsRequest logsRequest = new LogsRequest("HTTP/1.1", "YWRtaW46aHVudGVyMg==", requestLogStore);
        assertEquals("HTTP/1.1 200 OK", logsRequest.respond().startLine);
    }

    @Test
    public void ifCredentialsNotValidStatusCodeIs401() throws IOException {
        LogsRequest logsRequest = new LogsRequest("HTTP/1.1", "YWRtaW46d2hhdGV2ZXI=", new RequestLogStore());
        assertEquals("HTTP/1.1 401 Unauthorized", logsRequest.respond().startLine);
    }
}
