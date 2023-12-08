package edu.hw9.Search;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;

public final class ForTests {
    private ForTests() {
    }

    public static void createFile(Path filePath) {
        if (Files.exists(filePath)) {
            return;
        }

        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createDirectory(Path directoryPath) {
        if (Files.exists(directoryPath)) {
            return;
        }

        try {
            Files.createDirectory(directoryPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteFile(Path filePath) {
        if (!Files.exists(filePath)) {
            return;
        }

        try {
            Files.delete(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteDirectory(Path directoryPath) throws IOException {
        for (Path file : Files.list(directoryPath).collect(Collectors.toList())){
            if(Files.isDirectory(file)){
                deleteDirectory(file);
            }
            Files.delete(file);
        }
        Files.delete(directoryPath);
    }
    public static void write(Path filePath, String string) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.APPEND)) {
            writer.write(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
