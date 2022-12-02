package org.syh.demo.bio.serverdemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServer {
    private static ExecutorService executors = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(1271));
        try {
            while (true) {
                // Blocker 1
                Socket socket = serverSocket.accept();
                System.out.println("Create new connection");
                executors.execute(() -> {
                    while (true) {
                        InputStream inputStream;
                        try {
                            inputStream = socket.getInputStream();
                            byte[] result = new byte[1024];
                            // Blocker 2
                            int len = inputStream.read(result);
                            if (len != -1) {
                                System.out.println("Receive: " + new String(result, 0, len));
                                OutputStream outputStream = socket.getOutputStream();
                                outputStream.write("Received".getBytes());
                                outputStream.flush();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                });
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}