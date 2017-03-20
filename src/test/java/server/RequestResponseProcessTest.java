package server;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import static org.junit.Assert.assertTrue;

public class RequestResponseProcessTest {

    private RequestResponseProcess requestResponseProcess = new RequestResponseProcess(
            new Socket(),
            new Router(),
            new DataStore(),
            new DataStore(),
            new RequestLogStore()
    );

    @Test
    public void hasBasicAuth() throws IOException {

        byte[] unauthorizedResponse = getResponse("GET /logs HTTP/1.1\n\n");
        assertTrue(new String(unauthorizedResponse).contains("401 Unauthorized"));
        assertTrue(new String(unauthorizedResponse).contains("WWW-Authenticate: Basic realm=\"My Server\""));

        getResponse("GET /log HTTP/1.1\n\n");
        getResponse("PUT /these HTTP/1.1\n\n");
        getResponse("HEAD /requests HTTP/1.1\n\n");

        byte[] authorizedResponse = getResponse("GET /logs HTTP/1.1\n" +
                "Authorization: Basic YWRtaW46aHVudGVyMg==\n\n");
        assertTrue(new String(authorizedResponse).contains("200 OK"));
        assertTrue(new String(authorizedResponse).contains("GET /log HTTP/1.1"));
        assertTrue(new String(authorizedResponse).contains("PUT /these HTTP/1.1"));
        assertTrue(new String(authorizedResponse).contains("HEAD /requests HTTP/1.1"));
    }

    @Test
    public void storesCookieData() throws IOException {

        byte[] cookieSettingResponse = getResponse("GET /cookie?type=chocolate HTTP/1.1\n\n");
        assertTrue(new String(cookieSettingResponse).contains("200 OK"));
        assertTrue(new String(cookieSettingResponse).contains("Set-Cookie: "));
        assertTrue(new String(cookieSettingResponse).contains("Eat"));

        byte[] cookieEatingResponse = getResponse("GET /eat_cookie HTTP/1.1\n\n");
        assertTrue(new String(cookieEatingResponse).contains("200 OK"));
        assertTrue(new String(cookieEatingResponse).contains("mmmm chocolate"));
    }

    @Test
    public void indexHasDirectoryLinks() throws IOException {

        byte[] directoryListingResponse = getResponse("GET / HTTP/1.1\n\n");
        assertTrue(new String(directoryListingResponse).contains("200 OK"));
        assertTrue(new String(directoryListingResponse).contains("html"));
        assertTrue(new String(directoryListingResponse).contains("<a href=\"/file1"));
        assertTrue(new String(directoryListingResponse).contains("<a href=\"/file2"));
        assertTrue(new String(directoryListingResponse).contains("<a href=\"/text-file.txt"));
        assertTrue(new String(directoryListingResponse).contains("<a href=\"/image.jpeg"));
        assertTrue(new String(directoryListingResponse).contains("<a href=\"/image.png"));
        assertTrue(new String(directoryListingResponse).contains("<a href=\"/image.gif"));
        assertTrue(new String(directoryListingResponse).contains("<a href=\"/partial_content.txt"));
        assertTrue(new String(directoryListingResponse).contains("<a href=\"/patch-content.txt"));
    }

    @Test
    public void indexHasDirectoryListings() throws IOException {

        byte[] directoryListingResponse = getResponse("GET / HTTP/1.1\n\n");
        assertTrue(new String(directoryListingResponse).contains("200 OK"));
        assertTrue(new String(directoryListingResponse).contains("file1"));
        assertTrue(new String(directoryListingResponse).contains("file2"));
        assertTrue(new String(directoryListingResponse).contains("text-file.txt"));
        assertTrue(new String(directoryListingResponse).contains("image.jpeg"));
        assertTrue(new String(directoryListingResponse).contains("image.png"));
        assertTrue(new String(directoryListingResponse).contains("image.gif"));
        assertTrue(new String(directoryListingResponse).contains("partial_content.txt"));
        assertTrue(new String(directoryListingResponse).contains("patch-content.txt"));
    }

    @Test
    public void showsFileContentsForFiles() throws IOException {

        byte[] textFileResponse = getResponse("GET /file1 HTTP/1.1\n\n");
        assertTrue(new String(textFileResponse).contains("200 OK"));
        assertTrue(new String(textFileResponse).contains("file1 content"));
    }

