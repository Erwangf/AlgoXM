package controller;

import model.Article;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
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

public class IndexController {
    private Directory index;
    private IndexWriterConfig config;
    private StandardAnalyzer analyzer;

    public static void main(String[] args){
        IndexController IC = new IndexController();
        try {
            IC.loadArticles(Article.getDefaultArticles());
            for(Document d : IC.search("la",10)){
                System.out.println(d.get("title"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public IndexController() {
        // 0. On spécifie un analyzer
        analyzer = new StandardAnalyzer();

        // 1. On crée l'index (Directory)
        index = new RAMDirectory();
        // On le configure avec l'analyser
        config = new IndexWriterConfig(analyzer);
    }

    /**
     * Ajoute un ensemble d'articles à l'index
     *
     * @param articles une collection d'{@link Article}
     * @throws IOException En cas de problème I/O avec l'index
     */
    public void loadArticles(Collection<Article> articles) throws IOException {
        IndexWriter indexWriter = new IndexWriter(index, config);
        int i = 0;
        for (Article article : articles) {
            //on convertit l'article en document, et on l'ajoute à l'index
            indexWriter.addDocument(articleToDoc(article));
            i++;
        }
        indexWriter.close(); // fermeture de l'indexWriter

    }

    public ArrayList<Document> search(String queryStr, int n) throws IOException, ParseException {

        // Par defaut, le champ description est utilisé si la requête n'en spécifie pas
        Query query = new QueryParser("title", analyzer).parse(queryStr);


        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(query,n);
        ScoreDoc[] hits = docs.scoreDocs;

        ArrayList<Document> result = new ArrayList<Document>();
        for(ScoreDoc hit : hits){
            Document d = searcher.doc(hit.doc);
            result.add(d);
        }
        return result;

    }


    private static Document articleToDoc(Article article) {
        Document doc = new Document();

        doc.add(new TextField("title", article.getTitle(), Field.Store.YES));
        doc.add(new TextField("description", article.getDescription(), Field.Store.YES));
        doc.add(new TextField("rss", article.getRss(), Field.Store.YES));
        doc.add(new TextField("author", article.getAuthor(), Field.Store.YES));

        //date stockée en millisecondes (getTime)
        doc.add(new LongPoint("date", article.getDate().getTime()));

        //link stocké sous forme de texte
        doc.add(new TextField("link", article.getLink().toString(), Field.Store.YES));
        return doc;
    }
}
