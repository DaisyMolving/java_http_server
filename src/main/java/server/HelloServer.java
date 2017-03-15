package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HelloServer {

    private ServerSocket serverSocket;
    private ExecutorService threadPool = Executors.newFixedThreadPool(100);
    private Router router = new Router();
    private DataStore dataStore = new DataStore();
    private DataStore cookieStore = new DataStore();
    private RequestLogStore requestLogStore = new RequestLogStore();

    public void start(String[] args){
        int portNumber = Integer.parseInt(args[1]);
        bindServerSocketToPort(portNumber);
        while(running()){
            Socket clientSocket = acceptConnectionFromClient(serverSocket);
            threadPool.execute(new RequestResponseProcess(clientSocket, router, dataStore, cookieStore, requestLogStore));
        }
        threadPool.shutdown();
    }

    private boolean running() {
        return true;
    }

    private void bindServerSocketToPort(int portNumber) {
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket acceptConnectionFromClient(ServerSocket serverSocket) {
        try {
            return serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}