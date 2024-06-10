import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

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
        monitor = new FileAlterationMonitor(ms_delay);
        observer = new FileAlterationObserver(path);
        observer.addListener(listener);
        monitor.addObserver(observer);
    }

    public void startMonitoring() throws Exception {

        Executor executor = new Executor() {
            @Override
            public void execute(Runnable command) {
                command.run();
            }
        };
        executor.execute(monitor);
    }
}
