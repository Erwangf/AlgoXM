package controller;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;
import model.Frequency;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Contrôleur en charge du Nuage de mot
 */
public class WordCloudController {

    private ArticleIndex index;
    private List<Frequency> freq;

    public WordCloudController(ArticleIndex index) {
        this.index = index;
    }

    public void refreshFreq(int nb) {
        try {
            freq = index.getTopFreq(nb);
            System.out.println("reloaded freqs !");
        } catch (IOException e) {
            freq = new ArrayList<>();
            e.printStackTrace();
        }
    }

    public List<Frequency> getTopFreq(int nb) {
        if (this.freq == null || freq.size() < nb) {
            refreshFreq(nb);
        }
        return this.freq.subList(0,nb);
    }

    private List<WordFrequency> getTopWordFreqArray(int nb) {

        return getTopFreq(nb) //on récupère une liste <Mot,NbOccurence> triée par ordre décroissant en nombre d'occurence
                .stream()  // passage en stream pour modifier la liste
                .map(freq -> new WordFrequency(freq.getWord(), freq.getFrequency())) //convertisseur Map.Entry en WordFrequency
                .collect(Collectors.toCollection(ArrayList::new)); //On récupère les données, et on les stoque dans une collection, de constructeur ArrayList
    }


    public BufferedImage getWordCloudImage(int nb) {
        final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        final List<WordFrequency> wordFrequencies = frequencyAnalyzer.loadWordFrequencies(getTopWordFreqArray(nb));
        final Dimension dimension = new Dimension(600, 600);
        final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        wordCloud.setPadding(2);
        wordCloud.setBackgroundColor(new Color(0xF4F4F4));
        wordCloud.setBackground(new CircleBackground(300));
        wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1), new Color(0x40C5F1), new Color(0x40D3F1), new Color(0xAF18BB)));
        wordCloud.setFontScalar(new SqrtFontScalar(10, 80));
        wordCloud.build(wordFrequencies);
        return wordCloud.getBufferedImage();
    }


}
