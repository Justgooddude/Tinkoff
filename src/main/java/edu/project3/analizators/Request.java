package edu.project3.analizators;

import edu.project3.NginxBody;
import java.util.List;
import static edu.project3.Report.create1x2row;

public class Request {
    public static String analyze(List<NginxBody> nginxLogItems) {
        return create1x2row("Total requests", Integer.toString(nginxLogItems.size()));
    }
}
