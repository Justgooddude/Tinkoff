package edu.hw6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class PortCheck {
    public void pars() {
        List<Integer> closedportTC = new ArrayList<>();
        for (int i = 1; i <= 49151; i++) {
            try (ServerSocket server = new ServerSocket(i)) {
            } catch (IOException e) {
                closedportTC.add(i);
            }
            try (DatagramSocket server = new DatagramSocket(i)) {
            } catch (IOException e) {
                closedportTC.add(i);
            }
        }
    }
}
