package server.request;

import org.junit.Test;
import server.DataStore;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CookieRequestTest {

    @Test
    public void cookieRequestRespondsWith200WhenCookieIsSet() throws IOException {
        CookieRequest cookieRequest = new CookieRequest("HTTP/1.1", "chocolate", new DataStore());
        assertEquals("HTTP/1.1 200 OK\nSet-Cookie: chocolate\n\nEat", new String(cookieRequest.respond().generateContent()));
    }

    @Test
    public void cookieRequestGivenDataDisplaysData() throws IOException {
        DataStore dataStore = new DataStore();
        CookieRequest cookieRequest = new CookieRequest("HTTP/1.1", "chocolate", dataStore);
        cookieRequest.respond();
        CookieRequest secondCookieRequest = new CookieRequest("HTTP/1.1", "vanilla", dataStore);
        assertEquals("HTTP/1.1 200 OK\nSet-Cookie: vanilla\n\nmmmm chocolate", new String(secondCookieRequest.respond().generateContent()));
    }
}
