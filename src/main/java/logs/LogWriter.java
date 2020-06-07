package logs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {

    private static final String logsFile = "C:\\Users\\ak_he\\IdeaProjects\\web-seevise\\src\\main\\java\\logs";
    private final File file = new File(logsFile);

    public void saveLog(String serverEx) throws IOException {
        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);

        fw.write(serverEx + " /n");
      }
}
