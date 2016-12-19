package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class ArticleBase implements Iterable<Article>{
    private LinkedHashMap<String,Article> hashMap;
    public ArticleBase(){
        hashMap = new LinkedHashMap<String, Article>();
    }

    public Article getArticleByID(String ID){
        return hashMap.get(ID);
    }

    public void addArticle(Article a){
        hashMap.put(a.getID(),a);
    }

    public void addArticles(Collection<Article> c){
        for(Article a : c){
            this.addArticle(a);
        }
    }
    public void updateArticle(String ID,Article a){
        hashMap.put(ID,a);
    }
    public void removeArticle(String ID){
        hashMap.remove(ID);
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
