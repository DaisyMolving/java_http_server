package server.request;

import org.junit.Test;
import server.RequestLogStore;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LogsRequestTest {

    @Test
    public void ifCredentialsAreValidStatusCodeIs200() throws IOException {
        RequestLogStore requestLogStore = new RequestLogStore();
        LogsRequest logsRequest = new LogsRequest("HTTP/1.1", "YWRtaW46aHVudGVyMg==", new RequestLogStore());
        assertEquals("HTTP/1.1 200 OK\n\n", new String(logsRequest.respond().generateContent()));
    }

    @Test
    public void ifCredentialsNotValidStatusCodeIs401() throws IOException {
        LogsRequest logsRequest = new LogsRequest("HTTP/1.1", "YWRtaW46d2hhdGV2ZXI=", new RequestLogStore());
        assertEquals("HTTP/1.1 401 Unauthorized\nWWW-Authenticate: Basic realm=\"My Server\"\n\n", new String(logsRequest.respond().generateContent()));
    }

    @Test
    public void ifCredentialsAreValidLogsAreShown() throws IOException {
        RequestLogStore requestLogStore = new RequestLogStore();
        new IndexRequest("HTTP/1.1");
        new FormRequest("HTTP/1.1", "data");
        LogsRequest logsRequest = new LogsRequest("HTTP/1.1", "YWRtaW46aHVudGVyMg==", requestLogStore);
        assertEquals("HTTP/1.1 200 OK\n\n", new String(logsRequest.respond().generateContent()));
    }
}
