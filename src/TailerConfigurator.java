import org.apache.commons.io.FileUtils;
import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import org.apache.commons.io.input.TailerListenerAdapter;
import org.json.simple.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Executable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

public class TailerConfigurator {
    String path;
    int delay = 200;
    TailerListener listener;
    Tailer tailer = null;
    Set<String> words = new HashSet<>();
    Thread thread = null;

    public TailerConfigurator(String path, Set<String> words) {
        this.path = path;
        listener = new MyTailerListener(new File(path));
        ((MyTailerListener)listener).setKeywords(words);
    }

    public TailerConfigurator(File file, Set<String> words) {
        this.path = file.getPath();
        listener = new MyTailerListener(file);
        ((MyTailerListener)listener).setKeywords(words);
    }


    public void startMonitoring() {
        tailer = new Tailer(new File(path), listener, delay);
        thread = new Thread(tailer);
        thread.start();

    }

    public void stopMonitoring() {
        thread.stop();

    }

    public String getPath() {
        return path;
    }

    public int getDelay() {
        return delay;
    }

    public Set<String> getWords() {
        return ((MyTailerListener)listener).getKeywords();
    }
}
