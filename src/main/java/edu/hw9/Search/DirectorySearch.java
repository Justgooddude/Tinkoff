package edu.hw9.Search;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class DirectorySearch extends RecursiveTask<DirRes> {
    private Path root;
    private int fileMin;

    public DirectorySearch(Path root, int requiredFilesNumber) {
        this.root = root;
        this.fileMin = requiredFilesNumber;
    }

    @Override
    protected DirRes compute() {
        List<Path> filesAndDirectories;
        try {
            filesAndDirectories = Files.list(root).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int filesNumber = 0;
        List<Path> subDirs = new ArrayList<>();
        for (Path content : filesAndDirectories) {
            if (Files.isRegularFile(content)) {
                ++filesNumber;
            } else if (Files.isDirectory(content)) {
                subDirs.add(content);
            }
        }
        List<DirectorySearch> tasks = new ArrayList<>();
        for (Path subdirectory : subDirs) {
            var task = new DirectorySearch(subdirectory, fileMin);
            task.fork();
            tasks.add(task);
        }
        List<Path> searchedDirs = new ArrayList<>();
        for (var task : tasks) {
            DirRes result = task.join();

            searchedDirs.addAll(result.directories());
            filesNumber += result.filesNumber();
        }
        if (filesNumber > fileMin) {
            searchedDirs.add(root);
        }
        return new DirRes(
            searchedDirs,
            filesNumber
        );
    }
}
