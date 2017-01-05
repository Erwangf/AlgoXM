package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.opencsv.CSVReader;



public class StopWordsManager {

	
    static CSVReader reader = null;
    static String path = "./data/frenchstopwords.csv";
	
    /**
     * Crée une arrayList de stopwords Français à paritr du'n fichier csv
     * 
     * @return une ArrayList de String contenant les stopwords du fichiers
     */
	public static ArrayList<String> getFrenchStopWords() {
        ArrayList<String> sw = new ArrayList<>();
	
              try {
                reader = new CSVReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
                String[] fileLine;
                while ((fileLine = reader.readNext()) != null) {
                    sw.add(fileLine[0]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return sw;

        }
        
	/*
	 public static void main(String[] args) {
	    	
	    	System.out.println(getFrenchStopWords() );
	    }
	*/
}
