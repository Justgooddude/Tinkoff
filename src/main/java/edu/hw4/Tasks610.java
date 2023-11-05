package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Tasks610 {
    public Map<Animal.Type, Animal> heavistFromType(List<Animal> list) {
        return list.stream()
            .collect(Collectors.toMap(e -> e.type(), e -> list.stream()
                .filter(n -> n.type() == e.type())
                .max(Comparator.comparing(Animal::weight))
                .orElseThrow(), (first, second) -> second));
    }

    public Animal kthEldest(List<Animal> list, Integer k) {
        return list.stream()
            .sorted(Comparator.comparing(Animal::age))
            .skip(k).findFirst().get();
    }

    public Optional<Animal> heviestUnderKcm(List<Animal> list, Integer k) {
        return Optional.of(list.stream()
            .filter(n -> n.height() <= k)
            .max(Comparator.comparing(Animal::weight))
            .orElseThrow());
    }

    public Integer pawnSum(List<Animal> list) {
        return list.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public List<Animal> pawnsNotAge(List<Animal> list) {
        return list.stream()
            .filter(n -> n.age() != n.paws())
            .collect(Collectors.toList());
    }
}
