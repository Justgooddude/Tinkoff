package edu.hw4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.*;

class Tasks610Test {
    List<Animal> list = new ArrayList<>();

    @Test
    void heavistFromType() {
        Tasks610 tasks = new Tasks610();
        Animal f = new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 10, 20, 50, true);
        Animal b = new Animal("Betty", Animal.Type.CAT, Animal.Sex.F, 10, 40, 5, true);
        Animal a = new Animal("Ant", Animal.Type.DOG, Animal.Sex.M, 10, 30, 20, true);
        Animal c = new Animal("Be", Animal.Type.CAT, Animal.Sex.F, 10, 40, 10, true);
        list.add(c);
        list.add(f);
        list.add(b);
        list.add(a);
        Map<Animal.Type,Animal> test=new TreeMap<>();
        test.put(Animal.Type.DOG,f);
        test.put(Animal.Type.CAT,c);
        assertEquals(tasks.heavistFromType(list),test);
    }

    @Test
    void kthEldest() {
        Tasks610 tasks = new Tasks610();
        Animal f = new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 15, 20, 50, true);
        Animal b = new Animal("Betty", Animal.Type.CAT, Animal.Sex.F, 20, 40, 5, true);
        Animal a = new Animal("Ant", Animal.Type.DOG, Animal.Sex.M, 16, 30, 20, true);
        Animal c = new Animal("Be", Animal.Type.CAT, Animal.Sex.F, 10, 40, 10, true);
        list.add(c);
        list.add(f);
        list.add(b);
        list.add(a);
        int k =2;
        assertEquals(tasks.kthEldest(list,k),a);
    }

    @Test
    void heviestUnderKcm() {
        Tasks610 tasks = new Tasks610();
        Animal f = new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 15, 20, 50, true);
        Animal b = new Animal("Betty", Animal.Type.CAT, Animal.Sex.F, 20, 40, 5, true);
        Animal a = new Animal("Ant", Animal.Type.DOG, Animal.Sex.M, 16, 30, 20, true);
        Animal c = new Animal("Be", Animal.Type.CAT, Animal.Sex.F, 10, 40, 10, true);
        list.add(c);
        list.add(f);
        list.add(b);
        list.add(a);
        int k =30;
        assertEquals(tasks.heviestUnderKcm(list,k), Optional.of(f));
    }

    @Test
    void pawnSum() {
        Tasks610 tasks = new Tasks610();
        Animal f = new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 15, 20, 50, true);
        Animal b = new Animal("Betty", Animal.Type.CAT, Animal.Sex.F, 20, 40, 5, true);
        Animal a = new Animal("Ant", Animal.Type.DOG, Animal.Sex.M, 16, 30, 20, true);
        Animal c = new Animal("Be", Animal.Type.FISH, Animal.Sex.F, 10, 40, 10, true);
        list.add(c);
        list.add(f);
        list.add(b);
        list.add(a);
        assertEquals(tasks.pawnSum(list), 12);
    }

    @Test
    void pawnsNotAge() {
        Tasks610 tasks = new Tasks610();
        Animal f = new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 15, 20, 50, true);
        Animal b = new Animal("Betty", Animal.Type.CAT, Animal.Sex.F, 20, 40, 5, true);
        Animal a = new Animal("Ant", Animal.Type.DOG, Animal.Sex.M, 16, 30, 20, true);
        Animal c = new Animal("Be", Animal.Type.CAT, Animal.Sex.F, 10, 40, 10, true);
        list.add(c);
        list.add(f);
        list.add(b);
        list.add(a);
        assertEquals(tasks.pawnsNotAge(list),list);
    }
}
