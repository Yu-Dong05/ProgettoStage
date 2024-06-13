import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class MyTailerListener implements TailerListener {
    File tailedFile;
    Set<String> keywords = new HashSet<>();
    int counter = 0;

    public MyTailerListener(File tailedFile) {
        this.tailedFile = tailedFile;
    }

    @Override
    public void fileNotFound() {
        System.out.println(tailedFile.getName() + "Searched file does not exist");
    }

    @Override
    public void fileRotated() {

    }

    @Override
    public void handle(Exception ex) {
        System.out.println(tailedFile.getName() + "File launched an exception");
    }

    @Override
    public void handle(String line) {
        this.counter++;
        for (String keyword : keywords) {
            if (line.contains(keyword)){
                System.out.println(tailedFile.getName() + " in folder " + tailedFile.getParent() + " at line " + counter + ": keyword " + keyword + " found in " + line);
            }
        }
    }

    @Override
    public void init(Tailer tailer) {

    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }

    public Set<String> getKeywords() {
        return keywords;
    }
}
