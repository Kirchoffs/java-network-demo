package org.syh.demo.bio.fulldemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientHandler {
    public static final int MAX_DATA_LEN = 1024;
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        System.out.println("New client connected.");
        new Thread(new Runnable() {
            @Override
            public void run() {
                doStart();
            }
        }).start();
    }

    private void doStart() {
        try {
            InputStream inputStream = socket.getInputStream();
            socket.setSoTimeout(10000);
            while (true) {
                byte[] data = new byte[MAX_DATA_LEN];
                int len;
                while ((len = inputStream.read(data)) != -1) {
                    String message = new String(data, 0, len);
                    System.out.println(String.format("Received message: [%s].", message));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
