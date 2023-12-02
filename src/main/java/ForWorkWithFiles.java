import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ForWorkWithFiles {

    private ForWorkWithFiles(){
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
    public static void clearFile(Path filePath) {
        if (!fileExists(filePath)) {
            return;
        }

        deleteFile(filePath);
        createFile(filePath);
    }

}
