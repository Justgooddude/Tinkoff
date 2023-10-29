package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ContactsTest {

    @Test
    void parseContactstest() {
        Contacts contacts = new Contacts();
        String[] contactex = {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        List<String> example = Arrays.asList(contactex);
        String sortType = "ASC";
        List<String> result = contacts.parseContacts(example, sortType);
        List<String> test = new ArrayList<>();
        test.add("Paul Erdos");
        test.add("Leonhard Euler");
        test.add("Carl Gauss");
        Assertions.assertEquals(result, test);
    }
}
