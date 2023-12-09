package edu.hw9.Search;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import static edu.hw9.Search.ForTests.createDirectory;
import static edu.hw9.Search.ForTests.createFile;
import static edu.hw9.Search.ForTests.recursiveDelete;
import static edu.hw9.Search.ForTests.write;
import static org.assertj.core.api.Assertions.assertThat;

class DirectorySearchTest {
    private Path root = Paths.get("src/test/java/edu/hw9/Search/");

    @Test
    void compute() {
        createDirectory(root.resolve("test/"));
        createDirectory(root.resolve("test/test1/"));
        createDirectory(root.resolve("test/test2/"));
        createDirectory(root.resolve("test/test1/test21/"));
        createDirectory(root.resolve("test/test1/test22/"));
        createFile(root.resolve("test/test1/test21.txt"));
        write(root.resolve("test/test1/test21.txt"), "6BYTES");
        createFile(root.resolve("test/test1/test21/test31.txt"));
        createFile(root.resolve("test/test1/test22/test31.md"));
        write(root.resolve("test/test1/test22/test31.md"), "6BYTES");
        createFile(root.resolve("test/test2/test21.txt"));
        createFile(root.resolve("test/test2/test22.txt"));
        write(root.resolve("test/test2/test22.txt"), "7 BYTES");
        List<Path> result;
        try (var forkJoinPool = new ForkJoinPool()) {
             result = forkJoinPool.invoke(
                new DirectorySearch(root.resolve("test/"), 2)
            ).directories();
        }
        assertThat(result).hasSize(2);
      recursiveDelete(root.resolve("test/").toFile());

    }
}
