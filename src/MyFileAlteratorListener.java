import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyFileAlteratorListener implements FileAlterationListener {


    @Override
    public void onDirectoryChange(File directory) {
        // System.out.println("Cartella modificata: " + directory.getName());
    }

    @Override
    public void onDirectoryCreate(File directory) {
        // System.out.println("Cartella creata: " + directory.getName());
    }

    @Override
    public void onDirectoryDelete(File directory) {
        // System.out.println("Cartella rimossa: " + directory.getName());
    }

    @Override
    public void onFileChange(File file) {
        // System.out.println("File modificato: " + file.getName());
    }

    @Override
    public void onFileCreate(File file) {
        System.out.println("File creato: " + file.getName());
        if (ConfigurationRepository.configurationsList.containsKey(file.getParentFile().toString())){

            System.out.println("Folder check passed");

            for (Configuration config : ConfigurationRepository.configurationsList.get(file.getParentFile().toString())){

                if (file.getName().equals(config.getFile_name())){

                    TailerRepository.addTailer(config);
                    System.out.println("Added and started tailer for file " + file.getName() + " in directory " + file.getParentFile().toString());
                    System.out.println("Tailed keywords are " + config.getKeywords());

                }
            }
        }
        System.out.println();
    }

    @Override
    public void onFileDelete(File file) {
        // System.out.println("File eliminato: " + file.getName());
    }

    @Override
    public void onStart(FileAlterationObserver observer) {

    }

    @Override
    public void onStop(FileAlterationObserver observer) {

    }
}
