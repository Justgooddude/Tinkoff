package edu.hw6;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class DiskMap implements Map<String, String> {
    final Path path;
    final Path filename;
    final Map<String, String> map;

    public DiskMap(String input, String filen) {
        path = Path.of(input);
        filename = Path.of(input, filen);
        createDirectory(path);
        createFile(filename);
        map = new HashMap<>();
        upload();
    }

    public boolean equal(Map<String, String> obj) {
        return map.equals(obj);
    }
    public boolean equals(DiskMap disk){
        return map.equals(disk.map);
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    public boolean containsValue(Object val) {
        return map.containsValue(val);
    }

    public String get(Object key) {
        return map.get(key);
    }

    public String put(String key, String val) {
        map.put(key, val);
        return val;
    }

    public String remove(Object key) {
        map.remove(key);
        return map.get(key);
    }

    public void putAll(Map<? extends String, ? extends String> m) {
        map.putAll(m);
    }

    public void clear() {
        map.clear();
    }

    public Set<String> keySet() {
        return map.keySet();
    }

    public Collection<String> values() {
        return map.values();
    }

    public Set<Map.Entry<String, String>> entrySet() {
        return map.entrySet();
    }

    public void upload() {
        try (Stream<String> lines = Files.lines(filename)) {
            lines.forEach(line -> {
                String[] parts = line.split(":");
                map.put(parts[0], parts[1]);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(filename)) {
            map.forEach((key, value) -> {
                try {
                    String entry = key + ":" + value + System.lineSeparator();
                    writer.write(entry);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    public static void deleteDirectory(Path directoryPath) {
        if (!Files.exists(directoryPath)) {
            return;
        }

        try {
            Files.delete(directoryPath);
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

}
