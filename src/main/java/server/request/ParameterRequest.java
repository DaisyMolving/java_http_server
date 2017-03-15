package server.request;

import server.Response;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class ParameterRequest implements Request {

    private String protocolVersion;
    private String urlParameters;

    public ParameterRequest(String protocolVersion, String urlParameters) {
        this.protocolVersion = protocolVersion;
        this.urlParameters = urlParameters;
    }

    public Response respond() throws UnsupportedEncodingException {

        List<String> headerFields = new ArrayList<>();
        headerFields.add(protocolVersion + " 200 OK");
        byte[] bodyContent = decodeParameters().getBytes();

        return new Response(headerFields, bodyContent);
    }

    public String decodeParameters() throws UnsupportedEncodingException {
        return URLDecoder.decode(formatParameters(urlParameters), "UTF-8");
    }

    private String formatParameters(String urlParameters) {
        String separatedParameters = urlParameters.replaceAll("&", "\n");
        String formattedParameters = separatedParameters.replaceAll("=", " = ");
        return formattedParameters;
    }
}
