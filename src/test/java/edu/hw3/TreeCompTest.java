package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.*;

class TreeCompTest {
    Comparator<String> treecomp = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            if(o1==null && o2==null){
                return 0;
            }
            if(o1==null){
                return -1;
            }
            if (o2==null){
                return 1;
            }
            return compare(o1,o2);
        }
    };@Test
    void compare() {
        TreeMap<String, String> tree= new TreeMap<>(treecomp);
        tree.put(null, "test");
        boolean result= tree.containsKey(null);
        Assertions.assertEquals(result,true);
    }
}
