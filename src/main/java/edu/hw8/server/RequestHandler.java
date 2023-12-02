package edu.hw8.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.file.Path;
import java.util.Map;

public class RequestHandler implements Runnable {
    private final static Path LOGPATH = Path.of("src/main/java/edu/hw8/server/AnswerLog.txt");
    private final static Map<String, String> ANSWERS = Map.of(
        "личности", "Не переходи на личности там, где их нет.",
        "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверен — твоя победа не за горами.",
        "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно. Ты просто бог идиотизма.",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления."
    );
    private final static int BUFFERSIZE = 1024;

    private Socket clientSocket;
    private BufferedReader inputStream;
    private BufferedWriter outputStream;
    private BufferedWriter logwriter;

    public RequestHandler(Socket clientSocket) {
        try {
            this.clientSocket = clientSocket;
            this.inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.outputStream = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            this.logwriter = new BufferedWriter(new FileWriter(LOGPATH.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            String request;
            while ((request = inputStream.readLine()) != null) {
                String response = ANSWERS.getOrDefault(request, "Ответа нет,но есть котики");
                String logEntry = String.format(
                    "ip: %s, request: %s, response: %s%n",
                    clientSocket.getInetAddress().getHostAddress(), request, response
                );
                logwriter.write(logEntry);
                outputStream.write(response);
                outputStream.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (logwriter != null) {
                    logwriter.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
