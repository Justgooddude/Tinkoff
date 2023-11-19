package edu.hw6;

import org.apache.logging.log4j.core.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DiskMap implements Map<String, String> {
    Path path;
    Map<String, String> map;

    public DiskMap(Path input) {
        path = input;
        map = new HashMap<>();
    }

    public boolean equal(Map<String, String> obj) {
        return map.equals(obj);
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    public boolean containsValue(Object val) {
        return map.containsValue(val);
    }

    public String get(Object key) {
        return map.get(key);
    }

    public String put(String key, String val) {
        map.put(key, val);
        return val;
    }

    public String remove(Object key) {
        map.remove(key);
        return map.get(key);
    }

    public void putAll(Map<? extends String, ? extends String> m) {
        map.putAll(m);
    }

    public void clear() {
        map.clear();
    }

    public Set<String> keySet() {
        return map.keySet();
    }

    public Collection<String> values() {
        return map.values();
    }

    public Set<Map.Entry<String, String>> entrySet() {
        return map.entrySet();
    }

    public void upload() {
        try {
            File file = new File(path.toString());
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                int doubledot = line.indexOf(":");
                String key = line.substring(0, doubledot);
                String value = line.substring(doubledot + 1);
                map.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            List<String> wr = map.entrySet().stream().map(k -> k.getKey() + ":" + k.getValue() + "\n").collect(Collectors.toList());

            Files.write(
                path,wr
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
