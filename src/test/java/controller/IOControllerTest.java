package controller;

import model.Article;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;


public class IOControllerTest {


    /**
     * On teste la fonction IOController.importCSV.
     * Cette fonction est sensée renvoyer un ArrayList d'Article
     *
     */
    @Test
    public void testImportOneLineToString(){

        InputStream fileIR = this.getClass().getResourceAsStream("abc.xml");

        ArrayList<Article> result = IOController.importCSV(fileIR);

        Assert.assertNotNull("Le résultat ne devrait pas être null",result);
        Article a = result.get(0);

        //tests sur la première ligne
        Assert.assertNotNull("le premier résultat ne devrait pas être null",a);
        Assert.assertEquals("Titre incorrect","TestTitle",a.getTitle());
        Assert.assertEquals("Description incorrecte","TestDescription",a.getDescription());
        Assert.assertEquals("Date incorrecte et/ou malrestituée","Mon, 20 Jun 2016 05:23:17 -0400",a.getDate().toString());
        Assert.assertEquals("RSS incorrect","TestRSS",a.getRss());
        Assert.assertEquals("Auteur incorrect","TestAuthor",a.getAuthor());
        Assert.assertEquals("URL incorrect","http://test.url.com/",a.getLink().toString());

    }
}
