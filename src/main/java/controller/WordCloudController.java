package controller;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Contrôleur en charge du Nuage de mot
 */
public class WordCloudController {

    private ArticleIndex index;

    public WordCloudController(ArticleIndex index) {
        this.index = index;
    }

    private ArrayList<String> getKeyWordsFromListEntry(List<Map.Entry<String, Integer>> list) {
        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : list) {
            result.add(entry.getKey());
        }
        return result;
    }

    public ArrayList<String> getKeyWords(int nb) {
/*
        //la fonction de franck bug.
        //donc voici un résulat par défault
        ArrayList<String> result = new ArrayList<>();

        Collections.addAll(result, "Morgane", "Princesse", "Fromage", "Emilien", "Dandinou", "Cheesecake", "Crepes", "Nutella", "Fluos", "Bachar", "Cacahuette", "Banane", "Pizza", "France", "Flamby");
        return result;*/
        try {
            return getKeyWordsFromListEntry(index.getTopFreq(nb));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public BufferedImage getWordCloudImage(int nb){
        final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(getKeyWords(nb));
        final Dimension dimension = new Dimension(600, 600);
        final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        wordCloud.setPadding(2);
        wordCloud.setBackgroundColor(new Color(0xF4F4F4));
        wordCloud.setBackground(new CircleBackground(300));
        wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1), new Color(0x40C5F1), new Color(0x40D3F1), new Color(0xAF18BB)));
        wordCloud.setFontScalar(new SqrtFontScalar(10, 40));
        wordCloud.build(wordFrequencies);
        return wordCloud.getBufferedImage();
    }


}
