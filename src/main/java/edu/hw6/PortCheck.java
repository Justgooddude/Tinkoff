package edu.hw6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;

@SuppressWarnings({"MagicNumber", "RegexpSinglelineJava"})
public class PortCheck {
    private final static String FORMAT = "%-9s %-5d %-16s %-35s\n";
    private final static Map<Integer, String> PORTS = Map.of(
        137, "Служба имен NetBIOS",
        139, "Служба сеансов NetBIOS",
        147, "ISO-IP",
        1433, "Microsoft SQL Server — Server",
        843, "Adobe Flash",
        1900, "SSDP",
        3702, "Динамическое обнаружение веб-служб",
        5353, "Многоадресный DNS",
        17500, "Dropbox"
    );

    public enum Protocol {
        TCP, UDP
    }

    public PortCheck() {

    }

    public static void pars() {
        for (int port = 0; port <= 49151; ++port) {
            String name = PORTS.getOrDefault(port, "");
            if (tcpPortClosed(port)) {
                System.out.printf(FORMAT, Protocol.TCP, port, "OPEN", name);
            } else {
                System.out.printf(FORMAT, Protocol.TCP, port, "CLOSED", name);
            }
            if (udpPortClosed(port)) {
                System.out.printf(FORMAT, Protocol.UDP, port, "Open", name);
            } else {
                System.out.printf(FORMAT, Protocol.UDP, port, "Closed", name);
            }
        }
    }

    private static boolean tcpPortClosed(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static boolean udpPortClosed(int port) {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
