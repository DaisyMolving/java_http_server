package server.request;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class ParameterRequestTest {

    @Test
    public void parameterRequestRespondsWith200() throws UnsupportedEncodingException {
        ParameterRequest parameterRequest = new ParameterRequest("HTTP/1.1", "this=hello");
        assertEquals("HTTP/1.1 200 OK", parameterRequest.respond().startLine);
    }

    @Test
    public void decodesURL() throws UnsupportedEncodingException {
        ParameterRequest parameterRequest = new ParameterRequest("HTTP/1.1", "variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff");
        assertEquals("variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?\nvariable_2 = stuff", parameterRequest.decodeParameters());
    }
}
