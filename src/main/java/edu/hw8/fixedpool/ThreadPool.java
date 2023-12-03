package edu.hw8.fixedpool;

public interface ThreadPool extends AutoCloseable {
    void start();

    void execute(Runnable runnable);
}
