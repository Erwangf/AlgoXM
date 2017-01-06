package model;

import javafx.beans.property.SimpleStringProperty;
import org.apache.lucene.document.*;
import org.apache.lucene.util.BytesRef;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Article {


    private final PropertyChangeSupport propertySupport;
    public final static SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
    private String title;
    private String description;
    private Date date;
    private String rss;
    private String author;
    private URL link;
    private final String ID; // l'identifiant d'un article ne peut pas être modifié


    //Properties
    private SimpleStringProperty titleProperty;

    /**
     * Construit un Article à partir de champs
     *
     * @param title       Le Titre de l'article
     * @param description La description ( contenu ) de l'article
     * @param date        La date de l'article
     * @param rss         Le flux RSS de l'article
     * @param author      L'auteur de l'article
     * @param link        L'URL vers l'article
     */
    public Article(String title, String description, Date date, String rss, String author, URL link) {
        ID = UUID.randomUUID().toString(); //création d'un identifiant unique et aléatoire pour l'objet créé.
        this.title = title;
        this.description = description;
        this.date = date;
        this.rss = rss;
        this.author = author;
        this.link = link;

        this.titleProperty = new SimpleStringProperty(this.title);
        this.propertySupport = new PropertyChangeSupport(this);
    }

    /**
     * Construit un Article à partir d'un Document Lucene
     *
     * @param d Un document Lucene
     */
    public Article(Document d) {

        this.ID = d.get(ArticleAttributes.ID);
        this.title = d.get(ArticleAttributes.TITLE);
        this.description = d.get(ArticleAttributes.DESCRIPTION);
        this.date = new Date(Long.parseLong(d.get(ArticleAttributes.DATE)));
        this.rss = d.get(ArticleAttributes.RSS);
        this.author = d.get(ArticleAttributes.AUTHOR);
        try {
            this.link = new URL(d.get(ArticleAttributes.LINK));
        } catch (MalformedURLException e) {
            this.link = null;
        }

        this.titleProperty = new SimpleStringProperty(this.title);
        this.propertySupport = new PropertyChangeSupport(this);


    }

    /**
     * Fourni une liste d'articles par défaut
     *
     * @return Une liste d'articles par défaut
     */
    public static ArrayList<Article> getDefaultArticles() {
        ArrayList<Article> l = new ArrayList<>();

        try {


            l.add(new Article(
                    "La véritable identité de Batman enfin révelée !",
                    "Il s'agirait probablement de Julien Jacques.",
                    new Date(),
                    "http://closer.com/rss-jj-batman",
                    "Mario Velazio",
                    new URL("http://closer.com/jj-batman")
            ));
            l.add(new Article(
                    "Flash et Robin, l'histoire qui dérange Batman",
                    "Dans sa sombre ville de Gotham City, Batman a parfois quelques petits soucis à résoudre, la pègre, " +
                            "bien sûr, mais aussi des problèmes de coeur. En effet, Robin, l'ex de Batman depuis 5 mois" +
                            " a annoncé être désormais en couple avec Flash (ahahhhh savior of the universe !!) Plus d'info sur Coser.com...",
                    new Date(),
                    "http://closer.com/rss-robin-flash-batman",
                    "Mario Velazio",
                    new URL("http://closer.com/robin-flash-batman")
            ));

            l.add(new Article(
                    "Epoque Révolutionnaire ou Robots tueurs de bébés",
                    "L'époque est révolutionnaire: aventures attendues dans le bionique, l'IA, l'imprimerie 3D, " +
                            "la fusion froide sans compter les robots qui arrivent et nous inquiètent. Tout ce qui paraissait à " +
                            "l'endroit passe à l'envers. Les prix eux-mêmes dont la progression non maitrisée était la" +
                            " caractéristique première ne parviennent plus à monter. Le prix du pétrole dégringole à des " +
                            "niveaux que la morale d'autrefois aurait réprouvé. La BCE devait empêcher le taux d'inflation " +
                            "de dépasser le seuil diabolique de 2%. C'est pire: il s'est installé bien en dessous et n'en bouge" +
                            " plus. La révolution suit son chemin et s'installe triomphalement dans les endroits les moins attendus:" +
                            " la banque, la finance, l'Argent en d'autres termes. La révolution? L'argent qui ne vaut plus rien." +
                            " Les flots de monnaie qui dévalent et ne s'arrêtent pas même dans les poches. La foule des prêteurs " +
                            "qui erre, have et déguenillée, pareille à un migrant de base et qui tend sa sébile suppliant les" +
                            " emprunteurs de prendre l'argent qui dégouline de ses poches. La révolution de l'argent? Le marché " +
                            "qui assure la rencontre de l'offre et de la demande ne connaît pas les territoires négatifs pour les" +
                            " services, les biens et les marchandises, c'est-à-dire tout ce qui a un coût de production. Or," +
                            " justement, la monnaie ne coûte rien à fabriquer. Regardez comme font les banques centrales. Elles " +
                            "disent je vais vous en passer pour 60 milliards par mois et en avant, vous avez vos 60 milliards! ." +
                            " Pas de souci pour le rythme de production, pas de tensions sur les capacités productives. La révolution " +
                            "de la monnaie renvoie au grenier dans les vieilles malles napthalinisées toutes les théories monétaires." +
                            " L'argent islamique qui a pompé les vieux préceptes de la sagesse grecque et vous regarde de haut en " +
                            "baragouinant l'argent ne fait pas de petits est ringardisé. Le débiteur n'est pas la victime d'une in" +
                            "justice sociale. On n'a plus besoin de convoquer les dieux de l'Olympe et de l'Islam pour condamner" +
                            " ceux qui prétendent lui faire suer de l'or. Révolutionner, c'est mettre à l'envers. De nos jours " +
                            "ceux qui souffrent de l'argent sont ceux qui le produisent. Qu'en faire, une fois fabriqué? Le poser " +
                            "sur des étals devant leurs boutiques, saignant et bien à point. Comment faire boire tous ces ânes qui " +
                            "n'ont pas soif et déambulent sans emprunter. Le seul moyen: le taux négatif. L'équivalent capitaliste " +
                            "du pain et des jeux de l'Empire Romain. Le cadeau publicitaire institutionnalisé. La démarque qui se " +
                            "perpétue. Le temps ne fait plus rien à l'affaire: il s'est arrêté, il a suspendu son vol. Autrefois, " +
                            "l'argent de demain ne valait pas celui d'aujourd'hui. Il fallait payer pour convaincre son propriétaire " +
                            "de ne le retrouver qu'après quelques temps . Avec les taux d'intérêts négatifs, l'argent de demain ne vaut" +
                            " plus rien du tout. Plus personne ne veut payer pour en avoir, c'est tout l'inverse: il faut payer le " +
                            "débiteur pour qu'il en prenne. Révolution... Pascal Ordonneau - Le retour de l'Empire Allemand ou" +
                            " le Modèle Imaginaire chez JFE éditions.",
                    new Date(),
                    "http://closer.com/rss-robin-flash-batman",
                    "Tom Jedusort",
                    new URL("http://marie-pottere.com/epoque-rev")
            ));


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return l;
    }

    @Override
    public String toString() {
        return "Title = " + title + "\n" +
                "ID = " + ID + "\n" +
                "Author = " + author + "\n" +
                "Date = " + date + "\n" +
                "RSS = " + rss + "\n" +
                "link = " + link.toString() + "\n" +
                "Description = " + (description.length() > 128 ? description.substring(0, 128) + "..." : description) + "\n";
    }


    /**
     * Construit un document à partir de l'article actuel
     *
     * @return Le document correspondant à l'Article
     */
    public Document toDocument() {
        Document doc = new Document();
        // 1 : Champs indexables (TextField, LongPoint, StringField)
        doc.add(new TextField(ArticleAttributes.TITLE,title, Field.Store.YES));

        //nécessaire pour créer un TermVector sur description
        FieldType myFieldType = new FieldType(TextField.TYPE_STORED);
        myFieldType.setStoreTermVectors(true);
        doc.add(new Field(ArticleAttributes.DESCRIPTION, description, myFieldType));

       // doc.add(new TextField(ArticleAttributes.DESCRIPTION, description, Field.Store.YES));
        doc.add(new TextField(ArticleAttributes.RSS, rss, Field.Store.YES));
        doc.add(new TextField(ArticleAttributes.AUTHOR, author, Field.Store.YES));
        if(date!=null){
            //date stockée en millisecondes (getTime)
            doc.add(new StoredField(ArticleAttributes.DATE, date.getTime()));
            doc.add(new SortedNumericDocValuesField(SortableAttributes.DATE, date.getTime()));
        }


        //link stocké sous forme de texte
        if (link != null) {
            doc.add(new TextField(ArticleAttributes.LINK, link.toString(), Field.Store.YES));
            doc.add(new StringField(ArticleAttributes.ID, ID, Field.Store.YES));
        }


        // 2 : champs triables (Sorted[Fields...])
        doc.add(new SortedDocValuesField(SortableAttributes.TITLE, new BytesRef(title)));
        doc.add(new SortedDocValuesField(SortableAttributes.AUTHOR, new BytesRef(author)));


        return doc;
    }

    // =============== GETTERS & SETTERS =================
    public String getTitle() {
        return titleProperty.get();
    }

    public void setTitle(String title) {
        propertySupport.firePropertyChange("set", this.title, title);
        this.titleProperty.set(title);

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

    public String getStrDate() {
        return dateFormat.format(this.date);
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

    public String getID() {
        return ID;
    }

    public SimpleStringProperty titleProperty() {
        return this.titleProperty;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
}
