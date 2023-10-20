package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PalindromeTest {
    @Test
    void isPalindromeDescendant() {
        Palindrome problem = new Palindrome();
        int example = 13001120;
        boolean result = problem.isPalindromeDescendant(example);
        Assertions.assertThat(result).isEqualTo(true);
    }

    @Test
    void isPalindromeDescendant2() {
        Palindrome problem = new Palindrome();
        int example = 543;
        boolean result = problem.isPalindromeDescendant(example);
        Assertions.assertThat(result).isEqualTo(false);
    }

}
