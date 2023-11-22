package edu.project3;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LogAnalyzerTest {

    @Test
    void testMarkdownReport() {
        String[] args = {
            "--path", "test"
        };
        String expected;
        try {
            expected = Files.readString(Paths.get("src/test/java/edu/project3/markdownTest"));
        } catch (IOException ignored) {
            expected = "";
        }

        // Act
        LogAnalyzer logAnalyzer = new LogAnalyzer(args);
        String result = logAnalyzer.fullyAnalyze();

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testAdocReport() {
        // Arrange
        String[] args = {
            "--path", "test",
            "--format", "adoc"
        };
        String expected;
        try {
            expected = Files.readString(Paths.get("src/test/java/edu/project3/adocTest"));
        } catch (IOException ignored) {
            expected = "";
        }

        // Act
        LogAnalyzer logAnalyzer = new LogAnalyzer(args);
        String result = logAnalyzer.fullyAnalyze();

        // Assert
        assertThat(result).isEqualTo(expected);
    }

}

