package server;

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
        for(;;) {
            clientConnection = acceptConnectionFromClient();

            StringBuilder buffer = new StringBuilder();
            input = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
            output = new PrintStream(clientConnection.getOutputStream());

            String line;
            while(!(line = input.readLine()).equals("")) {
                buffer.append(line + "\n");
            }

            RequestFilter currentRequestFilter = new RequestFilter(buffer.toString());
            Response response = currentRequestFilter.createByType();

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