package server.handler;

import org.junit.Test;
import server.RequestLogStore;
import server.request.LogsRequest;
import server.request.MethodNotAllowedRequest;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class LogsHandlerTest {

    @Test
    public void logsHandlerSendsLogsRequestIfMethodIsValid() {
        LogsHandler logsHandlerGet = new LogsHandler(setParameters("GET", "HTTP/1.1", "/logs?type=chocolate"), new RequestLogStore());
        assertTrue(logsHandlerGet.send() instanceof LogsRequest);
        LogsHandler logsHandlerPost = new LogsHandler(setParameters("POST", "HTTP/1.1", "/logs?type=chocolate"), new RequestLogStore());
        assertTrue(logsHandlerPost.send() instanceof MethodNotAllowedRequest);
        LogsHandler logsHandlerBogus = new LogsHandler(setParameters("KDAJKFJDS", "HTTP/1.1", "/logs?type=chocolate"), new RequestLogStore());
        assertTrue(logsHandlerBogus.send() instanceof MethodNotAllowedRequest);
    }

    private HashMap<String, String> setParameters(String method, String protocolVersion, String path) {
        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("Method", method);
        requestParameters.put("Protocol Version", protocolVersion);
        requestParameters.put("Path", path);
        return requestParameters;
    }
}
