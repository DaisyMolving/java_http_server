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

            HashMap<String, String> requestDefinition = define(request);
            String methodVerb = requestDefinition.get("methodVerb");
            String path = requestDefinition.get("path");
            String protocolVersion = requestDefinition.get("protocolVersion");

            if (methodVerb.equals("GET")) {
                System.out.println("get request");
                if (path.equals("/")) {
                    output.println(protocolVersion + " " + "200 OK");
                } else if (path.equals("/file1")) {
                    output.println(protocolVersion + " " + "200 OK");
                } else if (path.equals("/text-file.txt")) {
                    output.println(protocolVersion + " " + "200 OK");
                } else if (path.equals("/redirect")) {
                    output.println(protocolVersion + " " + "302 Found");
                    output.println("Location: http://localhost:5000/");
                } else if (path.equals("/tea")) {
                    output.println(protocolVersion + " " + "200 OK");
                } else if (path.equals("/coffee")) {
                    output.println(protocolVersion + " " + "418 I'm a teapot");
                    output.println("");
                    output.println("I'm a teapot");
                } else {
                    output.println(protocolVersion + " " + "404 Not Found");
                }
            }

            else if (methodVerb.equals("OPTIONS")) {
                System.out.println("options request");
                if (path.equals("/method_options")) {
                    output.println(protocolVersion + " " + "200 OK");
                    output.println("Allow: GET,HEAD,POST,OPTIONS,PUT");
                } else if (path.equals("/method_options2")) {
                    output.println(protocolVersion + " " + "200 OK");
                    output.println("Allow: GET,OPTIONS");
                } else {
                    output.println(protocolVersion + " " + "200 OK");
                }
            }

            else if (methodVerb.equals("POST")) {
                System.out.println("post request");
                if (path.equals("/form")) {
                    output.println(protocolVersion + " " + "200 OK");
                } else {
                    output.println(protocolVersion + " " + "405 Method Not Allowed");
                }
            }

            else if (methodVerb.equals("PUT")) {
                System.out.println("put request");
                if (path.equals("/form")) {
                    output.println(protocolVersion + " " + "200 OK");
                } else {
                    output.println(protocolVersion + " " + "405 Method Not Allowed");
                }
            }

            else if (methodVerb.equals("HEAD")) {
                System.out.println("head request");
                if (path.equals("/")) {
                    output.println(protocolVersion + " " + "200 OK");
                } else {
                    output.println(protocolVersion + " " + "404 Not Found");
                }
            }

            else {
                System.out.println("bogus request");
                output.println(protocolVersion + " " + "405 Method Not Allowed");
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