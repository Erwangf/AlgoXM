package controller;

import model.Article;
import model.ArticleAttributes;
import model.SortableAttributes;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;
import java.util.*;

public class ArticleIndex{
    private Directory index;
    private IndexWriterConfig config;
    private StandardAnalyzer analyzer;
    private IndexWriter indexWriter;


    public static void main(String[] args) throws IOException, ParseException {
        ArticleIndex indx = new ArticleIndex();

        indx.addArticles(Article.getDefaultArticles()); //On ajoute des article à l'index
        System.out.println("1) on recherche les 2 premiers articles qui ont dans leur description la chaine \"la\"");
        // 1) on recherche les 2 premiers articles qui ont dans leur description la chaine "la"
        ArrayList<Article> result = indx.search(ArticleAttributes.DESCRIPTION + ":la", 2);

        // Affichage
        for (Article article : result) {
            System.out.println(article);
        }

        System.out.println("2) même chose, mais avec searchByTerms :");
        //2) même chose, mais avec searchByTerms :
        LinkedHashMap<String, String> termValueMap = new LinkedHashMap<>(); // HashMap <terme,valeur>

        //ici on veut que la description contienne la chaine "la"
        termValueMap.put(ArticleAttributes.DESCRIPTION, "la");

        //searchByTerms prend en entrée une hashMap <terme,valeur>, et un nombre de résultats
        ArrayList<Article> result2 = indx.searchByTerms(termValueMap, 2);

        for (Article article : result2) {
            System.out.println(article);
        }
        System.out.println("3) Effectuons la même recherche, mais on trie les résultats par auteur, par ordre alphabétique descendant");
        // 3) Effectuons la même recherche, mais on trie les résultats par auteur, par ordre alphabétique descendant
        ArrayList<Article> result3 = indx.searchByTerms(termValueMap,2,SortableAttributes.AUTHOR,false);
        for(Article article : result3){
            System.out.println(article);
        }

        System.out.println("4) On va supprimer l'article de Tom Jedusort ( le premier trouvé )");
        //4) On va supprimer l'article de Tom Jedusort ( le premier trouvé )
        termValueMap = new LinkedHashMap<>();
        termValueMap.put(ArticleAttributes.AUTHOR,"Tom Jedusort");
        Article articleASupprimer = indx.searchByTerms(termValueMap,1).get(0);
        indx.removeArticle(articleASupprimer.getID());
        System.out.println("Articles restants : ");

        for(Article article : indx.getAllArticles()){
            System.out.println(article);
        }

        //5) Supposons que Mario Velazio se marie, et prenne le nom de sa femme, Françoise Nette
        System.out.println("5) Supposons que Mario Velazio se marie, et prenne le nom de sa femme, Françoise Nette");
        // 5.1 : On récupère les articles de Mario Velazio
        termValueMap = new LinkedHashMap<>();
        termValueMap.put(ArticleAttributes.AUTHOR,"Mario Velazio");
        ArrayList<Article> articlesDeMario = indx.searchByTerms(termValueMap,1000);
        for(Article article : articlesDeMario){
            // 5.2 pour chaque article, on modifie l'article, et on met à jour l'index
            article.setAuthor("Mario Nette"); //quel humour !
            String ID = article.getID();
            indx.updateArticle(ID,article);
        }

        System.out.println("Articles restants : ");

        for(Article article : indx.getAllArticles()){
            System.out.println(article);
        }


    }


    public ArticleIndex() {

        // 1. On spécifie un analyzer
        analyzer = new StandardAnalyzer();

        // 2. On crée l'index (Directory)
        index = new RAMDirectory();

        // 3. le configure avec l'analyser
        config = new IndexWriterConfig(analyzer);

        // 4. On prépare un IndexWriter
        try {
            indexWriter = new IndexWriter(index, config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Article> getAllArticles(){
        try {
            return search("*:*",1000000);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<Article> search(String queryStr, int n, String sortTerm, boolean ascending) throws IOException, ParseException {

        // Par defaut, le champ description est utilisé si la requête n'en spécifie pas
        Query query = new QueryParser("title", analyzer).parse(queryStr);

        // Critère de tri

        Sort sort;
        if(sortTerm==null) sortTerm = "";
        switch (sortTerm) {
            case SortableAttributes.TITLE:
            case SortableAttributes.AUTHOR:
                // Le tri porte sur une chaine de caractère
                sort = new Sort(new SortField(sortTerm, SortField.Type.STRING, ascending));
                break;
            case ArticleAttributes.DATE:
                // La date est stockées sous forme de nombre de secondes
                // Le tri porte donc sur un nombre
                SortField sf = new SortedNumericSortField(sortTerm, SortField.Type.LONG, ascending);
                sort = new Sort(sf);

                break;
            default:
                sort = Sort.RELEVANCE;
                break;
        }

        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(query, n, sort);
        ScoreDoc[] hits = docs.scoreDocs;

        ArrayList<Article> result = new ArrayList<>();
        for (ScoreDoc hit : hits) {
            Document d = searcher.doc(hit.doc);
            result.add(new Article(d));
        }
        return result;
    }

    public ArrayList<Article> search(String queryStr, int n) throws IOException, ParseException{
        return search(queryStr,n,null,true);
    }

    public ArrayList<Article> searchByTerms(HashMap<String, String> mapTermValue, int n,String sortTerm, boolean ascending) throws IOException, ParseException {

        return search(buildQueryFromHashMap(mapTermValue), n,sortTerm,ascending);
    }

    public ArrayList<Article> searchByTerms(HashMap<String, String> mapTermValue, int n) throws IOException, ParseException {

        return search(buildQueryFromHashMap(mapTermValue), n);
    }

    private String buildQueryFromHashMap(HashMap<String, String> mapTermValue) {
        String strQuery = "";
        Iterator<Map.Entry<String, String>> iterator = mapTermValue.entrySet().iterator();
        boolean first = true;
        while (iterator.hasNext()) {
            if (!first) {
                strQuery += " ";
            }
            Map.Entry e = iterator.next();
            String term = (String) e.getKey();
            String value = (String) e.getValue();
            strQuery += term + ":" + value;
            first = false;
        }
        return strQuery;
    }


    public void stop() {
        try {
            indexWriter.close(); // fermeture de l'indexWriter
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Article getArticleByID(String ID) {
        //return hashMap.get(ID);
        try {
            return search(ArticleAttributes.ID+":"+ID,1).get(0);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addArticle(Article a) {
        //hashMap.put(a.getID(), a);
        try {
            indexWriter.addDocument(a.toDocument());
            indexWriter.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addArticles(Collection<Article> c) {
        for (Article a : c) {
            this.addArticle(a);
        }
    }

    public void updateArticle(String ID, Article a) {
        try {
            indexWriter.deleteDocuments(new Term("ID", ID));
            indexWriter.addDocument(a.toDocument());
            indexWriter.commit();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeArticle(String ID) {


        try {
            indexWriter.deleteDocuments(new Term("ID", ID));
            indexWriter.commit();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dropAll(){
        try {
            indexWriter.deleteAll();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
