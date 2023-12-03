package edu.hw8.password;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PasswordBrutTest {
    private static final Map<String, String> MD5HASH_LOGIN_DATABASE = Map.of(
        "d93591bdf7860e1e4ee2fca799911215", "1234",
        "06d80eb0c50b49a509b49f2424e8c805", "cat",
        "d14338942d148b6a846f53d9fb5d327d", "film"
    );
    private static final String ALLCHARS = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int MAX_PASSWORD_LENGTH = 4;
    private static final int THREADS_NUMBER = 4;

    private PasswordBrut passwordMiner;

    @Test
    void singleThread() {
        passwordMiner = new PasswordBrut(
            MD5HASH_LOGIN_DATABASE,
            ALLCHARS,
            MAX_PASSWORD_LENGTH
        );
        passwordMiner.singleThread();
        ConcurrentHashMap<String, String> result = passwordMiner.getLoginPasswordDatabase();
        assertThat(result).contains(
            entry("1234", "4321"),
            entry("cat", "dog"),
            entry("film", "lotr")
        );
    }

    @Test
    void multiThread() {
        passwordMiner = new PasswordBrut(
            MD5HASH_LOGIN_DATABASE,
            ALLCHARS,
            MAX_PASSWORD_LENGTH
        );
        var executorService = Executors.newFixedThreadPool(THREADS_NUMBER);
        passwordMiner.multiThread(
            executorService,
            THREADS_NUMBER
        );
        ConcurrentHashMap<String, String> result = passwordMiner.getLoginPasswordDatabase();
        assertThat(result).contains(
            entry("1234", "4321"),
            entry("cat", "dog"),
            entry("film", "lotr")
        );
    }
}