    @Test
    public void givesFourEighteenForCoffeeRequest() throws IOException {

        byte[] coffeeResponse = getResponse("GET /coffee HTTP/1.1\n\n");
        assertTrue(new String(coffeeResponse).contains("418 I'm a teapot"));

        byte[] teaResponse = getResponse("GET /tea HTTP/1.1\n\n");
        assertTrue(new String(teaResponse).contains("200 OK"));
    }

    @Test
    public void givesFourOhFourForNonExistentRequest() throws IOException {

        byte[] fileDoesNotExistResponse = getResponse("GET /foobar HTTP/1.1\n\n");
        assertTrue(new String(fileDoesNotExistResponse).contains("404 Not Found"));
    }

    @Test
    public void showsImageContent() {

    }

    @Test
    public void givesContentTypeHeaderFieldForFiles() throws IOException {

        byte[] textFileResponse = getResponse("GET /text-file.txt HTTP/1.1\n\n");
        assertTrue(new String(textFileResponse).contains("200 OK"));
        assertTrue(new String(textFileResponse).contains("Content-Type: text/plain"));

        byte[] PNGResponse = getResponse("GET /image.png HTTP/1.1\n\n");
        assertTrue(new String(PNGResponse).contains("200 OK"));
        assertTrue(new String(PNGResponse).contains("Content-Type: image/png"));

        byte[] JPEGResponse = getResponse("GET /image.jpeg HTTP/1.1\n\n");
        assertTrue(new String(JPEGResponse).contains("200 OK"));
        assertTrue(new String(JPEGResponse).contains("Content-Type: image/jpeg"));

        byte[] GIFResponse = getResponse("GET /image.gif HTTP/1.1\n\n");
        assertTrue(new String(GIFResponse).contains("200 OK"));
        assertTrue(new String(GIFResponse).contains("Content-Type: image/gif"));
    }

    @Test
    public void givesFourOhFiveForMethodsNotAllowed() throws IOException {

        byte[] legalMethodResponse = getResponse("GET /text-file.txt HTTP/1.1\n\n");
        assertTrue(new String(legalMethodResponse).contains("200 OK"));

        byte[] illegalPostMethodResponse = getResponse("POST /text-file.txt HTTP/1.1\n\n");
        assertTrue(new String(illegalPostMethodResponse).contains("405 Method Not Allowed"));

        byte[] illegalPutMethodResponse = getResponse("PUT /file1 HTTP/1.1\n\n");
        assertTrue(new String(illegalPutMethodResponse).contains("405 Method Not Allowed"));

        byte[] bogusMethodResponse = getResponse("JFKAFJDKSAF / HTTP/1.1\n\n");
        assertTrue(new String(bogusMethodResponse).contains("405 Method Not Allowed"));
    }

    @Test
    public void parameterDecode() throws IOException {

        byte[] parameterDecodeResponse = getResponse("GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1\n\n");
        assertTrue(new String(parameterDecodeResponse).contains("200 OK"));
        assertTrue(new String(parameterDecodeResponse).contains("variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?"));
        assertTrue(new String(parameterDecodeResponse).contains("variable_2 = stuff"));
    }

    @Test
    public void givesPartialContent() throws IOException {

        byte[] partialFileResponseOne = getResponse("GET /partial_content.txt HTTP/1.1\n" +
                "Content-Length: 77\n" +
                "Range: bytes=0-4\n\n");
        assertTrue(new String(partialFileResponseOne).contains("206 Partial Content"));
        assertTrue(new String(partialFileResponseOne).contains("This"));

        byte[] partialFileResponseTwo = getResponse("GET /partial_content.txt HTTP/1.1\n" +
                "Content-Length: 77\n" +
                "Range: bytes=-6\n\n");
        assertTrue(new String(partialFileResponseTwo).contains("206 Partial Content"));
        assertTrue(new String(partialFileResponseTwo).contains(" 206.\n"));

        byte[] partialFileResponseThree = getResponse("GET /partial_content.txt HTTP/1.1\n" +
                "Content-Length: 77\n" +
                "Range: bytes=4-\n\n");
        assertTrue(new String(partialFileResponseThree).contains("206 Partial Content"));
        assertTrue(new String(partialFileResponseThree).contains(" is a file that contains text to read part of in order to fulfill a 206.\n"));
    }

