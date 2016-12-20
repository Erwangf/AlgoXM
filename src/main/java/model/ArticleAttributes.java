package model;

/**
 * Created by Erwan on 20/12/2016.
 */
public enum ArticleAttributes {

    TITLE("title"),
    DESCRIPTION("description"),
    AUTHOR("author"),
    DATE("date"),
    RSS("rss"),
    LINK("link"),
    ID("ID");

    private final String name;

    ArticleAttributes(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
