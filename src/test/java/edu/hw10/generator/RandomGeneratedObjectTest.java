package edu.hw10.generator;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RandomGeneratedObjectTest {
    RandomGeneratedObject rgo = new RandomGeneratedObject();

    @Test
    void nextObject()  throws InvocationTargetException,
        InstantiationException,
        IllegalAccessException {
        var myClass = rgo.nextObject(Student.class, "create");

        assertThat(myClass.getgroup()).isBetween(21001, 21999);
        assertThat(myClass.getName()).isNotNull();
    }

    @Test
    void testNextObject()   throws InvocationTargetException,
        InstantiationException,
        IllegalAccessException {
        var myRecord = rgo.nextObject(RecStudent.class);

        assertThat(myRecord.group()).isBetween(21001, 21999);
        assertThat(myRecord.name()).isNotNull();
    }
}
}
