import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import org.apache.commons.io.input.TailerListenerAdapter;

import java.io.File;
import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

public class TailerConfigurator {
    String path = null;
    int delay = 0;
    TailerListener listener = new MyTailerListener();
    Tailer tailer = null;
    Set<String> words = new HashSet<>();

    public TailerConfigurator(String path, int ms_delay, Set<String> words) {
        this.path = path;
        this.delay = ms_delay;
        ((MyTailerListener)listener).setKeywords(words);
    }

    public TailerConfigurator(String path) {
        this.path = path;
        this.delay = 0;
    }

    public void startMonitoring(){
        tailer = new Tailer(new File(path), listener, delay);
        Executor executor = new Executor() {
            @Override
            public void execute(Runnable command) {
                command.run();
            }
        };
        executor.execute(tailer);
    }

    public void addWord(String word) {
        words.add(word);
    }

}
