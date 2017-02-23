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

            Request currentRequest = new Request(input.readLine());


            if (currentRequest.getMethodVerb().equals("GET")) {
                System.out.println("get request");
                if (currentRequest.getPath().equals("/")) {
                    output.println(currentRequest.getProtocolVersion() + " " + "200 OK");
                } else if (currentRequest.getPath().equals("/text-file.txt")) {
                    output.println(currentRequest.getProtocolVersion() + " " + "200 OK");
                } else if (currentRequest.getPath().equals("/redirect")) {
                    output.println(currentRequest.getProtocolVersion() + " " + "302 Found");
                    output.println("Location: http://localhost:5000/");
                } else if (currentRequest.getPath().equals("/tea")) {
                    output.println(currentRequest.getProtocolVersion() + " " + "200 OK");
                } else if (currentRequest.getPath().equals("/coffee")) {
                    output.println(currentRequest.getProtocolVersion() + " " + "418 I'm a teapot");
                    output.println("");
                    output.println("I'm a teapot");
                } else {
                    output.println(currentRequest.getProtocolVersion() + " " + "404 Not Found");
                }
            }

            else if (currentRequest.getMethodVerb().equals("OPTIONS")) {
                System.out.println("options request");
                if (currentRequest.getPath().equals("/method_options")) {
                    output.println(currentRequest.getProtocolVersion() + " " + "200 OK");
                    output.println("Allow: GET,HEAD,POST,OPTIONS,PUT");
                } else if (currentRequest.getPath().equals("/method_options2")) {
                    output.println(currentRequest.getProtocolVersion() + " " + "200 OK");
                    output.println("Allow: GET,OPTIONS");
                } else {
                    output.println(currentRequest.getProtocolVersion() + " " + "200 OK");
                }
            }

            else if (currentRequest.getMethodVerb().equals("POST")) {
                System.out.println("post request");
                if (currentRequest.getPath().equals("/form")) {
                    output.println(currentRequest.getProtocolVersion() + " " + "200 OK");
                } else {
                    output.println(currentRequest.getProtocolVersion() + " " + "405 Method Not Allowed");
                }
            }

            else if (currentRequest.getMethodVerb().equals("PUT")) {
                System.out.println("put request");
                if (currentRequest.getPath().equals("/form")) {
                    output.println(currentRequest.getProtocolVersion() + " " + "200 OK");
                } else {
                    output.println(currentRequest.getProtocolVersion() + " " + "405 Method Not Allowed");
                }
            }

            else if (currentRequest.getMethodVerb().equals("HEAD")) {
                System.out.println("head request");
                if (currentRequest.getPath().equals("/")) {
                    output.println(currentRequest.getProtocolVersion() + " " + "200 OK");
                } else {
                    output.println(currentRequest.getProtocolVersion() + " " + "404 Not Found");
                }
            }

            else {
                System.out.println("bogus request");
                output.println(currentRequest.getProtocolVersion() + " " + "405 Method Not Allowed");
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