package org.syh.demo.bio.fulldemo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println(String.format("Server started on port %d.", port));
        } catch (IOException exception) {
            System.out.println("Failed to start the server.");
        }
    }

    public void start() {
        doStart();
        System.out.println("It should be unreachable.");
    }

    private void doStart() {
        while (true) {
            try {
                Socket client = serverSocket.accept();
                new ClientHandler(client).start();
            } catch (IOException e) {
                System.out.println("Exception on server side.");
            }
        }
    }
}
