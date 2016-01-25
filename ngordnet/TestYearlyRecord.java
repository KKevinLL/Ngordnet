package ngordnet;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Collection;
import java.util.HashMap;

public class TestYearlyRecord {

    @Test
    public void testConstructorAndPutAndCount() {
        YearlyRecord yr = new YearlyRecord();
        yr.put("quayside", 95);
        yr.put("surrogate", 340);
        yr.put("merchantman", 181);
        HashMap<String, Integer> tm = new HashMap<>();
        tm.put("quayside", 95);
        tm.put("surrogate", 340);
        tm.put("merchantman", 181);
        YearlyRecord yr2 = new YearlyRecord(tm);
        assertEquals(95, yr.count("quayside"));
        assertEquals(95, yr2.count("quayside"));
        assertEquals(340, yr.count("surrogate"));
        assertEquals(340, yr2.count("surrogate"));
        assertEquals(181, yr.count("merchantman"));
        assertEquals(181, yr2.count("merchantman"));
    }

    @Test
    public void testSize() {
        YearlyRecord yr = new YearlyRecord();
        yr.put("quayside", 95);
        yr.put("surrogate", 340);
        yr.put("merchantman", 181);
        assertEquals(3, yr.size());
    }

    @Test
    public void testWordsAndCountsAndRanks() {
        YearlyRecord yr = new YearlyRecord();
        yr.put("quayside", 95);
        yr.put("surrogate", 340);
        yr.put("merchantman", 181);
        Collection<String> words = yr.words();
        Collection<Number> counts = yr.counts();
        assertEquals(1, yr.rank("surrogate"));
        assertEquals(3, yr.rank("quayside"));
        assertEquals(3, words.size());
        assertEquals(true, words.contains("merchantman"));
        assertEquals(true, words.contains("quayside"));
        assertEquals(false, words.contains("mercury"));
        assertEquals(true, counts.contains(95));
        assertEquals(false, counts.contains(195));
        assertEquals(true, counts.contains(340));
    }

    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(WordNet.class);
    }
}
