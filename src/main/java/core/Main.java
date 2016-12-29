package core;

import controller.ArticleIndex;
import controller.IOController;
import model.Article;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Erwan on 28/12/2016.
 */
public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        ArticleIndex AI = new ArticleIndex();
        ArrayList<Article> list = IOController.readFile("./data/fr-news-1.csv");
        AI.addArticles(list);
        System.out.println("Articles ajoutés !");
        ArrayList<Article> l1 = AI.search("title:Paris",200);
        for(Article a : l1){
            System.out.println(a);
        }
        IOController.writeToCSV(l1,"./data/result.csv");

    }
}
