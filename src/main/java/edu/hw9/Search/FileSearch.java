package edu.hw9.Search;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class FileSearch extends RecursiveTask<List<Path>> {
    private Path root;
    private long minFileSize;
    private long maxFileSize;
    private String extension;

    public FileSearch(Path root, long minFileSize, long maxFileSize, String extension) {
        this.root = root;
        this.minFileSize = minFileSize;
        this.maxFileSize = maxFileSize;
        this.extension = extension;
    }

    @Override
    protected List<Path> compute() {
        List<Path> filesAndDirectories;
        try {
            filesAndDirectories = Files.list(root).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Path> files = new ArrayList<>();
        List<Path> subdirectories = new ArrayList<>();
        for (Path content : filesAndDirectories) {
            if (Files.isRegularFile(content)) {
                files.add(content);
            } else if (Files.isDirectory(content)) {
                subdirectories.add(content);
            }
        }
        List<Path> searcedFiles = new ArrayList<>();
        for (Path file : files) {
            long fileSize;
            try {
                fileSize = Files.size(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (fileSize >= minFileSize && fileSize <= maxFileSize && file.getFileName().toString().endsWith(extension))
            {
                searcedFiles.add(file);
            }
        }
        List<FileSearch> tasks = new ArrayList<>();
        for (Path subdirectory : subdirectories) {
            var task = new FileSearch(subdirectory, minFileSize, maxFileSize, extension);
            task.fork();
            tasks.add(task);
        }
        for (var task : tasks) {
            searcedFiles.addAll(task.join());
        }
        return searcedFiles;
    }
}
