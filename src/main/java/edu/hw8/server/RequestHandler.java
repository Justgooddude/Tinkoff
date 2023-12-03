package edu.hw8.server;

import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
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

    public RequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            byte[] buffer = new byte[BUFFERSIZE];
            String request;
            while (true) {
                int size = clientSocket.getInputStream().read(buffer);

                if (size != -1) {
                    request = new String(buffer, 0, size);

                    String response = ANSWERS.getOrDefault(request, "Переходи на нашу сторону, у нас есть котики");
                    String log = String.format(
                        "ip: %s, request: %s, response: %s%n",
                        clientSocket.getInetAddress().getHostAddress(), request, response
                    );
                    Files.write(LOGPATH, log.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                    clientSocket.getOutputStream().write(response.getBytes());
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
