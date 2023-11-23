package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class Realisation {
    public DirectoryFilter regularFile = Files::isRegularFile;
    public DirectoryFilter readable = Files::isReadable;
    public DirectoryFilter writable = Files::isWritable;

    public DirectoryFilter largerThan(long num) {
        return file -> Files.size(file) > num;
    }

    public DirectoryFilter globMatches(String glob) {
        return path -> Pattern.matches(".*" + glob, path.toString());
    }

    public DirectoryFilter regexcontains(String reg) {
        return path -> Pattern.matches(".*" + reg + ".*", path.toString());
    }

    public interface DirectoryFilter extends DirectoryStream.Filter<Path> {
        @Override
        boolean accept(Path entry) throws IOException;
    }

}
