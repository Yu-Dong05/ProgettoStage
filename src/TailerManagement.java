import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListenerAdapter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
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
            String file_path;
            Integer delay;
            for (Object obj : files){
                file_path = (String) ((JSONObject)obj).get("file");
                File file = new File(file_path);
                if (!file.exists()){
                    System.out.println("File " + file_path + " does not exist.");
                } else {
                    delay = Math.toIntExact((Long)((JSONObject) obj).get("delay"));
                    Set<String> keywords = new HashSet<>();
                    JSONArray array = (JSONArray) ((JSONObject) obj).get("keywords");
                    for (Object keyword : array) {
                        keywords.add((String) keyword);
                    }
                    System.out.println("Added file " + file + " with keywords " + keywords);
                    TailerConfigurator tailer = new TailerConfigurator(file, delay, keywords);
                    tailerMap.put(file.getPath(), tailer);
                }
            }
        } else {
            System.out.println("No file was passed or the file doesn't exist.");
        }

    }


    public void saveTailer() throws IOException {
        if (configurationFile != null && !configurationFile.isEmpty()) {
            JSONObject configuration = new JSONObject();
            if (this.folder == null || this.folder.isEmpty()) {
                System.out.println("No watch folder was set.");
                return;
            }
            configuration.put("folder", this.folder);
            JSONArray files = new JSONArray();
            for (TailerConfigurator tailer : tailerMap.values()) {
                JSONObject file = new JSONObject();
                file.put("file", tailer.getPath());
                file.put("delay", tailer.getDelay());
                JSONArray keywords = new JSONArray();
                for (String word : tailer.getWords()) {
                    System.out.println(word);
                    keywords.add(word);
                }
                file.put("keywords", keywords);
                files.add(file);
                System.out.println("Saved file " + tailer.getPath() + " with delay " + tailer.getDelay() + " and keywords " + keywords);
            }
            configuration.put("files", files);
            FileWriter fw = new FileWriter(configurationFile);
            fw.write(configuration.toJSONString());
            fw.flush();
            fw.close();
        } else {
            System.out.println("No configuration file given.");
        }





    }




}
