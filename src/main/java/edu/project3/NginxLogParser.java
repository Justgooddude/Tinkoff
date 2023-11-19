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

public class NginxLogParser{
  private String regPatern =
      "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(
        "dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH
    );
  private Pattern logPattern= Pattern.compile(
      "(?<remoteAddress>" + regPatern + ")" + " - "
          + "(?<remoteUser>.*)" + " "
          + "\\[" + "(?<timeLocal>.*)" + "]" + " "
          + "\\\"" + "(?<request>.*)" + "\\\"" + " "
          + "(?<status>\\d+)" + " "
          + "(?<bodyBytesSent>\\d+)" + " "
          + "\\\"" + "(?<httpReferer>.*)" + "\\\"" + " "
          + "\\\"" + "(?<httpUserAgent>.*)" + "\\\""
  );
  public List<NginxBody> parse(List<String>logs){
      List<NginxBody>result= new ArrayList<>();
      for(String log:logs){
          Matcher matcher = logPattern.matcher(log);
          try {
              NginxBody formatedlog = new NginxBody(
                  InetAddress.getByName(matcher.group("remoteAdress")),
                  matcher.group("remoteUser"),
                  OffsetDateTime.parse(matcher.group("timeLocal"), dateFormat),
                  matcher.group("request"),
                  Integer.parseInt(matcher.group("status")),
                  Long.parseLong(matcher.group("bodyBytesSent")),
                  URI.create(matcher.group("httpReferer")),
                  matcher.group("httpUserAgent")
              );
              result.add(formatedlog);

          }catch (UnknownHostException e){

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
