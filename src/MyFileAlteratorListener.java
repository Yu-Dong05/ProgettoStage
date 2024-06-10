import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class MyFileAlteratorListener implements FileAlterationListener {


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
        System.out.println("Cartella rimossa: " + directory.getName());
    }

    @Override
    public void onFileChange(File file) {
        System.out.println("File modificato: " + file.getName());
    }

    @Override
    public void onFileCreate(File file) {
        // System.out.println("File creato: " + file.getName());
        if (file.getName() == "newfile.txt"){
            Set<String> words = new HashSet<String>();
            words.add("newtest");
            TailerConfigurator tailConfig = new TailerConfigurator(file, 200, words);
        }
    }

    @Override
    public void onFileDelete(File file) {
        System.out.println("File eliminato: " + file.getName());
    }

    @Override
    public void onStart(FileAlterationObserver observer) {

    }

    @Override
    public void onStop(FileAlterationObserver observer) {

    }
}
