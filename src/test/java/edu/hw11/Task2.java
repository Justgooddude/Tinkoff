package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class Task2 {
    public static class ClassicalSum {
        public static int sum(int a, int b) {
            return a + b;
        }
    }

    public static class CutsedSum {
        public static int sum(int a, int b) {
            return a * b;
        }
    }
    @Test
    public void redefineMethod() throws Exception {
        ByteBuddyAgent.install();
        var sum = new ClassicalSum();

        try {
            new ByteBuddy()
                .redefine(CutsedSum.class)
                .name(ClassicalSum.class.getName())
                .make()
                .load(ClassicalSum.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

            assertThat(sum.sum(2, 1)).isEqualTo(2);
        } finally {
            ClassReloadingStrategy.fromInstalledAgent().reset(ClassicalSum.class);
        }

        assertThat(sum.sum(2, 1)).isEqualTo(3);
    }
}
