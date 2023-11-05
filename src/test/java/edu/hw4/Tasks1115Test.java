package edu.hw4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import static edu.hw4.Animal.Type.DOG;
import static edu.hw4.Animal.Type.SPIDER;
import static org.junit.jupiter.api.Assertions.*;

class Tasks1115Test {
    List<Animal> list = new ArrayList<>();

    @Test
    void bigBiter() {
        Tasks1115 tasks = new Tasks1115();
        Animal f = new Animal("Jack", DOG, Animal.Sex.M, 15, 20, 50, false);
        Animal b = new Animal("Betty", SPIDER, Animal.Sex.F, 20, 40, 5, true);
        Animal a = new Animal("Ant", DOG, Animal.Sex.M, 16, 30, 20, false);
        Animal c = new Animal("Be", SPIDER, Animal.Sex.F, 10, 40, 10, false);
        list.add(c);
        list.add(f);
        list.add(b);
        list.add(a);
        List<Animal> test=new ArrayList<>();
        assertEquals(tasks.bigBiter(list),test);
    }

    @Test
    void heavierThanTall() {
        Tasks1115 tasks = new Tasks1115();
        Animal f = new Animal("Jack", DOG, Animal.Sex.M, 15, 20, 50, false);
        Animal b = new Animal("Betty", SPIDER, Animal.Sex.F, 20, 40, 5, true);
        Animal a = new Animal("Ant", DOG, Animal.Sex.M, 16, 30, 20, false);
        Animal c = new Animal("Be", SPIDER, Animal.Sex.F, 10, 40, 10, false);
        list.add(c);
        list.add(f);
        list.add(b);
        list.add(a);
        assertEquals(tasks.heavierThanTall(list),1);
    }

    @Test
    void twoPartNames() {
        Tasks1115 tasks = new Tasks1115();
        Animal f = new Animal("Jack", DOG, Animal.Sex.M, 15, 20, 50, false);
        Animal b = new Animal("Be tty", SPIDER, Animal.Sex.F, 20, 40, 5, true);
        Animal a = new Animal("Ant", DOG, Animal.Sex.M, 16, 30, 20, false);
        Animal c = new Animal("Be", SPIDER, Animal.Sex.F, 10, 40, 10, false);
        list.add(c);
        list.add(f);
        list.add(b);
        list.add(a);
        List<Animal> test=new ArrayList<>();
        test.add(b);
        assertEquals(tasks.twoPartNames(list),test);
    }

    @Test
    void haveTallDog() {
        Tasks1115 tasks = new Tasks1115();
        Animal f = new Animal("Jack", DOG, Animal.Sex.M, 15, 20, 50, false);
        Animal b = new Animal("Be tty", SPIDER, Animal.Sex.F, 20, 40, 5, true);
        Animal a = new Animal("Ant", DOG, Animal.Sex.M, 16, 30, 20, false);
        Animal c = new Animal("Be", SPIDER, Animal.Sex.F, 10, 40, 10, false);
        list.add(c);
        list.add(f);
        list.add(b);
        list.add(a);
        int k =30;
        assertEquals(tasks.haveTallDog(list,k),false);
    }

    @Test
    void summaryWeight() {
        Tasks1115 tasks = new Tasks1115();
        Animal f = new Animal("Jack", DOG, Animal.Sex.M, 15, 20, 50, false);
        Animal b = new Animal("Be tty", SPIDER, Animal.Sex.F, 20, 40, 5, true);
        Animal a = new Animal("Ant", DOG, Animal.Sex.M, 16, 30, 20, false);
        Animal c = new Animal("Be", SPIDER, Animal.Sex.F, 10, 40, 10, false);
        list.add(c);
        list.add(f);
        list.add(b);
        list.add(a);
        int k=15;
        int l=20;
        assertEquals(tasks.summaryWeight(list,k,l),75);
    }
}
