import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TailerRepository {
    static Map<String, List<TailerConfigurator>> tailers = new HashMap<>();

    public TailerRepository() {}

    static void addTailer(Configuration configurator) {
        List<TailerConfigurator> list = tailers.get(configurator.getFolder_path());
        if (list == null){
            tailers.put(configurator.getFolder_path(), new ArrayList<>());
            list = tailers.get(configurator.getFolder_path());
        }
        TailerConfigurator tailer = new TailerConfigurator(new File(configurator.getFolder_path(), configurator.getFile_name()), configurator.getKeywords());
        list.add(tailer);
        tailer.startMonitoring();
    }

    static void startTailer(String folder) {
        List<TailerConfigurator> list = tailers.get(folder);
        if (list == null || list.isEmpty()) {System.out.println("No tailers were started"); return;}
        for (TailerConfigurator tailer : list) {
            tailer.startMonitoring();
        }
        System.out.println("Tailers for " + folder + " started.");
    }

    static void startTailerAll() {
        for (List<TailerConfigurator> list : tailers.values()) {
            for (TailerConfigurator tailer : list) {
                tailer.startMonitoring();
            }
        }

        System.out.println("All tailers have been started");
    }

    static void stopTailerAll() {
        for (List<TailerConfigurator> list : tailers.values()) {
            for (TailerConfigurator tailer : list) {
                tailer.stopMonitoring();
            }
        }

        System.out.println("All tailers have been stopped");
    }
}
