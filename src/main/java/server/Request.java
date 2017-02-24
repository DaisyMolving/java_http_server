package server;

public interface Request {

  public String path = new String();
  public String protocolVersion = new String();

  public String getPath();

  public String getProtocolVersion();

  public Response createResponse();
}
