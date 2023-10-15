package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VideoTest {

    @Test
    void lengthCor() {
        Video video = new Video();
        String time = "13:56";
        int seconds = video.length(time);
        Assertions.assertThat(seconds).isEqualTo(836);

    }

    @Test
    void lengthFalse() {
        Video video = new Video();
        String time = "13:60";
        int seconds = video.length(time);
        Assertions.assertThat(seconds).isEqualTo(-1);

    }
}
