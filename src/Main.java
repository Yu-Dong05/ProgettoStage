import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import org.apache.commons.io.input.TailerTest;
import org.apache.commons.io.monitor.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

public class Main {
    public static void main(String[] args) throws IOException {
        /*
        File inputFile = new File("module/input.txt");
        List<String> lines = FileUtils.readLines(inputFile, "UTF-8");
        List<String> hightlight = new ArrayList<>();
        for (String line : lines) {
            if (line.contains("Isola")) {
                hightlight.add(line);

            }
        }

        for (String line : hightlight) {
            System.out.println(lines.indexOf(line));
            System.out.println(line);
        }
        */

        Set<String> set  = new HashSet<>();
        set.add("test");
        set.add("Igot"); // da Igor
        TailerConfigurator tailerConfig = new TailerConfigurator("module/input.txt", 200, set);
        tailerConfig.startMonitoring();







        /*
        File outputFile = new File("module/output.txt");
        FileUtils.writeLines(outputFile, lines);
        */
    }
}