package server.request;

import server.Response;

import java.io.IOException;
import java.nio.file.*;
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

    public Response respond() throws IOException {
        List<String> headerFields = new ArrayList<>();
        headerFields.add(protocolVersion + " 200 OK");
        return new Response(headerFields, addLinks().getBytes());
    }

    public String addLinks() throws IOException {
        String htmlLinks = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"></head><body>";
        Path dir = Paths.get("/Users/daisymolving/Documents/Apprenticeship/cob_spec/public");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file: stream) {
                htmlLinks = htmlLinks + "<a href=\"/"+ file.getFileName() + "\">" + file.getFileName() + "</a>\n";
            }
        } catch (IOException | DirectoryIteratorException x) {
            System.err.println(x);
        } htmlLinks = htmlLinks + ("</body></html>");
        return htmlLinks;
    }

}
