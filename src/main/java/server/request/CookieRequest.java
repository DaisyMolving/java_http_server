package server.request;

import server.DataStore;
import server.Response;

import java.util.ArrayList;
import java.util.List;

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
        byte[] bodyContent;
        List<String> headerFields = new ArrayList<>();

        if (cookieToAddExists() && cookieStoreIsEmpty()) {

            headerFields.add(protocolVersion + " 200 OK");
            headerFields.add("Set-Cookie: " + cookieToAdd);
            bodyContent = "Eat".getBytes();
            cookieStore.update("mmmm " + cookieToAdd);

        } else if (cookieToAddExists() && storeHasCookie()) {

            headerFields.add(protocolVersion + " 200 OK");
            headerFields.add("Set-Cookie: " + cookieToAdd);
            bodyContent = cookieStore.read().getBytes();
            cookieStore.update("mmmm " + cookieToAdd);

        } else {

            headerFields.add(protocolVersion + " 200 OK");
            bodyContent = cookieStore.read().getBytes();
            cookieStore.delete();

        } return new Response(headerFields, bodyContent);
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
