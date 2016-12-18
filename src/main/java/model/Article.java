package model;

import java.net.URL;
import java.util.Date;

public class Article {

    private String title;
    private String description;
    private Date date;
    private String rss;
    private String author;
    private URL link;
    private int inter;
    private String access;

    public Article() {

    }

    public Article(String title, String description, Date date, String rss, String author, URL link, int inter) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.rss = rss;
        this.author = author;
        this.link = link;
        this.inter = inter;
    }

    public Article(String title, String description, Date date, String rss, String author, URL link) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.rss = rss;
        this.author = author;
        this.link = link;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", rss='" + rss + '\'' +
                ", author='" + author + '\'' +
                ", link=" + link +
                ", inter=" + inter +
                '}';
    }


    // GETTERS & SETTERS

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRss() {
        return rss;
    }

    public void setRss(String rss) {
        this.rss = rss;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }

    public int getInter() {
        return inter;
    }

    public void setInter(int inter) {
        this.inter = inter;
    }

    public String getAccess() {
        if(access==null) return "ALL";
        else return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
}
