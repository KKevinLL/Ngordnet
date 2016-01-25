package ngordnet;

import java.util.Set;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestWordNet {

    /* These should all print true. */
    @Test
    public void testisNoun() {
        WordNet wn = new WordNet("./wordnet/synsets11.txt",
                "./wordnet/hyponyms11.txt");
        assertEquals(true, wn.isNoun("jump"));
        assertEquals(true, wn.isNoun("leap"));
        assertEquals(true, wn.isNoun("nasal_decongestant"));
    }

    /*
     * The code below should print the following (maybe not in this order): All
     * nouns: augmentation nasal_decongestant change action actifed
     * antihistamine increase descent parachuting leap demotion jump
     */

    @Test
    public void testNoun() {
        WordNet wn = new WordNet("./wordnet/synsets11.txt",
                "./wordnet/hyponyms11.txt");
        Set<String> allWords = wn.nouns();
        assertEquals(12, allWords.size());
        assertEquals(true, allWords.contains("augmentation"));
        assertEquals(true, allWords.contains("nasal_decongestant"));
        assertEquals(true, allWords.contains("change"));
        assertEquals(true, allWords.contains("action"));
        assertEquals(true, allWords.contains("actifed"));
    }

    /*
     * The code below should print the following (maybe not in this order):
     * Hypnoyms of increase: augmentation increase leap jump
     */

    @Test
    public void testHypnoyms() {
        WordNet wn = new WordNet("./wordnet/synsets11.txt",
                "./wordnet/hyponyms11.txt");
        Set<String> HypnoymsOfIncrease = wn.hyponyms("increase");
        assertEquals(4, HypnoymsOfIncrease.size());
        assertEquals(true, HypnoymsOfIncrease.contains("augmentation"));
        assertEquals(true, HypnoymsOfIncrease.contains("jump"));
        assertEquals(true, HypnoymsOfIncrease.contains("leap"));
        assertEquals(true, HypnoymsOfIncrease.contains("increase"));
    }

    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(WordNet.class);
    }
}
