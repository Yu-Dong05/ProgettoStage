import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListenerAdapter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TailerManagement {


     private Map<String, TailerConfigurator> tailerMap;
     private String folder;
     private String configurationFile;

    public TailerManagement(String file_path) {
        this.tailerMap = new HashMap<>();
        this.configurationFile = file_path;
        this.folder = null;
    }


    public void loadTailer() throws IOException, ParseException {

        if (configurationFile != null && !configurationFile.isEmpty()) {
            File configFile = new File(configurationFile);
            JSONParser parser = new JSONParser();
            JSONObject config = (JSONObject) parser.parse(new FileReader(configFile));
            this.folder = (String) config.get("folder");
            JSONArray files = (JSONArray) config.get("files");
            String file;
            for (Object obj : files){
                file = (String) ((JSONObject)obj).get("file");
                Set<String> keywords = new HashSet<>();
                JSONArray array = (JSONArray) ((JSONObject)obj).get("keywords");
                for (Object keyword : array){
                    keywords.add((String) keyword);
                }
                System.out.println("Added file " + file + " with keywords " + keywords);
                TailerConfigurator tailer = new TailerConfigurator(file, 200, keywords);
                tailerMap.put(file, tailer);
            }
        } else {
            System.out.println("No file was passed or the file doesn't exist.");
        }

    }


    public void saveTailer() {




    }




}
