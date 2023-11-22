package edu.project3;

import java.time.OffsetDateTime;
import java.util.List;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;

@CommandLine.Command(name = "Log an", description = "") public class ComandLineAn {

    @CommandLine.Option(names = {"--path"}, required = true) public List<String> paths;
    @CommandLine.Option(names = {"--from"}) public OffsetDateTime from;
    @CommandLine.Option(names = {"--to"}) public OffsetDateTime to;
    @CommandLine.Option(names = {"--format"}) public Report.ReportFormat format;

}
