package edu.hw6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FileCloner {
    public void cloneFile(Path path) {
        Path direc = path.getParent();
        long max = 0;
        String nametxt = path.getFileName().toString();
        String name = nametxt.substring(0, nametxt.lastIndexOf("."));
        try (Stream stream = Files.list(direc)) {
            max = Files.list(direc).
                filter(file -> Pattern.matches(name + ".*" + "[.]txt", file.getFileName().toString()))
                .count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String copy = name + " - копия (" + String.valueOf(max) + ").txt";
        try {
            Files.createFile(Path.of(direc.toString() + copy));
            Files.copy(path, Path.of(direc.toString() + copy));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
