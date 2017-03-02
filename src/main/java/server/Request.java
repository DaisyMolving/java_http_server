package server;

import java.io.IOException;

public interface Request {

  public Response createResponse() throws IOException;
}
