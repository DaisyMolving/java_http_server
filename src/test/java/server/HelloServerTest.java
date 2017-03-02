package server;

import org.junit.After;
import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

    public class HelloServerTest {

//        private HelloServer server = new HelloServer();

//        @After
//        public void shutDownServer() throws IOException {
//            server.closeSocketConnections(server.input, server.output, server.clientConnection);
//        }

//        @Test
//        public void runsServerOnPort5000() throws IOException {
//            runTheServer();
//            assertEquals(5000, server.serverSocket.getLocalPort());
//        }

//        @Test
//        public void acceptsConnectionFromClient() throws IOException, InterruptedException {
//            runTheServer();
//            Socket socket = new Socket("localhost", 5000);
//            assertTrue(socket.isConnected());
//        }

//        @Test
//        public void servesResponseToClient() throws Exception {
//            runTheServer();
//            Socket clientSocket = new Socket("localhost",5000);
//            PrintWriter p  = new PrintWriter(clientSocket.getOutputStream(), true);
//            p.println("GET / HTTP/1.1");
//            String echo = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())).readLine();
//            assertEquals("HTTP/1.1 200 OK", echo);
//        }

//        private void runTheServer() {
//            String[] args = new String[2];
//            args[0] = "-p";
//            args[1] = "5000";
//            ExecutorService serverThread = Executors.newSingleThreadExecutor();
//            serverThread.submit(() -> {
//                try {
//                    server.start(args);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (URISyntaxException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
}