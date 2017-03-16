package server;

import server.handler.Handler;
import server.request.Request;

import java.io.*;
import java.net.Socket;

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

            byte[] responseContent = directRequestInputForResponseOutput(input);

            output.write(responseContent);

            output.close();
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] directRequestInputForResponseOutput(BufferedReader input) throws IOException {

        RequestReader readInput = new RequestReader(input);

        Handler requestHandler = router.routeNewRequest(readInput.getRequestParameters(), dataStore, cookieStore, requestLogStore);

        Request request = requestHandler.send();

        Response response = request.respond();

        return response.generateContent();
    }
}
