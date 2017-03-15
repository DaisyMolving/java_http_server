package server;

import server.handler.Handler;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;

public class RequestResponseProcess implements Runnable{

    private final Router router;
    private final DataStore dataStore;
    private final DataStore cookieStore;
    private final RequestLogStore requestLogStore;
    private Socket clientSocket = null;

    public RequestResponseProcess(Socket clientSocket, Router router, DataStore dataStore, DataStore cookieStore, RequestLogStore requestLogStore) {
        this.clientSocket = clientSocket;
        this.router = router;
        this.dataStore = dataStore;
        this.cookieStore = cookieStore;
        this.requestLogStore = requestLogStore;
    }

    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintStream output = new PrintStream(clientSocket.getOutputStream());
            RequestReader request = new RequestReader(input);
            Handler requestHandler = router.routeNewRequest(request.getRequestParameters(), dataStore, cookieStore, requestLogStore);
            Response response = requestHandler.send().respond();
            output.write(response.generateContent());
            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
