import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileAlteratorRepository {
    static Map<String, FileAlteratorConfigurator> fileAlterators = new HashMap<>();

    public FileAlteratorRepository() {
        fileAlterators = new HashMap<>();
    }

    static void addFileAlterator(String folderPath){
        FileAlteratorConfigurator configurator = new FileAlteratorConfigurator(folderPath);
        fileAlterators.put(folderPath, configurator);
    }

    static void startFileAlterators() throws Exception {
        for(FileAlteratorConfigurator configurator : fileAlterators.values()){
            configurator.startMonitoring();
        }
    }

    static void stopFileAlterators() throws Exception {
        for(FileAlteratorConfigurator configurator : fileAlterators.values()){
            configurator.stopMonitoring();
        }
    }
}