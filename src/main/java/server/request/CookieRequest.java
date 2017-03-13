package server.request;

import server.DataStore;
import server.Response;

public class CookieRequest implements Request {

    private String protocolVersion;
    private String cookieToAdd;
    private DataStore cookieStore;

    public CookieRequest(String protocolVersion, String cookieToAdd, DataStore cookieStore) {
        this.protocolVersion = protocolVersion;
        this.cookieToAdd = cookieToAdd;
        this.cookieStore = cookieStore;
    }

    public Response respond() {
        String contentBody;
        if (cookieToAddExists() && cookieStoreIsEmpty()) {
            contentBody = "Eat";
            cookieStore.update("mmmm " + cookieToAdd);
        } else if (cookieToAddExists() && storeHasCookie()) {
            contentBody = cookieStore.read();
            cookieStore.update("mmmm " + cookieToAdd);
        } else {
            contentBody = cookieStore.read();
            cookieStore.delete();
        } return new Response(
                protocolVersion + " 200 OK",
                "",
                "",
                "",
                contentBody.getBytes());
    }

    private boolean storeHasCookie() {
        return !cookieStoreIsEmpty();
    }

    private boolean cookieStoreIsEmpty() {
        return cookieStore.read().equals("");
    }

    private boolean cookieToAddExists() {
        return !(cookieToAdd.equals(""));
    }

}
