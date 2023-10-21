package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Video {
    private final static Logger LOGGER = LogManager.getLogger();

    public Video() {
    }

    @SuppressWarnings("MagicNumber")
    public int length(String time) {

        final int secInMin = 60;
        // ошибка при некоректном вводе-отсутствует разделение минут  секунд
        if (!time.contains(":")) {
            return -1;
        }
        //выделяем строку минут
        String minutes = time.substring(0, time.indexOf(":"));
        //выделяем подстроку секунд
        String seconds = time.substring(time.indexOf(":") + 1);
        //переводим из строк в целые
        int sec = Integer.parseInt(seconds);
        int min = Integer.parseInt(minutes);
        if (sec >= secInMin) {
            return -1;
        }
        return min * secInMin + sec;
    }
    //задание 0

    public void sayHello() {
        LOGGER.info("Hello, world!");
    }
}
