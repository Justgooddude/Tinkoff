package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Tasks1620 {
    public List<Animal> complexSort(List<Animal> list) {
        return list.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .collect(Collectors.toList());
    }

    public Boolean spiderAngry(List<Animal> list) {
        Long dogProb = list.stream()
            .filter(n -> n.type() == Animal.Type.DOG)
            .count();
        Long dogbites = (list.stream()
            .filter(n -> n.type() == Animal.Type.DOG && n.bites())
            .count());
        Long spider = list.stream()
            .filter(n -> n.type() == Animal.Type.SPIDER)
            .count();
        Long spiderbites = (list.stream()
            .filter(n -> n.type() == Animal.Type.SPIDER && n.bites())
            .count());
        if (dogbites == 0) {
            return true;
        }
        if (spiderbites == 0) {
            return false;
        }
        if (spiderbites == dogbites) {
            return false;
        }
        return dogProb / dogbites < spider / spiderbites;
    }

    public Animal heaviestFish(List<List<Animal>> lists) {
        return lists.stream()
            .flatMap(List::stream)
            .filter(n -> n.type() == Animal.Type.FISH)
            .max(Comparator.comparing(Animal::weight))
            .orElseThrow();
    }

    public class ValidationError extends Exception {
        public ValidationError(String message) {
            super(message);
        }
    }
}
