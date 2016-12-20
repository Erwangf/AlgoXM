package controller;

import model.Article;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class ArticleIndex implements Iterable<Article>{
    private LinkedHashMap<String,Article> hashMap;
    private Directory index;
    private IndexWriterConfig config;
    private StandardAnalyzer analyzer;
    private IndexWriter indexWriter;


    public static void main(String[] args) throws IOException, ParseException {
        ArticleIndex indx = new ArticleIndex();

        indx.addArticles(Article.getDefaultArticles()); //On ajoute des article à l'index

        ArrayList<Article> result = indx.search("description:la",2); // on recherche tous les articles qui ont dans leur description la chaine "la"
        for(Article article : result){
            System.out.println(article);
        }
    }


    public ArticleIndex(){
        hashMap = new LinkedHashMap<String, Article>();

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

    public ArrayList<Article> search(String queryStr, int n) throws IOException, ParseException {

        // Par defaut, le champ description est utilisé si la requête n'en spécifie pas
        Query query = new QueryParser("title", analyzer).parse(queryStr);


        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(query,n);
        ScoreDoc[] hits = docs.scoreDocs;

        ArrayList<Article> result = new ArrayList<Article>();
        for(ScoreDoc hit : hits){
            Document d = searcher.doc(hit.doc);
            result.add(hashMap.get(d.get("ID")));
        }
        return result;

    }


    public void stop(){
        try {
            indexWriter.close(); // fermeture de l'indexWriter
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Article getArticleByID(String ID){
        return hashMap.get(ID);
    }

    public void addArticle(Article a){
        hashMap.put(a.getID(),a);
        try {
            indexWriter.addDocument(a.toDocument());
            indexWriter.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addArticles(Collection<Article> c){
        for(Article a : c){
            this.addArticle(a);
        }
    }
    public void updateArticle(String ID,Article a){
        try {
            indexWriter.deleteDocuments(new Term("ID",ID));
            indexWriter.addDocument(a.toDocument());
            indexWriter.commit();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(a.getID().equals(ID)){
            hashMap.put(ID,a);
        }
        else{
            hashMap.remove(ID);
            hashMap.put(a.getID(),a);
        }
    }

    public void removeArticle(String ID){

        hashMap.remove(ID);

        try {
            indexWriter.deleteDocuments(new Term("ID",ID));
            indexWriter.commit();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void dropAll(){
        hashMap.clear();
    }

    public Iterator<Article> iterator() {
        return hashMap.values().iterator();
    }

    public Collection<Article> getAllArticles(){
        return hashMap.values();
    }
}
