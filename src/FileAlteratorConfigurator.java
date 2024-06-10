import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.util.concurrent.Executor;

public class FileAlteratorConfigurator {
    String path;
    int delay;
    FileAlterationListener listener = new MyFileAlteratorListener();
    FileAlterationMonitor monitor;
    FileAlterationObserver observer;

    public FileAlteratorConfigurator(String path, int ms_delay) {
        this.path = path;
        this.delay = ms_delay;
        monitor = new FileAlterationMonitor(this.delay);
        observer = new FileAlterationObserver(new File(this.path));
        observer.addListener(listener);
        monitor.addObserver(observer);
    }

    public void startMonitoring() throws Exception {
        this.monitor.start();
    }
}
