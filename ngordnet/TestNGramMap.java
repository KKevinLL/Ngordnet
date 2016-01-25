package ngordnet;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestNGramMap {

    @Test
    public void testConstructorAndCountInYear() {
        NGramMap ngm = new NGramMap("./ngrams/words_that_start_with_q.csv",
                "./ngrams/total_counts.csv");
        assertEquals(139, ngm.countInYear("quantity", 1736));
        assertEquals(0, ngm.countInYear("apple", 1890));
        assertEquals(103776, ngm.countInYear("quo", 2005));
    }

    @Test
    public void testCountAndGetRecord() {
        NGramMap ngm = new NGramMap("./ngrams/words_that_start_with_q.csv",
                "./ngrams/total_counts.csv");
        YearlyRecord yr = ngm.getRecord(1736);
        assertEquals(139, yr.count("quantity"));
    }

    @Test
    public void testCountHistory() {
        NGramMap ngm = new NGramMap("./ngrams/words_that_start_with_q.csv",
                "./ngrams/total_counts.csv");
        TimeSeries<Integer> countHistory = ngm.countHistory("quantity");
        assertEquals(139, countHistory.get(1736), 1e-5);
        TimeSeries<Long> totalCountHistory = ngm.totalCountHistory();
        assertEquals(8049773, totalCountHistory.get(1736), 1e-5);
    }

    @Test
    public void testWeightHistory() {
        NGramMap ngm = new NGramMap("./ngrams/words_that_start_with_q.csv",
                "./ngrams/total_counts.csv");
        TimeSeries<Double> weightHistory = ngm.weightHistory("quantity");
        assertEquals(1.7267e-5, weightHistory.get(1736), 1e-5);
        ArrayList<String> words = new ArrayList<String>();
        words.add("quantity");
        words.add("quality");
        TimeSeries<Double> sum = ngm.summedWeightHistory(words);
        assertEquals(3.875e-5, sum.get(1736), 1e-5);
    }

    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(NGramMap.class);
    }
}
