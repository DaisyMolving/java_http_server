package server;

import server.handler.Handler;

import java.io.*;
import java.net.*;

public class HelloServer {

    public ServerSocket serverSocket;
    public Socket clientConnection;
    public BufferedReader input;
    public PrintStream output;


    public void start(String[] args) throws IOException, URISyntaxException {
        int portNumber = Integer.parseInt(args[1]);
        bindServerSocketToPort(portNumber);
        Router router = new Router();
        for(;;) {
            clientConnection = acceptConnectionFromClient();

            input = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
            output = new PrintStream(clientConnection.getOutputStream());

            RequestReader request = new RequestReader(input);

            Handler requestHandler = router.routeNewRequest(request.getMethod(), request.getPath(), request.getBody(), request.getProtocolVersion());

            Response response = requestHandler.send().respond();

            output.write(response.generateContent());

            closeSocketConnections(input, output, clientConnection);
        }
    }

    public void bindServerSocketToPort(int portNumber) {
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket acceptConnectionFromClient() {
        try {
            return serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeSocketConnections(BufferedReader input, PrintStream output, Socket clientConnection) throws IOException {
        input.close();
        output.close();
        clientConnection.close();
    }
}