package edu.project3;

import java.util.List;

@SuppressWarnings("MultipleStringliterals")
public class Report {
    private Report() {
    }

    public enum ReportFormat {
        markdown, adoc
    }

    public static String createReport(List<String> tables, ReportFormat format) {
        return switch (format) {
            case adoc -> adocReport(tables);
            case markdown -> markdownReport(tables);
        };
    }

    private static String markdownReport(List<String> tables) {
        String report = "";

        for (String table : tables) {
            String[] rows = table.split(System.lineSeparator());

            report += markdownHeader(rows[0]);
            for (int i = 1; i < rows.length; ++i) {
                report += row(rows[i]);
            }

            report += System.lineSeparator() + System.lineSeparator();
        }

        return report;
    }

    private static String adocReport(List<String> tables) {
        String report = "";

        for (String table : tables) {
            String[] rows = table.split(System.lineSeparator());

            report += adocHeader(rows[0]);
            for (int i = 1; i < rows.length; ++i) {
                report += row(rows[i]);
            }

            report += "|===" + System.lineSeparator() + System.lineSeparator();
        }

        return report;
    }

    public static String markdownHeader(String header) {
        int colnum = header.split(">-<").length;
        return row(header) + "|" + ":-:|".repeat(colnum) + System.lineSeparator();
    }

    public static String adocHeader(String header) {
        int colnum = header.split(">-<").length;
        return "[cols=\"^" + ",^".repeat(colnum - 1) + "\"]" + System.lineSeparator()
            + "|===" + System.lineSeparator()
            + row(header)
            + System.lineSeparator();
    }

    public static String row(String row) {
        String[] cols = row.split(">-<");
        return "|" + String.join("|", cols) + "|" + System.lineSeparator();
    }

    public static String create1x1row(String col1) {
        return col1 + System.lineSeparator();
    }

    public static String create1x2row(String col1, String col2) {
        return col1
            + ">-<"
            + col2
            + System.lineSeparator();
    }

    public static String create1x3row(String col1, String col2, String col3) {
        return col1
            + ">-<"
            + col2
            + ">-<"
            + col3
            + System.lineSeparator();
    }

}
