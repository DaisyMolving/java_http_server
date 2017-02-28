package server;

import java.io.*;
import java.net.*;

public class HelloServer {

    public ServerSocket serverSocket;


    public void start(String[] args) throws IOException {
        int portNumber = Integer.parseInt(args[1]);
        bindServerSocketToPort(portNumber);
        for(;;) {
            Socket clientConnection = acceptConnectionFromClient();

            BufferedReader input = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
            PrintStream output = new PrintStream(clientConnection.getOutputStream());

            RequestFilter currentRequestFilter = new RequestFilter(input.readLine());
            Request currentRequest = currentRequestFilter.createByType();

            Response currentResponse = currentRequest.createResponse();

            output.print(currentResponse.generateContent());

            closeSocketConnections(input, output, clientConnection);

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