package controller;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import model.Article;
import model.ArticleAttributes;
import org.apache.commons.lang.StringEscapeUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Function;

/**
 * Controleur permettant l'import et l'export d'un ensemble d'articles
 */
public class IOController {


    private static Function<CSVError,String> errorHandler;
    private static final CSVParser parser = new CSVParser('\t', '\"');

    public static void setErrorHandler(Function<CSVError, String> errorHandler) {
        IOController.errorHandler = errorHandler;
    }
    //*********PARTIE IMPORT*********//

    //format de la date, exemple : "Mon, 20 Jun 2016 01:32:03 -0400"
    static SimpleDateFormat sdf = Article.dateFormat;
    static Article art;
    static ArrayList<Article> artList = new ArrayList<>();
    static CSVReader reader = null;

    //test
    //static String pathTest = "E:/MASTER/JAVA/PROJET/data/10L_fr-blogs-1.csv"; 


    /**
     * Convertit une chaine de date en type date (ne fonctionne que pour le format "EEE, d MMM yyyy HH:mm:ss Z"
     *
     * @param dateS une date de type String
     * @return dateS en type Date
     */
    public static Date convertDate(String dateS) throws ParseException {
        return sdf.parse(dateS);
    }


    /**
     * Transforme un tableau de String (une ligne) passé en paramètre en un Article.
     *
     * @param rawline un tableau de String où chaque case = un champ d'un fichier
     * @return art un objet Article
     */
    public static Article readLine(String rawline) throws ParseException {
        if(rawline.equals("\"title\"\t\"description\"\t\"date\"\t\"rss\"\t\"author\"\t\"link\"")){
            return null; //Header
        }
        String[] line;
        String title = null;
        String description = null;
        Date date = null;
        String rss = null;
        String author = null;
        URL url = null;
        boolean validated = false;
        while(!validated) {
            try {
                line = parser.parseLine(rawline);
                validated = true;
                if (line.length != 6) throw new Exception("Erreur, nombre de champs incorrects");
                title = line[0];
                if(title.length()==0) throw new Exception("Erreur, pas de titre !");
                description = StringEscapeUtils.unescapeHtml(line[1]);
                date = (line[2].length()==0 ? null : convertDate(line[2]));
                rss = line[3];
                author = line[4];
                if(line[5].length()==0) url = null;
                else{
                    url = new URL(line[5]);
                }
            } catch (MalformedURLException e){
                validated = false;
                rawline = errorHandler.apply(new CSVError("Erreur dans l'URL", rawline));
            }

            catch (Exception e) {
                validated = false;
                rawline = errorHandler.apply(new CSVError(e.getMessage(), rawline));
            }
        }

        art = new Article(title,description, date, rss, author, url );

        return art;
    }





    /**
     * Importe un ensemble d'article depuis un chemin de fichier fourni en paramètre.
     *
     * @param pathSrc un chemin de fichier csv (UTF-8, séparateur tabulation, 1 ligne d'entête, champs délimités par des ")
     * @return ArrayList d'Articles
     */
    public static ArrayList<Article> readFile(String pathSrc) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathSrc), "UTF8"));

            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if(!isHeader){
                    Article a = readLine(line);
                    if(a!=null)artList.add(a);
                }
                isHeader = false;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return artList;

    }


    //*********PARTIE EXPORT*********//


    static String separator = "\t";
    static String delimitor = "\"";


    /**
     * Transforme un article en String contenant les données, le délimiteur et le séparateur
     *
     * @param a un article
     * @return oneLine un String contenant une ligne article
     */
    public static String artToString(Article a) {
        String oneLine = delimitor + a.getTitle() + delimitor + separator + delimitor + a.getDescription() + delimitor + separator
                + delimitor + sdf.format(a.getDate()) + delimitor + separator + delimitor + a.getRss() + delimitor + separator
                + delimitor + a.getAuthor() + delimitor + separator + delimitor + a.getLink() + delimitor;
        return oneLine;
    }


    /**
     * Crée une ligne String pour chaque article d'une ArrayList et l'ajoute au fichier csv passé en paramètre
     *
     * @param listArt une liste d'articles, pathTgt un chemin cible pour un fichier csv
     */
    public static void writeToCSV(ArrayList<Article> listArt, String pathTgt) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathTgt), "UTF-8"));
            String entete = delimitor + ArticleAttributes.TITLE + delimitor + separator + delimitor + ArticleAttributes.DESCRIPTION + delimitor + separator
                    + delimitor + ArticleAttributes.DATE + delimitor + separator + delimitor + ArticleAttributes.RSS + delimitor + separator
                    + delimitor + ArticleAttributes.AUTHOR + delimitor + separator + delimitor + ArticleAttributes.LINK + delimitor;
            bw.write(entete);
            bw.newLine();
            for (Article a : listArt) {
                bw.write(artToString(a));
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /*
    public static void main(String[] args) {
    	// System.out.println(readFile(pathTest));
    	writeToCSV(readFile(pathTest), "E:/MASTER/JAVA/PROJET/data/test1.csv");
    }
     */
        
    /*	test format de date
     
      String s = "Mon, 20 Jun 2016 01:32:03 -0400";
      Date formattedDate = sdf.parse(s);
      System.out.println("date : "+sdf.format(formattedDate)); 
     */


}
