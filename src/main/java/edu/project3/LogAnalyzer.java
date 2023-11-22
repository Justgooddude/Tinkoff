package edu.project3;

import edu.project3.analizators.AmPm;
import edu.project3.analizators.AverageSreverReport;
import edu.project3.analizators.HttpStatus;
import edu.project3.analizators.Request;
import edu.project3.analizators.Resourses;
import edu.project3.analizators.ResponseFreq;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import static edu.project3.Report.create1x1row;
import static edu.project3.Report.create1x2row;
import static edu.project3.Report.create1x3row;
import static edu.project3.Report.createReport;
import static java.util.stream.Collectors.toList;

public class LogAnalyzer {
    public ComandLineAn configs;
    public List<Path> allLogFiles;
    public List<NginxBody> allLogs;

    public LogAnalyzer(String[] args){
        configs = new ComandLineAn();
        CommandLine commandLine = new CommandLine(configs);
        commandLine.parse(args);
        allLogFiles=new ArrayList<>();
        List<String>rawPaths = configs.paths;
        NginxLogParser parser= new NginxLogParser();
        for (String rawpath:rawPaths){
            try{
                allLogFiles.add(Path.of(new URI(rawpath).toURL().getFile()));
                var  filesLogs=( parser.parse(
                    readAllLinesFrom(new URI(rawpath).toURL())
                ));
                for (var log:filesLogs){
                    allLogs.add(log);
                }
            } catch (URISyntaxException | IllegalArgumentException | MalformedURLException ignored){
                String pattern="glob:**/" + rawpath;
                PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(pattern);
                var rootFiles=findLogFilesInRootDirectory(pathMatcher);
                allLogFiles.addAll(rootFiles);
                for (Path file:rootFiles){
                   allLogs.addAll(parser.parseLogFiles(List.of(file)));
                }
            }
        }
        allLogs = allLogs.stream()
            .filter(t->inDateRange(t))
            .toList();
    }

    public String fullyAnalyze() {
        List<String> tables = new ArrayList<>();

        tables.add(getFileNamesNx1Table());
        tables.add(getMetricValue5x2Table());
        tables.add(getResourceCount6x2Table());
        tables.add(getStatusNameCount6x3Table());
        tables.add(getCategoryCountPercent6x3Table());
        tables.add(getTimeOfDayPercent3x3Table());

        Report.ReportFormat format = configs.format;
        return format != null
            ? createReport(tables, format)
            : createReport(tables, Report.ReportFormat.markdown);
    }


    public List<String> readAllLinesFrom(URL url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                List<String> lines = new ArrayList<>();

                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }

                return lines;
            }
        } catch (IOException ignored) {
            return null;
        }
    }
    public static List<Path> findLogFilesInRootDirectory(PathMatcher pathMatcher) {
        try (var stream = Files.walk(Path.of("src/main/java/edu/project3/"))) {
            return stream
                .filter(pathMatcher::matches)
                .toList();
        } catch (IOException ignored) {
            return List.of();
        }
    }
    private boolean inDateRange(NginxBody item) {
        OffsetDateTime timeLocal = item.timeLocal();
        OffsetDateTime from = configs.from;
        OffsetDateTime to   = configs.to;

        return (from == null || timeLocal.isAfter(from)) && (to == null || timeLocal.isBefore(to));
    }

    private String getFileNamesNx1Table() {
        return create1x1row("File name") + getFileNames();
    }

    private String getFileNames() {
        String builder="";

        for (var path : allLogFiles) {
            builder+=create1x1row(path.getFileName().toString());
        }

        return builder;
    }

    private String getMetricValue5x2Table() {
       String info="";

        String from = configs.from != null ? configs.from.toString() : "-";
        String to   = configs.to != null ? configs.to.toString() : "-";

        info+=create1x2row("Metric", "Value")
            +create1x2row("From date", from)
            +create1x2row("To date", to)
            + Request.analyze(allLogs)
            + AverageSreverReport.analyze(allLogs);

        return info.toString();
    }

    private String getResourceCount6x2Table() {
        return create1x2row("Resource", "Count")
            + Resourses.analyze(allLogs);
    }

    private String getStatusNameCount6x3Table() {
        return create1x3row("Status", "Name", "Count")
            + ResponseFreq.analyze(allLogs);
    }

    private String getCategoryCountPercent6x3Table() {
        return create1x3row("Category", "Count", "Percent")
            + HttpStatus.analyze(allLogs);
    }

    private String getTimeOfDayPercent3x3Table() {
        return create1x3row("Time of day", "Count", "Percent")
            + AmPm.analyze(allLogs);
    }

}
