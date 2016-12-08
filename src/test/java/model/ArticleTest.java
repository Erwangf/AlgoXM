package model;

import org.junit.Assert;
import org.junit.Test;

public class ArticleTest {

    @Test
    public void emptyArticleIsFormattedCorrect() throws Exception {
        /*
        Dans ce test, un article est créé sans contenu, et les getters ne doivent pas lever d'erreur.
        Il existe deux types de getters : les getters normaux, et les getters "formatés", qui renvoient toujours une
        chaine de caractères.

         */
        //
        Article a = new Article();
        Assert.assertEquals("Titre vide => titre= '-'", "-", a.getFormattedTitle());
        Assert.assertEquals("Auteur vide => auteur = 'Auteur inconnu'", "Auteur inconnu", a.getFormattedAuthor());
        Assert.assertEquals("Description vide => description= ''", "", a.getFormattedDescription());
        Assert.assertEquals("RSS vide => RSS = ''", "", a.getFormattedRss());
        Assert.assertEquals("Date vide => date= null'", "Date inconnue", a.getFormattedDate());
        Assert.assertEquals("URL vide => URL = null'", "Pas de lien", a.getFormattedLink());
        Assert.assertEquals("Inter vide => Inter=0", 0, a.getFormattedInter());
    }

}