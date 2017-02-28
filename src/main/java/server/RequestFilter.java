package server;

import java.util.HashMap;

public class RequestFilter {

    private HashMap<String, Request> requestTypes = new HashMap<>();
    public String methodVerb;
    public String path;
    private String protocolVersion;

    public RequestFilter(String requestInformation) {
        splitRequestIntoComponents(requestInformation);
        requestTypes.put("GET", new GetRequest(path, protocolVersion));
        requestTypes.put("POST", new PostRequest(path, protocolVersion));
        requestTypes.put("PUT", new PutRequest(path, protocolVersion));
        requestTypes.put("HEAD", new GetRequest(path, protocolVersion));
        requestTypes.put("OPTIONS", new OptionRequest(path, protocolVersion));
    }

    public Request createByType() {
        if (requestTypes.containsKey(methodVerb)) {
            return requestTypes.get(methodVerb);
        } return new BogusRequest(path, protocolVersion);
    }

    private void splitRequestIntoComponents(String requestInformation) {
        String[] splitRequest = requestInformation.split("\\s+");
        this.methodVerb = splitRequest[0];
        this.path = splitRequest[1];
        this.protocolVersion = splitRequest[2];
    }


}
