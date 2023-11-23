package edu.hw6;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

class PortCheckTest {
    @Test
    void testScanPorts() {
        assertThatCode(PortCheck::pars).doesNotThrowAnyException();
    }

}
