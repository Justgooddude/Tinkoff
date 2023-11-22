package edu.project3;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("MultipleStringLiterals")
public class NginxLogParser {
    private String regPatern =
        "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(
        "dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH
    );
    private Pattern logPattern = Pattern.compile(
        "(?<remoteAddress>" + regPatern + ")" + " - "
            + "(?<remoteUser>.*)" + " "
            + "\\[" + "(?<timeLocal>.*)" + "]" + " "
            + "\\\"" + "(?<request>.*)" + "\\\"" + " "
            + "(?<status>\\d+)" + " "
            + "(?<bodyBytesSent>\\d+)" + " "
            + "\\\"" + "(?<httpReferer>.*)" + "\\\"" + " "
            + "\\\"" + "(?<httpUserAgent>.*)" + "\\\""
    );

    @SuppressWarnings("MagicNumber")
    public List<NginxBody> parse(List<String> logs) {
        List<NginxBody> result = new ArrayList<>();
        for (String log : logs) {
            Matcher matcher = logPattern.matcher(log);
            if (matcher.find()) {
                try {
                    NginxBody formatedlog = new NginxBody(
                        InetAddress.getByName(matcher.group(1)),
                        matcher.group(2),
                        OffsetDateTime.parse(matcher.group(3), dateFormat),
                        matcher.group(4),
                        Integer.parseInt(matcher.group(5)),
                        Long.parseLong(matcher.group(6)),
                        URI.create(matcher.group(7)),
                        matcher.group(8)
                    );
                    result.add(formatedlog);

                } catch (UnknownHostException e) {

                }
            }
        }
        return result;
    }

    public List<NginxBody> parseLogFiles(List<Path> paths) {
        List<String> allLogLines = readPath(paths);
        return parse(allLogLines);
    }

    private List<String> readPath(List<Path> paths) {
        List<String> result = new ArrayList<>();

        for (var path : paths) {
            try {
                result.addAll(Files.readAllLines(path));
            } catch (IOException ignored) {
            }
        }

        return result;
    }

}
