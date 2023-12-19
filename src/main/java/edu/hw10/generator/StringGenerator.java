package edu.hw10.generator;

import java.lang.reflect.Parameter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StringGenerator extends Generator {
    private static final List<String> DICT = List.of(
        "",
        "Hello",
        "World",
        "ByeBudd",
        "kittens",
        "puppys"
    );

    @Override
    public Object generate(Parameter parameter) {
        if (!parameter.getType().equals(String.class)) {
            return generateNext(parameter);
        }
        for (var annotation : parameter.getAnnotations()) {
            if (annotation instanceof NotNull) {
                return "Sth" + DICT.get(ThreadLocalRandom.current().nextInt(DICT.size()));
            }
        }

        boolean flag = ThreadLocalRandom.current().nextBoolean();
        if (flag) {
            return null;
        }
        return DICT.get(ThreadLocalRandom.current().nextInt(DICT.size()));
    }
}
