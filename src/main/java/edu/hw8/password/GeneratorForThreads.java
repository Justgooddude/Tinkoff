package edu.hw8.password;

import java.util.NoSuchElementException;

public class GeneratorForThreads {
    //От стаднадртного будет отличаться лишь 2мя новыми параметрами-границами генерации,чтобы разные
    // потоки генерировали собствееные куски пароля
    char[] allPosibleChars;
    int[] positionCharIndex;
    int partEnd;

    public GeneratorForThreads(char[] allPosibleChars, int partStart, int partEnd, int passwordLength) {
        this.allPosibleChars = allPosibleChars;
        this.positionCharIndex = new int[passwordLength];
        positionCharIndex[0] = partStart;
        this.partEnd = partEnd;
    }

    public String generatePassword() {
        if (positionCharIndex[0] == partEnd + 1) {
            throw new NoSuchElementException("");
        }
        StringBuilder pas = new StringBuilder();
        for (int index : positionCharIndex) {
            pas.append(allPosibleChars[index]);
        }
        generateNextPassword();
        return new String(pas);
    }

    private void generateNextPassword() {
        for (int i = positionCharIndex.length - 1; i >= 0; --i) {
            positionCharIndex[i]++;

            if (positionCharIndex[i] == allPosibleChars.length) {
                if (i != 0) {
                    positionCharIndex[i] = 0;
                }
            } else {
                break;
            }
        }
    }
}
