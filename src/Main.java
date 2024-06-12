import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import org.apache.commons.io.input.TailerTest;
import org.apache.commons.io.monitor.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.Executor;

public class Main {
    public static void main(String[] args) throws Exception {
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

        /*
        Set<String> set  = new HashSet<>();
        set.add("test");
        set.add("Igot"); // da Igor
        TailerConfigurator tailerConfig = new TailerConfigurator("module/input.txt", 200, set);
        tailerConfig.startMonitoring();
        */

        Scanner scanner = new Scanner(System.in);

        FileAlteratorConfigurator alteratorConfig = new FileAlteratorConfigurator("module", 1000);
        alteratorConfig.startMonitoring();

        Set<String> keywords = new HashSet<>();
        TailerConfigurator tailer = null;
        File file = null;
        int scelta = -1;
        do{
            System.out.println("1)Carica File\n2)Inizia Ricerca\n3)Rimuovi File");
            scelta = scanner.nextInt();
            switch(scelta){

                case 1:

                    System.out.println("Inserisci il percorso del File da caricare: ");
                    String path = scanner.next();
                    file = new File(path);

                    if(!file.exists()){
                        System.out.println("Attenzione, file inesistente");
                        break;
                    }

                    System.out.println("File caricato correttamente");

                    String parole = "ciao casa parola";

                    System.out.println("Le keyword sono: ciao, casa, parola");


                    keywords.addAll(Arrays.asList(parole.split(" ")));

                    System.out.println(Arrays.asList(parole.split(" ")));


                    tailer = new TailerConfigurator(path,500,keywords);

                    break;

                case 2:

                    if(tailer != null) {
                        tailer.startMonitoring();
                    }
                    else{
                        System.out.println("Attenzione, file inesistente");
                    }

                    break;

                case 3:

                    if(tailer != null)
                        tailer.stopMonitoring();
                    tailer = null;

                    if(file != null) {
                        FileUtils.forceDelete(file);
                    }
                    else
                        System.out.println("File inesistente");

                    break;
            }

        }while(scelta != 0);

        /*
        File outputFile = new File("module/output.txt");
        FileUtils.writeLines(outputFile, lines);
        */
    }
}