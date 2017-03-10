package server.request;

import server.Response;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ParameterRequest implements Request {

    private String protocolVersion;
    private String urlParameters;

    public ParameterRequest(String protocolVersion, String urlParameters) {
        this.protocolVersion = protocolVersion;
        this.urlParameters = urlParameters;
    }

    public Response respond() throws UnsupportedEncodingException {
        return new Response(
                protocolVersion + " 200 OK",
                "",
                "",
                "",
                decodeParameters().getBytes());
    }

    public String decodeParameters() throws UnsupportedEncodingException {
        return URLDecoder.decode(formatParameters(urlParameters), "UTF-8");
    }

    public String formatParameters(String urlParameters) {
        String separatedParameters = urlParameters.replaceAll("&", "\n");
        String formattedParameters = separatedParameters.replaceAll("=", " = ");
        return formattedParameters;
    }
}
