package edu.hw3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class Contacts {
    public Contacts() {
    }

    public static <T> List<T> reverseList(List<T> list) {
        List<T> reverse = new ArrayList<>(list);
        Collections.reverse(reverse);
        return reverse;
    }

    public List<String> parseContacts(List<String> conactlist, String sorttype) {
        List<String> result = new ArrayList<>();
        if (conactlist == null || conactlist.size() == 0) {
            return result;
        }
        if (sorttype.equals("DESC")) {
            result = conactlist.stream().sorted().collect(Collectors.toList());
        }
        if (sorttype.equals("ASC")) {
            result = conactlist.stream().sorted().collect(Collectors.toList());
            result = reverseList(result);
        }
        return result;
    }
}
