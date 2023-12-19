package edu.hw10.generator;

import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class IntGenerator extends Generator {
    @Override
    public Object generate(Parameter parameter) {
        if (!(parameter.getType().equals(int.class) || parameter.getType().equals(Integer.class))) {
            return generateNext(parameter);
        }
        var minValue = Integer.MIN_VALUE;
        var maxValue = Integer.MAX_VALUE;
        for (var annotation : parameter.getAnnotations()) {
            switch (annotation) {
                case Min minannotation -> minValue = minannotation.value();
                case Max maxannotation -> maxValue = maxannotation.value();
                default -> {
                }
            }
        }

        return ThreadLocalRandom.current().nextInt(minValue, maxValue);
    }
}
