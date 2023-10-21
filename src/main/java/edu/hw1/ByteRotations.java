package edu.hw1;

public class ByteRotations {

    public ByteRotations() {
    }


    int rotateLeft(int n, int shift) {
        String binary = Integer.toBinaryString(n);
        // старое и новое бинарное зн
        char[] oldBin = binary.toCharArray();
        char[] newBin = new char[binary.length()];
        for (int i = 0; i < binary.length(); i++) {
            int index = (i + shift) % binary.length();
            if (index < 0) {
                index += binary.length();
            }
            newBin[i] = oldBin[index];
        }
        String result = new String(newBin);
        return Integer.parseInt(result, 2);
    }

    int rotateRight(int n, int shift) {
        String binary = Integer.toBinaryString(n);
        char[] oldBin = binary.toCharArray();
        char[] newBin = new char[binary.length()];
        for (int i = 0; i < binary.length(); i++) {
            int index = (i - shift) % binary.length();
            if (index < 0) {
                index += binary.length();
            }
            newBin[i] = oldBin[index];
        }
        String result = new String(newBin);
        return Integer.parseInt(result, 2);
    }
}
