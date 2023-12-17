package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1 {
    @Test
    void helloByteBuddy() throws Exception {
        String string = "Hello, ByteBuddy!";

        String result = new ByteBuddy()
            .subclass(Object.class)
            .name("example.Type")
            .method(named("toString")).intercept(FixedValue.value(string))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded()
            .newInstance()
            .toString();
        assertThat(result).isEqualTo(string);
    }
}
