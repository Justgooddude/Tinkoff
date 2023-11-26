package edu.hw7.cacheServ;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWCacheDB implements PersonDatabase {
    private final Map<Integer, Person> idCache = new HashMap<>();
    private final Map<String, List<Person>> nameCache = new HashMap<>();
    private final Map<String, List<Person>> addressCache = new HashMap<>();
    private final Map<String, List<Person>> phoneNumberCache = new HashMap<>();
    private final ReadWriteLock rwLocker = new ReentrantReadWriteLock();

    @Override
    public synchronized void add(Person person) {
        rwLocker.writeLock().lock();
        try {
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
        } finally {
            rwLocker.writeLock().unlock();
        }
    }

    @Override
    public synchronized void delete(int id) {
        rwLocker.writeLock().lock();
        try {
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
        } finally {
            rwLocker.writeLock().unlock();
        }

    }

    @Override
    public synchronized List<Person> findByName(String name) {
        rwLocker.readLock().lock();
        try {
            return nameCache.getOrDefault(name, List.of());
        } finally {
            rwLocker.readLock().unlock();
        }
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        rwLocker.readLock().lock();
        try {
            return addressCache.getOrDefault(address, List.of());
        } finally {
            rwLocker.readLock().unlock();
        }
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        rwLocker.readLock().lock();
        try {
            return phoneNumberCache.getOrDefault(phone, List.of());
        } finally {
            rwLocker.readLock().unlock();
        }
    }
}
