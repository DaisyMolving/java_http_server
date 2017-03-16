package server.request;

import server.Response;

import java.io.IOException;

public interface Request {

    public Response respond() throws IOException;

}
