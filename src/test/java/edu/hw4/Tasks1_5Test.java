package edu.hw4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.*;

class Tasks1_5Test {
    List<Animal> list = new ArrayList<>();

    @Test
    void heigtSort() {
        Tasks15 tasks = new Tasks15();
        Animal f = new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 10, 20, 30, true);
        Animal b = new Animal("Betty", Animal.Type.CAT, Animal.Sex.F, 10, 40, 30, true);
        Animal a = new Animal("Ant", Animal.Type.FISH, Animal.Sex.M, 10, 30, 30, true);
        list.add(f);
        list.add(b);
        list.add(a);
        List<Animal> test = List.of(f, a, b);
        List<Animal> result = tasks.heigtSort(list);
        assertEquals(result, test);

    }

    @Test
    void weigtSort() {
        Tasks15 tasks = new Tasks15();
        Animal f = new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 10, 20, 50, true);
        Animal b = new Animal("Betty", Animal.Type.CAT, Animal.Sex.F, 10, 40, 10, true);
        Animal a = new Animal("Ant", Animal.Type.FISH, Animal.Sex.M, 10, 30, 20, true);
        list.add(f);
        list.add(b);
        list.add(a);
        List<Animal> test = List.of(b, a);
        List<Animal> result = tasks.weigtSort(list, 2);
        assertEquals(result, test);
    }

    @Test
    void typeCounter() {
        Tasks15 tasks = new Tasks15();
        Animal f = new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 10, 20, 50, true);
        Animal b = new Animal("Betty", Animal.Type.CAT, Animal.Sex.F, 10, 40, 10, true);
        Animal a = new Animal("Ant", Animal.Type.FISH, Animal.Sex.M, 10, 30, 20, true);
        Animal c = new Animal("Be", Animal.Type.CAT, Animal.Sex.F, 10, 40, 10, true);
        list.add(c);
        list.add(f);
        list.add(b);
        list.add(a);
        Map<Animal.Type, Integer> test = new TreeMap<>();
        test.put(Animal.Type.CAT, 2);
        test.put(Animal.Type.DOG, 1);
        test.put(Animal.Type.FISH, 1);
        assertEquals(tasks.typeCounter(list), test);

    }

    @Test
    void longestName() {
        Tasks15 tasks = new Tasks15();
        Animal f = new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 10, 20, 50, true);
        Animal b = new Animal("Betty", Animal.Type.CAT, Animal.Sex.F, 10, 40, 10, true);
        Animal a = new Animal("Ant", Animal.Type.FISH, Animal.Sex.M, 10, 30, 20, true);
        Animal c = new Animal("Be", Animal.Type.CAT, Animal.Sex.F, 10, 40, 10, true);
        list.add(c);
        list.add(f);
        list.add(b);
        list.add(a);
        assertEquals(tasks.longestName(list), b);
    }

    @Test
    void biggerSex() {
        Tasks15 tasks = new Tasks15();
        Animal f = new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 10, 20, 50, true);
        Animal b = new Animal("Betty", Animal.Type.CAT, Animal.Sex.M, 10, 40, 10, true);
        Animal a = new Animal("Ant", Animal.Type.FISH, Animal.Sex.M, 10, 30, 20, true);
        Animal c = new Animal("Be", Animal.Type.CAT, Animal.Sex.F, 10, 40, 10, true);
        list.add(c);
        list.add(f);
        list.add(b);
        list.add(a);
        assertEquals(tasks.biggerSex(list), Animal.Sex.M);
    }
}
