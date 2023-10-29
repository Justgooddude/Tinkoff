package edu.hw3;

import java.util.Comparator;

public class TreeComp {
    Comparator<String> treecomp = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            if (o1 == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }
            return compare(o1, o2);
        }
    };
}
