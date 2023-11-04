package edu.hw4;

import java.util.List;
import java.util.stream.Collectors;

public class Tasks1115 {
    @SuppressWarnings("MagicNumber")

    public List<Animal> bigBiter(List<Animal> list) {
        return list.stream()
            .filter(n -> n.bites())
            .filter(n -> n.height() >= 100)
            .collect(Collectors.toList());
    }

    public Integer heavierThanTall(List<Animal> list) {
        return (int) list.stream()
            .filter(n -> n.weight() > n.height())
            .count();
    }

    public List<Animal> twoPartNames(List<Animal> list) {
        return list.stream()
            .filter(n -> n.name().split(" ").length >= 2)
            .collect(Collectors.toList());
    }

    public Boolean haveTallDog(List<Animal> list, Integer k) {
        return 0 != list.stream()
            .filter(n -> n.type() == Animal.Type.DOG)
            .filter(n -> n.height() > k)
            .count();
    }

    public Integer summaryWeight(List<Animal> list, Integer k, Integer l) {
        return list.stream()
            .filter(n -> n.age() <= l && n.age() >= k)
            .mapToInt(n -> n.weight())
            .sum();
    }

}
