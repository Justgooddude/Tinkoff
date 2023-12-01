package edu.project1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void gamerun() {
        Game game = new Game();
        Game game1 = new Game();
        boolean result1 = game.gamerun("test", "tes".toCharArray());
        boolean result2 = game1.gamerun("test", "abcxzyr".toCharArray());

        /*Извиняюсь не совсем пониаю как писать тесты для методом с вводом с консоли*/
        assertThat(result1).isTrue();
        assertThat(result2).isFalse();

    }

}
