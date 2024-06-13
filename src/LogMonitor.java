import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LogMonitor {

    private Map<String, TailerConfigurator> tailerMap;

    private TailerManagement tailerManagement;

    private MyTailerListener tailerListener;





    String configurationFile = "";



    public LogMonitor(String configurationFile) throws IOException, ParseException {
        this.tailerManagement = new TailerManagement(configurationFile);
        this.tailerMap = tailerManagement.loadTailer();
        this.tailerListener = new MyTailerListener();

    }

    public void analizeMap() {

        for (String o : tailerMap.keySet()){




        }
    }





}







