package server.request;

import server.Response;

import java.io.IOException;
import java.net.URISyntaxException;

public interface Request {

    public Response respond() throws IOException, URISyntaxException;

}
