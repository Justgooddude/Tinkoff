package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Tasks15 {
    public List<Animal> heigtSort(List<Animal> list) {
        return list.stream()
            .sorted(Comparator.comparing(Animal::height))
            .collect(Collectors.toList());

    }

    public List<Animal> weigtSort(List<Animal> list, int k) {
        return list.stream()
            .sorted(Comparator.comparing(Animal::weight))
            .limit(k)
            .collect(Collectors.toList());
    }

    public Map<Animal.Type, Integer> typeCounter(List<Animal> list) {
        Map<Animal.Type, Integer> map = list.stream()
            .collect(Collectors.toMap(e -> e.type(), e -> 1, Integer::sum));
        return map;
    }

    public Animal longestName(List<Animal> list) {
        return list.stream()
            .max(Comparator.comparing(x -> x.name().length()))
            .orElseThrow();
    }

    public Animal.Sex biggerSex(List<Animal> list) {
        return list.stream().collect(Collectors.toMap(e -> e.sex(), e -> 1, Integer::sum))
            .entrySet().stream()
            .max(Comparator.comparing(Map.Entry::getValue)).orElseThrow()
            .getKey();
    }
}
