package edu.hw10.generator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;

public class RandomGeneratedObject {
    private final Generator generator;

    public RandomGeneratedObject() {
        this.generator = Generator.chain(
            new IntGenerator(),
            new StringGenerator()
        );
    }

    public RandomGeneratedObject(Generator generator) {
        this.generator = generator;
    }

    public <T> T nextObject(Class<T> target)
        throws InvocationTargetException,
        InstantiationException,
        IllegalAccessException {
        var constructor = Arrays.stream(target.getDeclaredConstructors())
            .max(Comparator.comparingInt(Constructor::getParameterCount))
            .orElseThrow();

        constructor.setAccessible(true);
        return (T) constructor.newInstance(getArgs(constructor));
    }

    public <T> T nextObject(Class<T> target, String creationMethodName)
        throws InvocationTargetException,
        InstantiationException,
        IllegalAccessException {
        if (creationMethodName == null) {
            return nextObject(target);
        }

        Method createMethod = Arrays.stream(target.getDeclaredMethods())
            .filter(method -> method.getName().equals(creationMethodName))
            .findFirst()
            .orElseThrow();

        createMethod.setAccessible(true);
        return (T) createMethod.invoke(null, getArgs(createMethod));
    }

    private Object[] getArgs(Executable method) {
        Parameter[] parameters = method.getParameters();

        Object[] args = new Object[parameters.length];
        for (int i = 0; i < parameters.length; ++i) {
            args[i] = generator.generate(parameters[i]);
        }

        return args;
    }
}
