package edu.hw8.fixedpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {
    BlockingQueue<Runnable> tasks;
    private final int threadsNumber;
    private final Thread[] threads;
    private volatile boolean isShutDown;

    private FixedThreadPool(int threadsNumber) {
        this.threadsNumber = threadsNumber;
        this.threads = new Thread[threadsNumber];
        this.isShutDown = false;
        this.tasks = new LinkedBlockingQueue<>();
    }

    public static FixedThreadPool getFixedPool(int threadsNumber) {
        return new FixedThreadPool(threadsNumber);
    }

    @Override
    public void start() {
        for (int i = 0; i < threadsNumber; i++) {
            threads[i] = new Thread(new TasksWalker());
            threads[i].start();
        }
    }

    @Override
    public void close() {
        isShutDown = true;
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    @Override
    public void execute(Runnable newTask) {
        if (isShutDown) {
            throw new IllegalStateException();
        }

        try {
            tasks.put(newTask);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private class TasksWalker implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Runnable task = tasks.take();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

}
