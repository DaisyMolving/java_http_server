package server.handler;

import org.junit.Test;
import server.DataStore;
import server.request.FormRequest;
import server.request.MethodNotAllowedRequest;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class FormHandlerTest {

    private DataStore dataStore = new DataStore();

    @Test
    public void formHandlerSendsFormRequestIfMethodIsValid() {
        FormHandler formHandlerGet = new FormHandler(setParameters("GET", "HTTP/1.1", ""), dataStore);
        assertTrue(formHandlerGet.send() instanceof FormRequest);

        FormHandler formHandlerPost = new FormHandler(setParameters("POST", "HTTP/1.1", "data=hey"), dataStore);
        assertTrue(formHandlerPost.send() instanceof FormRequest);

        FormHandler formHandlerPut = new FormHandler(setParameters("PUT", "HTTP/1.1", "data=hello"), dataStore);
        assertTrue(formHandlerPut.send() instanceof FormRequest);

        FormHandler formHandlerDelete = new FormHandler(setParameters("DELETE", "HTTP/1.1", ""), dataStore);
        assertTrue(formHandlerDelete.send() instanceof FormRequest);

        FormHandler formHandlerBogus = new FormHandler(setParameters("KDAJKFJDS", "HTTP/1.1", ""), dataStore);
        assertTrue(formHandlerBogus.send() instanceof MethodNotAllowedRequest);
    }

    private HashMap<String, String> setParameters(String method, String protocolVersion, String requestBody) {
        HashMap<String, String> requestParameters = new HashMap<>();
        requestParameters.put("Method", method);
        requestParameters.put("Protocol Version", protocolVersion);
        requestParameters.put("Request Body", requestBody);
        return requestParameters;
    }
}
