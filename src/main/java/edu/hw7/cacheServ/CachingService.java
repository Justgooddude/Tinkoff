package edu.hw7.cacheServ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CachingService implements PersonDatabase {
    private final Map<Integer, Person> idCache = new HashMap<>();
    private final Map<String, List<Person>> nameCache = new HashMap<>();
    private final Map<String, List<Person>> addressCache = new HashMap<>();
    private final Map<String, List<Person>> phoneNumberCache = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        List<Person> input = new ArrayList<>();
        idCache.put(person.id(), person);
        try {
            input.addAll(nameCache.remove(person.name()));
        } catch (NullPointerException e) {
            input = new ArrayList<>();
        }
        if (!input.contains(person)) {
            input.add(person);
        }
        nameCache.put(person.name(), input);
        try {
            input.addAll(addressCache.remove(person.name()));
        } catch (NullPointerException e) {
            input = new ArrayList<>();
        }
        if (!input.contains(person)) {
            input.add(person);
        }
        addressCache.put(person.address(), input);
        try {
            input.addAll(phoneNumberCache.remove(person.phoneNumber()));
        } catch (NullPointerException e) {
            input = new ArrayList<>();
        }
        if (!input.contains(person)) {
            input.add(person);
        }
        phoneNumberCache.put(person.phoneNumber(), input);
    }

    @Override
    public synchronized void delete(int id) {
        Person person = idCache.remove(id);

        if (person != null) {
            List<Person> res = nameCache.remove(person.name());
            res.remove(person);
            nameCache.put(person.name(), res);
            res = addressCache.remove(person.address());
            res.remove(person);
            addressCache.put(person.address(), res);
            res = phoneNumberCache.remove(person.phoneNumber());
            res.remove(person);
            phoneNumberCache.put(person.phoneNumber(), res);
        }
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return nameCache.getOrDefault(name, List.of());
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return addressCache.getOrDefault(address, List.of());
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return phoneNumberCache.getOrDefault(phone, List.of());
    }
}