    @Test
    public void patchesFiles() throws IOException {

        byte[] getPatchFileResponse = getResponse("GET /patch-content.txt HTTP/1.1\n\n");
        assertTrue(new String(getPatchFileResponse).contains("200 OK"));

        byte[] patchFileResponse = getResponse("PATCH /patch-content.txt HTTP/1.1\n" +
                                "Content-Length: 13\n" +
                                "If-Match: dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec\n\n" +
                                "patch content");
        assertTrue(new String(patchFileResponse).contains("204 No Content"));

        byte[] getAfterPatchResponse = getResponse("GET /patch-content.txt HTTP/1.1\n\n");
        assertTrue(new String(getAfterPatchResponse).contains("200 OK"));
        assertTrue(new String(getAfterPatchResponse).contains("patch content"));


        byte[] rewindPatchFileResponse = getResponse("PATCH /patch-content.txt HTTP/1.1\n" +
                        "Content-Length: 16\n" +
                        "If-Match: 5c36acad75b78b82be6d9cbbd6143ab7e0cc04b0\n\n" +
                        "default content\n");
        assertTrue(new String(rewindPatchFileResponse).contains("204 No Content"));

        byte[] getAfterRewindPatchResponse = getResponse("GET /patch-content.txt HTTP/1.1\n\n");
        assertTrue(new String(getAfterRewindPatchResponse).contains("200 OK"));
        assertTrue(new String(getAfterRewindPatchResponse).contains("default content\n"));
    }

    @Test
    public void postGetPutGetDeleteGet() throws IOException {
    }

    @Test
    public void redirectsPath() throws IOException {

        byte[] response = getResponse("GET /redirect HTTP/1.1\n\n");
        assertTrue(new String(response).contains("302 Found"));
        assertTrue(new String(response).contains("Location: http://localhost:5000/"));
    }

    @Test
    public void respondsToGetRequest() throws IOException {

        byte[] response = getResponse("GET / HTTP/1.1\n\n");
        assertTrue(new String(response).contains("200 OK"));
    }

    @Test
    public void respondsToHeadRequest() throws IOException {

        byte[] headIndexResponse = getResponse("HEAD / HTTP/1.1\n\n");
        assertTrue(new String(headIndexResponse).contains("200 OK"));

        byte[] headNowhereResponse = getResponse("HEAD /foobar HTTP/1.1\n\n");
        assertTrue(new String(headNowhereResponse).contains("404 Not Found"));
    }

    @Test
    public void respondsToOptionRequest() throws IOException {

        byte[] methodOptionsResponse = getResponse("OPTIONS /method_options HTTP/1.1\n\n");
        assertTrue(new String(methodOptionsResponse).contains("200 OK"));
        assertTrue(new String(methodOptionsResponse).contains("Allow: GET,HEAD,POST,OPTIONS,PUT"));

        byte[] methodOptionsTwoResponse = getResponse("OPTIONS /method_options2 HTTP/1.1\n\n");
        assertTrue(new String(methodOptionsTwoResponse).contains("200 OK"));
        assertTrue(new String(methodOptionsTwoResponse).contains("Allow: GET,OPTIONS"));
    }

    @Test
    public void respondsToPostRequest() throws IOException {

        byte[] response = getResponse("POST /form HTTP/1.1\n" +
                "Content-Length: 11\n\n" +
                "\"My\"=\"Data\"");
        assertTrue(new String(response).contains("200 OK"));
        assertTrue(new String(response).contains(("\"My\"=\"Data\"")));
    }

    @Test
    public void respondsToPutRequest() throws IOException {

        byte[] response = getResponse("PUT /form HTTP/1.1\n" +
                        "Content-Length: 11\n\n" +
                        "\"My\"=\"Data\"");
        assertTrue(new String(response).contains("200 OK"));
        assertTrue(new String(response).contains(("\"My\"=\"Data\"")));
    }

    private byte[] getResponse(String requestString) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(requestString.getBytes())));
        return requestResponseProcess.directRequestInputForResponseOutput(bufferedReader);
    }
}
