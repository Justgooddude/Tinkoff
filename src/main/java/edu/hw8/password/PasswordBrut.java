package edu.hw8.password;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public class PasswordBrut {
    private final Map<String, String> md5HashLoginDatabase;
    private final String allchars;
    private final int maxPasswordLength;
    private final ConcurrentHashMap<String, String> loginPasswordDatabase;

    public PasswordBrut(Map<String, String> md5hashLoginDatabase, String allchars, int maxPasswordLength) {
        this.md5HashLoginDatabase = md5hashLoginDatabase;
        this.allchars = allchars;
        this.maxPasswordLength = maxPasswordLength;
        this.loginPasswordDatabase = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<String, String> getLoginPasswordDatabase() {
        return loginPasswordDatabase;
    }
    public void singleThread() {
        var generator = new SimplePasswordGenerator(
            allchars.toCharArray(),
            maxPasswordLength
        );

        for (int i = 0; i < maxPasswordLength; ++i) {
            while (generator.totalAmmountOfPasswords!=0) {
                String password = generator.generatePassword();
                String hash = md5Hash(password);

                if (md5HashLoginDatabase.containsKey(hash)) {
                    String login = md5HashLoginDatabase.get(hash);
                    loginPasswordDatabase.put(login, password);
                }
            }

            generator = new SimplePasswordGenerator(
                allchars.toCharArray(),
                maxPasswordLength - i - 1
            );
        }
    }

    public void multiThread(ExecutorService executorService, int threadNumber) {
        var latch = new CountDownLatch(threadNumber);

        int part = (int) Math.floor((double) allchars.length() / threadNumber);
        for (int i = 0; i < threadNumber; ++i) {
            int from = part * i;
            int to = (i == threadNumber - 1)
                ? allchars.length() - 1
                : part * (i + 1) - 1;

            executorService.submit(() -> {
                try {
                    passwordGeneratorThread(from, to);
                } finally {
                    latch.countDown();
                }
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    private void passwordGeneratorThread(int start, int end) {
        var generator = new GeneratorForThreads(
            allchars.toCharArray(),
            start, end,
            maxPasswordLength
        );

        for (int i = 0; i < maxPasswordLength; ++i) {
            while (generator.positionCharIndex[0]!= end+1) {
                String password = generator.generatePassword();
                String hash = md5Hash(password);

                if (md5HashLoginDatabase.containsKey(hash)) {
                    String login = md5HashLoginDatabase.get(hash);
                    loginPasswordDatabase.put(login, password);
                }
            }

            generator = new GeneratorForThreads(
                allchars.toCharArray(),
                start, end,
                maxPasswordLength - i - 1
            );
        }
    }

    private static String md5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
