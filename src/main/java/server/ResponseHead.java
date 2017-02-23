package server;

public class ResponseHead {

    public String allow;
    public String location;
    public String startLine;
    public String endOfHead;

    public ResponseHead(String startLine, String location, String allow) {
        this.startLine = startLine;
        this.location = "Location: " + location;
        this.allow = "Allow: " + allow;
        this.endOfHead = "\n";
    }

}
