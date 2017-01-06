package controller;

import model.ArticleAttributes;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;

import model.Article;


/**
 * Contrôleur permettant d'afficher différentes statistiques d'une ArrayList d'Article avec Lucene
 */
public class StatController {
	
	
	public static void main(String[]args) throws IOException, ParseException{
		
		ArticleIndex AI = new ArticleIndex();
        ArrayList<Article> list = IOController.readFile("E:/MASTER/JAVA/PROJET/data/10L_fr-blogs-1.csv");
        AI.addArticles(list);
        System.out.println("Articles ajoutés !");
        
        System.out.println(AI.getNbDocTerm(new Term(ArticleAttributes.DESCRIPTION, "union")) );
        System.out.println(AI.getFreqTerm(new Term(ArticleAttributes.DESCRIPTION, "union")) );
      //  AI.runAnalysis();
        System.out.println(AI.getTopFreq(5));

	}
	
}
