package edu.hw8.server;

import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;

class ServerTest {


    private Server server;
    private PipedOutputStream serverOut;
    private ByteArrayOutputStream clientOut;
  //Сделал пееменные,из-за дальнейшей замеены ввода
    private InputStream systemIn;
    private PrintStream systemOut;

    private static final Path LOG = Path.of("src/main/java/edu/hw8/server/AnswerLog.txt");

    @Test
    public void testMultiThreads() throws IOException, InterruptedException {

        serverOut = new PipedOutputStream();
        clientOut = new ByteArrayOutputStream();
        systemIn  = System.in;
        systemOut = System.out;

        System.setOut(new PrintStream(clientOut));

        server = new Server();
        server.start();

        String client1Message = "личности" + System.lineSeparator() + "exit" + System.lineSeparator();
        String client2Message = "интеллект" + System.lineSeparator() + "exit" + System.lineSeparator();
        String expected1 = "ip: 127.0.0.1, request: личности, response: Не переходи на личности там, где их нет.";
        String expected2 = "ip: 127.0.0.1, request: интеллект, response: Чем ниже интеллект, тем громче оскорбления.";

        try (var inputStream = new ByteArrayInputStream(client1Message.getBytes())) {
            System.setIn(inputStream);

            Client client = new Client();
            client.start();

            sleep(1000);
        }


        assertThat(containsInFile(expected1)).isTrue();
        assertThat(containsInFile(expected2)).isTrue();

        sleep(500);

        if (server.isAlive()) {
            server.shutdown();
        }

        serverOut.close();
        clientOut.close();
        System.setIn(systemIn);
        System.setOut(systemOut);
        clearFile(LOG);
    }

    private boolean containsInFile(String expected) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(LOG)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(expected)) {
                    return true;
                }
            }
        }

        return false;
    }
    public static void clearFile(Path filePath) {
        if (!fileExists(filePath)) {
            return;
        }

        deleteFile(filePath);
        createFile(filePath);
    }
    public static void deleteFile(Path filePath) {
        if (!fileExists(filePath)) {
            return;
        }

        try {
            Files.delete(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void createFile(Path filePath) {
        if (fileExists(filePath)) {
            return;
        }

        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean fileExists(Path filePath) {
        return Files.exists(filePath);
    }

}
