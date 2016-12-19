package model;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.TextField;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Erwan on 19/12/2016.
 */
public class Article {

    private Document doc;

    public Article(String title, String description, Date date, String rss, String author, URL link){
        doc = new Document();

        doc.add(new TextField("title",title, Field.Store.YES));
        doc.add(new TextField("description", description, Field.Store.YES));
        doc.add(new TextField("rss", rss, Field.Store.YES));
        doc.add(new TextField("author", author, Field.Store.YES));

        //date stockée en millisecondes (getTime)
        doc.add(new LongPoint("date", date.getTime()));

        //link stocké sous forme de texte
        doc.add(new TextField("link", link.toString(), Field.Store.YES));
    }

    // GETTERS & SETTERS

    public String getTitle() {
        return doc.get("title");
    }

    public void setTitle(String title) {
        doc.removeField("title");
        doc.add(new TextField("title",title, Field.Store.YES));
    }

    public String getDescription() {
        return doc.get("description");
    }

    public void setDescription(String description) {
        doc.removeField("description");
        doc.add(new TextField("description",description, Field.Store.YES));
    }

    public Date getDate() {
        return new Date(Long.parseLong(doc.get("date")));
    }

    public void setDate(Date date) {
        doc.removeField("date");
        doc.add(new LongPoint("date",date.getTime()));
    }

    public String getRss() {
        return doc.get("rss");
    }

    public void setRss(String rss) {
        doc.removeField("rss");
        doc.add(new TextField("rss",rss, Field.Store.YES));
    }

    public String getAuthor() {
        return doc.get("author");
    }

    public void setAuthor(String author) {
        doc.removeField("author");
        doc.add(new TextField("author",author, Field.Store.YES));
    }

    public URL getLink() {
        URL result = null;
        try {
            result =  new URL(doc.get("link"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void setLink(URL link) {
        doc.removeField("link");
        doc.add(new TextField("link",link.toString(), Field.Store.YES));
    }

    public Document getDocument(){
        return doc;
    }

    public static ArrayList<Article> getDefaultArticles() {
        ArrayList<Article> l = new ArrayList<Article>();

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
}
