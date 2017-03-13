package server.request;

import server.Response;

import java.util.ArrayList;
import java.util.List;

public class IndexRequest implements Request {

    private String protocolVersion;

    private List<String> links = new ArrayList<>();

    public IndexRequest(String protocolVersion) {
        this.protocolVersion = protocolVersion;
        links.add("file1");
        links.add("file2");
        links.add("image.gif");
        links.add("image.jpeg");
        links.add("image.png");
        links.add("text-file.txt");
    }

    public Response respond() {
        return new Response(
                protocolVersion + " 200 OK",
                "",
                "",
                "",
                addLinks().getBytes());
    }

    public String addLinks() {
        String htmlLinks = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"></head><body>";
        for(String link : links) {
            htmlLinks = htmlLinks + "<a href=\""+ link + "\">" + link + "</a>\n";
        }
        htmlLinks = htmlLinks + ("</body></html>");
        return htmlLinks;
    }

}
