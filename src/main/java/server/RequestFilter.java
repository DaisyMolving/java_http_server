package server;

public class RequestFilter {

    public static Request createByType(String requestInformation) {
        if (requestInformation.contains("GET")) {
            return new GetRequest(requestInformation);
        } else if (requestInformation.contains("POST")) {
            return new PostRequest(requestInformation);
        } else if (requestInformation.contains("PUT")) {
            return new PutRequest(requestInformation);
        } else if (requestInformation.contains("HEAD")) {
            return new GetRequest(requestInformation);
        } else if (requestInformation.contains("OPTIONS")) {
            return new OptionRequest(requestInformation);
        } return new BogusRequest(requestInformation);
    }
}
