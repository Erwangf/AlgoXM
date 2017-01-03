package core;

import controller.ArticleIndex;
import model.Article;
import org.apache.lucene.queryparser.classic.ParseException;
import view.MainGUI;

import java.io.IOException;

/**
 * Created by Erwan on 28/12/2016.
 */
public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        start1();
    }

    public static void start1(){
        ArticleIndex index = new ArticleIndex();

        MainGUI gui = new MainGUI();
        Thread guiThread = new Thread(gui);
        guiThread.start();
        gui.setArticleList(Article.getDefaultArticles());
    }
}
