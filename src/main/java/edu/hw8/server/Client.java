package edu.hw8.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {
    private static final int SERVER_PORT = 1337;
    private static final int BUFFER_SIZE = 1024;

    @Override
    public void run() {
        try {
            try (Socket socket = new Socket("localhost", SERVER_PORT)) {
                byte[] buffer = new byte[BUFFER_SIZE];

                Scanner scanner = new Scanner(System.in);
                while (true) {
                    if (scanner.hasNextLine()) {
                        String message = scanner.nextLine();

                        if (message.equalsIgnoreCase("exit")) {
                            break;
                        }

                        socket.getOutputStream().write(message.getBytes());
                        int size = socket.getInputStream().read(buffer);
                        String answer = new String(buffer, 0, size);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

