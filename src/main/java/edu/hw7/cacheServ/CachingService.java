package edu.hw7.cacheServ;

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
        idCache.put(person.id(), person);
        List<Person> input = nameCache.remove(person.name());
        input.add(person);
        nameCache.put(person.name(), input);
        input = addressCache.remove(person.address());
        input.add(person);
        addressCache.put(person.address(), input);
        input = phoneNumberCache.remove(person.phoneNumber());
        input.add(person);
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

