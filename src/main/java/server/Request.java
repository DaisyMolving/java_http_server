package server;

public interface Request {

  public String methodVerb = new String();
  public String path = new String();
  public String protocolVersion = new String();

  public String getMethodVerb();

  public String getPath();

  public String getProtocolVersion();

  public Response createResponse();
}
