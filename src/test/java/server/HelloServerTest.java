package server;

import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HelloServerTest {

//    public HelloServer server = new HelloServer();

//    @Test
//    public void splitsRequestIntoKeyValuePairs() {
//        String request = "GET /hello HTTP/1.1";
//        assertEquals("hello",  server.define(request));
//    }
//    @Test
//    public void acceptsConnectionFromClient() {
//        runTheServer();
//        assertEquals(5000, server.serverSocket.getLocalPort());
//    }
//
////    @Test
////    public void createsAURL() throws MalformedURLException {
////        String[] args = new String[2];
////        args[0] = "localhost:";
////        args[0] = "5000";
////        server.createURL(args);
////        assertEquals("localhost", server.url.getHost());
////        assertEquals("5000", server.url.getPort());
////    }
//
////    @Test
////    public void sendsAGetRequest() throws Exception {
////        server.sendGet();
////        assertEquals(200, server.httpConnection.getResponseCode());
////    }
//
//    private void runTheServer() {
//        String[] args = new String[2];
//        args[0] = "-p";
//        args[1] = "5000";
//        ExecutorService serverThread = Executors.newSingleThreadExecutor();
//        serverThread.submit(() -> {
//            try {
//                server.start(args);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
}