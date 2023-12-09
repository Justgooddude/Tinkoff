package edu.hw9.Search;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static edu.hw9.Search.ForTests.createDirectory;
import static edu.hw9.Search.ForTests.createFile;
import static edu.hw9.Search.ForTests.deleteFile;
import static edu.hw9.Search.ForTests.recursiveDelete;
import static edu.hw9.Search.ForTests.write;
import static org.junit.jupiter.api.Assertions.*;

class FileSearchTest {
    private Path root = Paths.get("src/test/java/edu/hw9/task2/");

    @Test
    void compute() {
        createDirectory(root.resolve("test/"));
        createDirectory(root.resolve("test/test/"));
        createDirectory(root.resolve("test/test2/"));
        createDirectory(root.resolve("test/test/test21/"));
        createDirectory(root.resolve("test/test/test22/"));
        createFile(root.resolve("test/test/test21.txt"));
        write(root.resolve("test/test/test21.txt"), "6BYTES");
        createFile(root.resolve("test/test/test21/test31.txt"));
        createFile(root.resolve("test/test/test22/test31.md"));
        write(root.resolve("test/test/test22/test31.md"), "6BYTES");
        createFile(root.resolve("test/test2/test21.txt"));
        createFile(root.resolve("test/test2/test22.txt"));
        write(root.resolve("test/test2/test22.txt"), "7 BYTES");
        recursiveDelete(root.resolve("test").toFile());
    }


}
