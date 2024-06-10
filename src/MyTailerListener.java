import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;

import java.util.HashSet;
import java.util.Set;

public class MyTailerListener implements TailerListener {
    Set<String> keywords = new HashSet<>();
    @Override
    public void fileNotFound() {

    }

    @Override
    public void fileRotated() {

    }

    @Override
    public void handle(Exception ex) {
        System.out.println("Exception");
    }

    @Override
    public void handle(String line) {
        for (String keyword : keywords) {
            if (line.contains(keyword)){
                System.out.println("keyowrd " + keyword + " trovata in " + line);
            }
        }
    }

    @Override
    public void init(Tailer tailer) {

    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }
}
