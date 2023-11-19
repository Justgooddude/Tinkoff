package edu.hw6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import static edu.hw6.DiskMap.deleteDirectory;
import static edu.hw6.DiskMap.deleteFile;
import static org.junit.jupiter.api.Assertions.*;

class DiskMapTest {
    @Test
    public void map(){
        DiskMap disk = new DiskMap("src/test/java/edu/hw6/test","test.txt");
        disk.put("hello world","1");
        disk.put("2","3");
        disk.save();
        DiskMap testdisk = new DiskMap("src/test/java/edu/hw6/test","test.txt");
        testdisk.upload();
        Assertions.assertEquals(disk.map,testdisk.map);
        deleteFile(Path.of("src/test/java/edu/hw6/test","test.txt"));
        deleteDirectory(Path.of("src/test/java/edu/hw6/test"));
    }

}
