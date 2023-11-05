package edu.hw4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static edu.hw4.Animal.Type.DOG;
import static edu.hw4.Animal.Type.FISH;
import static edu.hw4.Animal.Type.SPIDER;
import static org.junit.jupiter.api.Assertions.*;

class Tasks1620Test {
    List<Animal> list = new ArrayList<>();

    @Test
    void complexSort() {
        Tasks1620 tasks = new Tasks1620();
        Animal f = new Animal("Jack", DOG, Animal.Sex.M, 15, 20, 50, false);
        Animal b = new Animal("Betty", SPIDER, Animal.Sex.M, 20, 40, 5, true);
        Animal a = new Animal("Ant", DOG, Animal.Sex.M, 16, 30, 20, false);
        Animal c = new Animal("Be", SPIDER, Animal.Sex.F, 10, 40, 10, false);
        list.add(c);
        list.add(f);
        list.add(b);
        list.add(a);
        List<Animal> test=new ArrayList<>();
        test.add(a);
        test.add(f);
        test.add(b);
        test.add(c);
        assertEquals(tasks.complexSort(list),test);
    }

    @Test
    void spiderAngry() {
        Tasks1620 tasks = new Tasks1620();
        Animal f = new Animal("Jack", DOG, Animal.Sex.M, 15, 20, 50, false);
        Animal b = new Animal("Betty", SPIDER, Animal.Sex.M, 20, 40, 5, true);
        Animal a = new Animal("Ant", DOG, Animal.Sex.M, 16, 30, 20, false);
        Animal c = new Animal("Be", SPIDER, Animal.Sex.F, 10, 40, 10, false);
        list.add(c);
        list.add(f);
        list.add(b);
        list.add(a);
        assertEquals(tasks.spiderAngry(list),true);
    }

    @Test
    void heaviestFish() {
        Tasks1620 tasks = new Tasks1620();
        Animal f = new Animal("Jack", FISH, Animal.Sex.M, 15, 20, 5, false);
        Animal b = new Animal("Betty", SPIDER, Animal.Sex.M, 20, 40, 100, true);
        Animal a = new Animal("Ant", FISH, Animal.Sex.M, 16, 30, 20, false);
        Animal c = new Animal("Be", SPIDER, Animal.Sex.F, 10, 40, 10, false);
        List<List<Animal>> test= new ArrayList<>();
        test.add(List.of(f,b));
        test.add(List.of(a));
        test.add(List.of(c));
        assertEquals(tasks.heaviestFish(test),a);

    }
}
