package edu.hw8.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {
    private static final int SERVER_PORT = 1337;

    private BufferedReader inputStream;
    private BufferedWriter outputStream;

    @Override
    public void run() {
        try {
            try (Socket socket = new Socket("localhost", SERVER_PORT)) {
                inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                outputStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                Scanner scanner = new Scanner(System.in);
                while (true) {
                    if (scanner.hasNextLine()) {
                        String message = scanner.nextLine();

                        if (message.equalsIgnoreCase("exit")) {
                            break;
                        }

                        outputStream.write(message);
                        outputStream.flush();
                        String answer = inputStream.readLine();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
