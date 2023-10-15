package edu.hw1;

import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Video {
    private final static Logger LOGGER = LogManager.getLogger();

    public Video() {
    }

    public int Length(String time) {
        if (!time.contains(":")) {
            return -1;
        }
        String minutes = time.substring(0, time.indexOf(":"));
        String seconds = time.substring(time.indexOf(":") + 1);
        int sec = Integer.parseInt(seconds);
        int min = Integer.parseInt(minutes);
        if (sec >= 60) {
            return -1;
        }
        return min * 60 + sec;
    }

    public void SayHello() {
        LOGGER.info("Hello, world!");
    }
}
