package server.request;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileRequestTest {

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
    public void patchFileAltersFile() throws IOException {
        FileRequest fileRequest = new FileRequest("HTTP/1.1", "patch-content.txt", null, "dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec", "hello");
        assertEquals("default content\n", new String(fileRequest.readFile()));
        fileRequest.patchFile("hello");
        assertEquals("hello", new String(fileRequest.readFile()));
        fileRequest.patchFile("default content\n");
    }

    @Test
    public void fileRequestRespondsWith200IfFileExists() throws IOException {
        FileRequest fileRequest = new FileRequest("HTTP/1.1", "file1", null, null, null);
        assertEquals("HTTP/1.1 200 OK\nContent-Type: text/plain\n\nfile1 contents", new String(fileRequest.respond().generateContent()));
    }

    @Test
    public void fileRequestRespondsWith404IfFileDoesNotExist() throws IOException {
        FileRequest fileRequest = new FileRequest("HTTP/1.1", "gobbledigook", "bytes=0-1", null, null);
        assertEquals("HTTP/1.1 404 Not Found\n\n404 Not Found", new String(fileRequest.respond().generateContent()));
    }

    @Test
    public void fileRequestRespondsWith206IfRangeIsGivenForPartialContent() throws IOException {
        FileRequest fileRequest = new FileRequest("HTTP/1.1", "partial_content.txt", "bytes=0-1", null, null);
        assertEquals("HTTP/1.1 206 Partial Content\nContent-Type: text/plain\n\nTh", new String(fileRequest.respond().generateContent()));
    }

    @Test
    public void returnsA204ForAPatchRequest() throws IOException {
        FileRequest fileRequest = new FileRequest("HTTP/1.1", "patch-content.txt", null, "dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec", "data");
        assertEquals("HTTP/1.1 204 No Content\nContent-Type: text/plain\n\ndefault content\n", new String(fileRequest.respond().generateContent()));
        fileRequest.patchFile("default content\n");
    }

    @Test
    public void returnsA200ForAJPEGFile() throws IOException {
        FileRequest fileRequest = new FileRequest("HTTP/1.1", "image.jpeg", null, null, null);
        String output = new String(fileRequest.respond().generateContent());
        assertTrue(output.contains("HTTP/1.1 200 OK\nContent-Type: image/jpeg"));
    }

    @Test
    public void returnsA200ForAPNGFile() throws IOException {
        FileRequest fileRequest = new FileRequest("HTTP/1.1", "image.png", null, null, null);
        String output = new String(fileRequest.respond().generateContent());
        assertTrue(output.contains("HTTP/1.1 200 OK\nContent-Type: image/png"));
    }

    @Test
    public void returnsA200ForAGIFFile() throws IOException {
        FileRequest fileRequest = new FileRequest("HTTP/1.1", "image.gif", null, null, null);
        String output = new String(fileRequest.respond().generateContent());
        assertTrue(output.contains("HTTP/1.1 200 OK\nContent-Type: image/gif"));
    }

}
