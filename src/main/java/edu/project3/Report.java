package edu.project3;

import java.util.List;

public class Report {
    public enum ReportFormat {
        markdown, adoc
    }

    public String createReport(List<String> tables, ReportFormat format) {
        return switch (format) {
            case adoc ->     adocReport(tables);
            case markdown -> markdownReport(tables);
        };
    }

    private String markdownReport(List<String> tables) {
      String report="";

        for (String table : tables) {
            String[] rows = table.split(System.lineSeparator());

            report+=markdownHeader(rows[0]);
            for (int i = 1; i < rows.length; ++i) {
                report+=row(rows[i]);
            }

            report+=System.lineSeparator()+System.lineSeparator();
        }

        return report;
    }

    private  String adocReport(List<String> tables) {
        String report="";

        for (String table : tables) {
            String[] rows = table.split(System.lineSeparator());

            report+=adocHeader(rows[0]);
            for (int i = 1; i < rows.length; ++i) {
                report+=row(rows[i]);
            }

            report+="|==="+System.lineSeparator()+System.lineSeparator();
        }

        return report;
    }

    public String markdownHeader(String header){
        int colnum=header.split(">-<").length;
        return row(header) + "|" + ":-:|".repeat(colnum) + System.lineSeparator();
    }

    public String adocHeader(String header){
        int colnum = header.split(">-<").length;
        return "[cols=\"^" + ",^".repeat(colnum - 1) + "\"]" + System.lineSeparator()
            + "|===" + System.lineSeparator()
            + row(header)
            + System.lineSeparator();
    }

    public String row(String row){
        String[] cols = row.split(">-<");
        return "|" + String.join("|", cols) + "|" + System.lineSeparator();
    }

}
