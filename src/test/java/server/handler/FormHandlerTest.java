package server.handler;

import org.junit.Test;
import server.DataStore;
import server.request.FormRequest;
import server.request.MethodNotAllowedRequest;

import static org.junit.Assert.assertTrue;

public class FormHandlerTest {

    private DataStore dataStore = new DataStore();

    @Test
    public void formHandlerSendsFormRequestIfMethodIsValid() {
        FormHandler formHandlerGet = new FormHandler("GET", "HTTP/1.1", "", dataStore);
        assertTrue(formHandlerGet.send() instanceof FormRequest);

        FormHandler formHandlerPost = new FormHandler("POST", "HTTP/1.1", "data=hey", dataStore);
        assertTrue(formHandlerPost.send() instanceof FormRequest);

        FormHandler formHandlerPut = new FormHandler("PUT", "HTTP/1.1", "data=hello", dataStore);
        assertTrue(formHandlerPut.send() instanceof FormRequest);

        FormHandler formHandlerDelete = new FormHandler("DELETE", "HTTP/1.1", "", dataStore);
        assertTrue(formHandlerDelete.send() instanceof FormRequest);

        FormHandler formHandlerBogus = new FormHandler("KDAJKFJDS", "HTTP/1.1", "", dataStore);
        assertTrue(formHandlerBogus.send() instanceof MethodNotAllowedRequest);
    }
}
