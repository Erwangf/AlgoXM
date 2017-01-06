package model;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class StopWordsManager {

	
    static CSVReader reader = null;
    static String path = "frenchstopwords.csv";
	
    /**
     * Crée une arrayList de stopwords Français à paritr d'un fichier csv
     * 
     * @return une ArrayList de String contenant les stopwords du fichiers
     */
	public static ArrayList<String> getFrenchStopWords() {
        ArrayList<String> sw = new ArrayList<>();
	    ClassLoader classLoader = new Object() { }.getClass().getClassLoader();
              try {
                reader = new CSVReader(new InputStreamReader(classLoader.getResourceAsStream(path),"UTF-8"));
                String[] fileLine;
                while ((fileLine = reader.readNext()) != null) {
                    sw.add(fileLine[0]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return sw;

        }

}
