package server;

import java.io.*;
import java.net.*;
import java.util.HashMap;

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
            String request = input.readLine();
            System.out.println(request);

            HashMap<String, String> requestDefinition = define(request);
            String methodVerb = requestDefinition.get("methodVerb");
            String path = requestDefinition.get("path");
            String protocolVersion = requestDefinition.get("protocolVersion");

            if (methodVerb.equals("GET")) {
                System.out.println("get request");
                if (path.equals("/")) {
                    output.println(protocolVersion + " " + "200 OK");
                } else {
                    output.println(protocolVersion + " " + "404 Not Found");
                }
            }

            if (methodVerb.equals("POST")) {
                System.out.println("get request");
                output.println(protocolVersion + " " + "200 OK");
            }

            if (methodVerb.equals("PUT")) {
                System.out.println("get request");
                output.println(protocolVersion + " " + "200 OK");
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

    private HashMap<String, String> define(String request) {
        String[] splitRequest = request.split("\\s+");
        HashMap<String, String> requestDefinition = new HashMap<>();
        requestDefinition.put("methodVerb", splitRequest[0]);
        requestDefinition.put("path", splitRequest[1]);
        requestDefinition.put("protocolVersion", splitRequest[2]);
        return requestDefinition;
    }
}