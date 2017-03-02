package server;

import java.io.IOException;
import java.net.URISyntaxException;

public interface Request {

  public Response createResponse() throws IOException;
}
