import java.io.File;
import java.util.Set;

public class Configuration {
    // General Information
    private String folder_path;
    private String file_name;
    private File file;
    private Set<String> keywords;

    // Folder's FileAlterator


    public Configuration(String folder, String file, Set<String> words){
        this.folder_path = folder;
        this.file_name = file;
        this.file = new File(folder_path, file_name);
        this.keywords = words;
    }

    public String getFolder_path() {
        return folder_path;
    }

    public String getFile_name() {
        return file_name;
    }

    public File getFile() {
        return file;
    }

    public Set<String> getKeywords() {
        return keywords;
    }
}
