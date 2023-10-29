package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Frequency<T> {
    public Frequency(){

    }

    public Map<T,Integer> freqDict( List<T> list){
        Map<T,Integer>result = new HashMap<>();
        for ( T element:list) {
            if (result.containsKey(element)){
                result.put(element, result.get(element)+1);
            } else {
                result.put(element,1);
            }

        }
        return result;
    }

}
