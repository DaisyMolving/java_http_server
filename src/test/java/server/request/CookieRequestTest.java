package server.request;

import org.junit.Test;
import server.DataStore;

import static org.junit.Assert.assertEquals;

public class CookieRequestTest {

    @Test
    public void cookieRequestRespondsWith200WhenCookieIsSet() {
        CookieRequest cookieRequest = new CookieRequest("HTTP/1.1", "chocolate", new DataStore());
        assertEquals("HTTP/1.1 200 OK", cookieRequest.respond().startLine);
    }

    @Test
    public void cookieRequestGivenNoDataDisplaysEat() {
        CookieRequest cookieRequest = new CookieRequest("HTTP/1.1", "chocolate", new DataStore());
        assertEquals("Eat", new String(cookieRequest.respond().bodyContent));
    }

    @Test
    public void cookieRequestGivenDataDisplaysData() {
        DataStore dataStore = new DataStore();
        CookieRequest cookieRequest = new CookieRequest("HTTP/1.1", "chocolate", dataStore);
        cookieRequest.respond();
        CookieRequest secondCookieRequest = new CookieRequest("HTTP/1.1", "vanilla", dataStore);
        assertEquals("mmmm chocolate", new String(secondCookieRequest.respond().bodyContent));
    }
}
