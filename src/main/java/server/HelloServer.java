package server;

import java.io.*;
import java.net.*;

public class HelloServer {

    public ServerSocket serverSocket;
    public Socket clientConnection;


    public void start(String[] args) throws IOException {
        int portNumber= Integer.parseInt(args[1]);
        bindServerSocketToPort(portNumber);
        for(;;) {
            acceptConnectionFromClient();

            BufferedReader input = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
            PrintStream output = new PrintStream(clientConnection.getOutputStream());

            Request currentRequest = RequestFilter.createByType(input.readLine());

            Response currentResponse = currentRequest.createResponse();

            for (String responseLine : currentResponse.generateContent()) {
                output.println(responseLine);
            }

            input.close();
            output.close();
            clientConnection.close();
            System.out.println("I am looping to find new requests");
        }
    }

    public void bindServerSocketToPort(int portNumber) {
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void acceptConnectionFromClient() {
        try {
            clientConnection = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}