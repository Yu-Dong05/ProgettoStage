import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import org.apache.commons.io.input.TailerTest;
import org.apache.commons.io.monitor.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

        TailerConfigurator tailerConfig = new TailerConfigurator("module/input.txt", 200);
        tailerConfig.startMonitoring();



        FileAlterationObserver fao = new FileAlterationObserver("module");
        FileAlterationMonitor fam = new FileAlterationMonitor(2000);
        FileAlterationListener fal = new FileAlterationListener(){

            @Override
            public void onDirectoryChange(File directory) {
                System.out.println("Cartella modificata: " + directory.getName());
            }

            @Override
            public void onDirectoryCreate(File directory) {
                System.out.println("Cartella creata: " + directory.getName());
            }

            @Override
            public void onDirectoryDelete(File directory) {
                System.out.println("Cartella Eliminata: " + directory.getName());
            }

            @Override
            public void onFileChange(File file) {
                System.out.println("File modificato: " + file.getName());
            }

            @Override
            public void onFileCreate(File file) {
                System.out.println("File creato: " + file.getName());
            }

            @Override
            public void onFileDelete(File file) {
                System.out.println("File eliminato: " + file.getName());
            }

            @Override
            public void onStart(FileAlterationObserver observer) {
                System.out.println("Alteration thread started");
            }

            @Override
            public void onStop(FileAlterationObserver observer) {
                System.out.println("Alteration thread stopped");
            }
        };

        fao.addListener(fal);
        fam.addObserver(fao);
        Thread alterationThread = new Thread(fam);
        alterationThread.start();



        /*
        File outputFile = new File("module/output.txt");
        FileUtils.writeLines(outputFile, lines);
        */
    }
}