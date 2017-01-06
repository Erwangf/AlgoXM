package model;

/**
 * Classe à l'usage statique, contenant les attributs triables des Documents Lucene générés à partir des objets Article
 */
public class SortableAttributes {
    private static final String suffix = "Sort";

    public static final String AUTHOR = ArticleAttributes.AUTHOR+suffix;
    public static final String DATE = ArticleAttributes.DATE+suffix;
    public static final String TITLE = ArticleAttributes.TITLE+suffix;
    public static final String RELEVANCE = "RELEVANCE";
}
