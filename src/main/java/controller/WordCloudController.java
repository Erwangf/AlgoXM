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
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Contrôleur en charge du Nuage de mot
 */
public class WordCloudController {

    private ArticleIndex index;
    private List<Map.Entry<String, Integer>> freq;

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

    private List<Map.Entry<String, Integer>> getTopFreq(int nb) {
        if (this.freq == null || freq.size() < nb) {
            refreshFreq(nb);
        }
        return this.freq
                .stream() // passage en stream pour modifier la liste
                .sorted((o1, o2) -> -(o2.getValue() - o1.getValue())) //tri des fréquences par nombre d'occurences, décroissant ( grace au - )
                .limit(nb) // on ne récupère que les nb premières fréquences
                .collect(Collectors.toList());
    }

    private List<WordFrequency> getTopWordFreqArray(int nb) {

        return getTopFreq(nb) //on récupère une liste <Mot,NbOccurence> triée par ordre décroissant en nombre d'occurence
                .stream()  // passage en stream pour modifier la liste
                .map(freq -> new WordFrequency(freq.getKey(), freq.getValue())) //convertisseur Map.Entry en WordFrequency
                .collect(Collectors.toCollection(ArrayList::new)); //On récupère les données, et on les stoque dans une collection, de constructeur ArrayList
    }

    public HashMap<String, Integer> getTopFrequencies(int nb) {


        HashMap<String, Integer> result = new HashMap<>();
        for (Map.Entry<String, Integer> entry : getTopFreq(nb)) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;

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
        wordCloud.setFontScalar(new SqrtFontScalar(10, 40));
        wordCloud.build(wordFrequencies);
        return wordCloud.getBufferedImage();
    }


}
