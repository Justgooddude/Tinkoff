package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Checksum;

public class PrinterFile {
    public void print(Path path) {
        try {
            OutputStream outs = Files.newOutputStream(path);
            CheckedOutputStream chos = new CheckedOutputStream(outs, new CRC32());
            BufferedOutputStream bufos = new BufferedOutputStream(chos);
            OutputStreamWriter osw = new OutputStreamWriter(bufos, StandardCharsets.UTF_8);
            PrintWriter pw = new PrintWriter(osw);
            pw.print("Programming is learned by writting programs. - Brian Kernigan");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
