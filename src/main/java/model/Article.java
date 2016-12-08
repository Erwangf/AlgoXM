package model;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class Article {

    private String title;
    private String description;
    private Date date;
    private String rss;
    private String author;
    private URL link;
    private int inter;

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

    public String getFormattedTitle() {
        if (title == null) return "-";
        else return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getFormattedDescription() {
        if (description == null) {
            return "";
        } else return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public String getFormattedDate() {
        if (date == null) {
            return "Date inconnue";
        } else return date.toString();
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Set the date of the article from a String
     * //TODO Tester
     *
     * @param strDate
     */
    public void setDate(String strDate) {
        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

        try {
            this.date = shortDateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getRss() {
        return rss;
    }

    public String getFormattedRss() {
        if (rss == null) {
            return "";
        } else return rss;

    }

    public void setRss(String rss) {
        this.rss = rss;
    }

    public String getAuthor() {
        return author;
    }

    public String getFormattedAuthor() {
        if (author == null) return "Auteur inconnu";
        else return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public URL getLink() {
        return link;
    }

    public String getFormattedLink() {
        if(link==null) return "Pas de lien";
        else return link.toString();
    }

    public void setLink(URL link) {
        this.link = link;
    }

    public int getInter() {
        return inter;
    }

    public int getFormattedInter() {
        return inter;
    }

    public void setInter(int inter) {
        this.inter = inter;
    }


}
