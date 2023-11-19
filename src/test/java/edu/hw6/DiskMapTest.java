package edu.hw6;

import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class DiskMapTest {
    @Test
    public void map(){
        DiskMap disk = new DiskMap(Path.of("disk.txt"));
        disk.put("hello world","1");
        disk.put("2","3");
        disk.save();
        DiskMap testdisk = new DiskMap(Path.of("disk.txt"));
        testdisk.upload();
        assertEquals(disk,testdisk);
    }

}
