package controller;

import model.Article;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.opencsv.CSVReader;

/**
 * Controleur permettant l'import et l'export d'un ensemble d'articles
 */
public class IOController {

	 SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
	 Article art;
	 ArrayList<Article> artList = new ArrayList<Article>();
	 CSVReader reader = null;
	 
	 //test
	 String csvFile = "E:/MASTER/JAVA/PROJET/data/10L_fr-blogs-1.csv";
	 
	 
	
    public static void main(String[] args) {
    	
    }

    
    public Date convertDate(String dateS) throws ParseException{
    	return sdf.parse(dateS);
    }
    
    
    public Article readLine(String[] line) throws MalformedURLException, ParseException{
    	art = new Article(line[0], line[1], convertDate(line[2]), line[3], line[4], new URL(line[5]));
    
    	return art;
    }
    	
    
    public ArrayList<Article> readFile(String path){
    
    	try {

               reader = new CSVReader(new InputStreamReader(new FileInputStream(csvFile), "UTF-8"),'\t', '\"', 1);
              
               String[] fileLine;
               while ((fileLine = reader.readNext()) != null) {
               	artList.add(readLine(fileLine));
                }
           } catch (IOException e) {
               e.printStackTrace();
           } catch (ParseException e) {
        	   e.printStackTrace();
		}

           return artList;
    
    }
    	
    	
   // 	Article art;
        

     //   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
    /*	String s = "Mon, 20 Jun 2016 01:32:03 -0400";
        Date formattedDate = simpleDateFormat.parse(s);
        System.out.println("date : "+simpleDateFormat.format(formattedDate)); 
     */
        
        
 

	
    /**
     * Importe un ensemble d'article depuis un InputReader fourni en paramètre.
     * @param fileIR Un InputReader associé à un fichier.
     * @return ArrayList d'Articles
     */
    public static ArrayList<Article> importCSV(InputStream fileIR) {
        return null;
    }
}
