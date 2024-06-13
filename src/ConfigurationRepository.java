import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ConfigurationRepository {
    static Map<String, List<Configuration>> configurationsList = new HashMap<>();

    public ConfigurationRepository(){
        configurationsList = new HashMap<>();
    }
    
    static void addConfiguration(Configuration configuration){
        List<Configuration> list = configurationsList.get(configuration.getFolder_path());
        if(list == null){
            configurationsList.put(configuration.getFolder_path(), new LinkedList<>());
            list = configurationsList.get(configuration.getFolder_path());
        }
        list.add(configuration);
    }

    static void loadConfigurations() throws Exception {
        // JSON Parser
        JSONParser parser = new JSONParser();

        // First Object
        JSONObject element;
        JSONArray configurations;

        // Under "conf", what constitutes the single "configurations" object
        String folder;
        JSONArray files;

        // Under "files", what constitutes the single "files" object.
        String file;
        JSONArray word_array;
        Set<String> words;


        /*
            START PARSE
         */
        element = (JSONObject) parser.parse(new FileReader("configurations/esempioConfig.json"));

        // System.out.println("Test: " + element + "\n");

        configurations = (JSONArray) element.get("conf");

        // System.out.println("Test: " + configurations + "\n");

        for (Object configuration : configurations) {

            // System.out.println("Test: " + configuration + "\n");

            // Reads the configurations per FOLDER
            folder = ((JSONObject) configuration).get("folder").toString();
            files = (JSONArray) ((JSONObject) configuration).get("files");

            for (Object fileObj : files){

                // Reads the configurations per FILE PATTERN
                file = ((JSONObject) fileObj).get("file").toString();
                word_array = (JSONArray) ((JSONObject) fileObj).get("keywords");
                words = new HashSet<>();

                // System.out.println("Test: " + word_array + "\n");

                for (Object word : word_array){

                    words.add(word.toString());

                }

                System.out.println("Read new configuration: ");
                System.out.println("Folder path: " + folder);
                System.out.println("File name: " + file);
                System.out.println("Keywords: " + words);
                System.out.println();

                if (!configurationsList.containsKey(folder)){
                    configurationsList.put(folder, new LinkedList<>());
                }

                List<Configuration> list = configurationsList.get(folder);
                list.add(new Configuration(folder, file, words));
            }

            FileAlteratorRepository.addFileAlterator(folder);
        }

        FileAlteratorRepository.startFileAlterators();
    }
}
