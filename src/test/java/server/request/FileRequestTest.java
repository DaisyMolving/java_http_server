package server.request;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FileRequestTest {

    @Test
    public void fileRequestRespondsWith200IfFileExists() throws IOException {
        FileRequest fileRequest = new FileRequest("HTTP/1.1", "file1", null, null, null);
        assertEquals("HTTP/1.1 200 OK", fileRequest.respond().startLine);
    }

    @Test
    public void fileRequestRespondsWith404IfFileDoesNotExist() throws IOException {
        FileRequest fileRequest = new FileRequest("HTTP/1.1", "gobbledigook", "bytes=0-1", null, null);
        assertEquals("HTTP/1.1 404 Not Found", fileRequest.respond().startLine);
    }

    @Test
    public void fileRequestRespondsWith206IfRangeIsGivenForPartialContent() throws IOException {
        FileRequest fileRequest = new FileRequest("HTTP/1.1", "partial_content.txt", "bytes=0-1", null, null);
        assertEquals("HTTP/1.1 206 Partial Content", fileRequest.respond().startLine);
    }

    @Test
    public void readsAFile() throws IOException {
        FileRequest fileRequest = new FileRequest("HTTP/1.1", "file1", null, null, null);
        assertEquals("file1 contents", new String(fileRequest.readFile()));
    }

    @Test
    public void readsAPartialFileWhenPassedRangeWithBothEnds() throws IOException {
        FileRequest fileRequest = new FileRequest("HTTP/1.1", "partial_content.txt", "bytes=3-14", null, null);
        assertEquals("s is a file ", new String(fileRequest.readPartialFile()));
    }

    @Test
    public void readsAPartialFileWhenPassedRangeWithNoEnd() throws IOException {
        FileRequest fileRequest = new FileRequest("HTTP/1.1", "partial_content.txt", "bytes=40-", null, null);
        assertEquals("d part of in order to fulfill a 206.\n", new String(fileRequest.readPartialFile()));
    }

    @Test
    public void readsAPartialFileWhenPassedRangeWithNoBeginning() throws IOException {
        FileRequest fileRequest = new FileRequest("HTTP/1.1", "partial_content.txt", "bytes=-3", null, null);
        assertEquals("6.\n", new String(fileRequest.readPartialFile()));
    }

    @Test
    public void returnsA204ForAPatchRequest() throws IOException {
        FileRequest fileRequest = new FileRequest("HTTP/1.1", "patch-content.txt", null, "dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec", "data");
        assertEquals("HTTP/1.1 204 No Content", fileRequest.respond().startLine);
        fileRequest.patchFile("default content\n");
    }

    @Test
    public void patchFileAltersFile() throws IOException {
        FileRequest fileRequest = new FileRequest("HTTP/1.1", "patch-content.txt", null, "dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec", "hello");
        assertEquals("default content\n", new String(fileRequest.readFile()));
        fileRequest.patchFile("hello");
        assertEquals("hello", new String(fileRequest.readFile()));
        fileRequest.patchFile("default content\n");
    }

}
