package ngordnet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.introcs.In;

public class NGramMap {

    private Map<String, HashMap<Integer, Integer>> data;
    private TimeSeries<Long> totalCounts;

    /** Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME. */
    public NGramMap(String wordsFilename, String countsFilename) {
        data = new HashMap<>();
        totalCounts = new TimeSeries<>();
        HashMap<Integer, Integer> yearSingleCount = new HashMap<>();
        In wF = new In(wordsFilename);
        In cF = new In(countsFilename);
        while (!wF.isEmpty()) {
            String line = wF.readLine();
            String[] word = line.split("\t");
            if (!data.containsKey(word[0])) {
                yearSingleCount = new HashMap<>();
                yearSingleCount.put(Integer.parseInt(word[1]),
                        Integer.parseInt(word[2]));
                data.put(word[0], yearSingleCount);
            } else {
                yearSingleCount.put(Integer.parseInt(word[1]),
                        Integer.parseInt(word[2]));
            }
        }
        while (!cF.isEmpty()) {
            String line = cF.readLine();
            String[] elem = line.split(",");
            totalCounts.put(Integer.parseInt(elem[0]), Long.parseLong(elem[1]));
        }
    }

    /**
     * Returns the absolute count of WORD in the given YEAR. If the word did not
     * appear in the given year, return 0.
     */
    public int countInYear(String word, int year) {
        if (!data.containsKey(word)) {
            return 0;
        } else if (data.get(word).containsKey(year)) {
            return data.get(word).get(year);
        }
        return 0;
    }

    /** Returns a defensive copy of the YearlyRecord of WORD. */
    public YearlyRecord getRecord(int year) {
        YearlyRecord yR = new YearlyRecord();
        for (String key : data.keySet()) {
            if (data.get(key).containsKey(year)) {
                yR.put(key, data.get(key).get(year));
            }
        }
        return yR;
    }

    /** Returns the total number of words recorded in all volumes. */
    public TimeSeries<Long> totalCountHistory() {
        return totalCounts;
    }

    /** Provides the history of WORD between STARTYEAR and ENDYEAR. */
    public TimeSeries<Integer> countHistory(String word, int startYear,
            int endYear) {
        if (!data.containsKey(word)) {
            return new TimeSeries<Integer>();
        }
        TimeSeries<Integer> cH = new TimeSeries<>();
        for (Integer year : data.get(word).keySet()) {
            if (year >= startYear && year <= endYear) {
                cH.put(year, data.get(word).get(year));
            }
        }
        return cH;
    }

    /** Provides a defensive copy of the history of WORD. */
    public TimeSeries<Integer> countHistory(String word) {
        TimeSeries<Integer> wordCountH = new TimeSeries<>();
        for (Integer year : data.get(word).keySet()) {
            wordCountH.put(year, data.get(word).get(year));
        }
        return wordCountH;
    }

    /** Provides the relative frequency of WORD between STARTYEAR and ENDYEAR. */
    public TimeSeries<Double> weightHistory(String word, int startYear,
            int endYear) {
        return countHistory(word, startYear, endYear).dividedBy(totalCounts);
    }

    /** Provides the relative frequency of WORD. */
    public TimeSeries<Double> weightHistory(String word) {
        return countHistory(word).dividedBy(totalCounts);
    }

    /**
     * Provides the summed relative frequency of all WORDS between STARTYEAR and
     * ENDYEAR.
     */
    public TimeSeries<Double> summedWeightHistory(Collection<String> words,
            int startYear, int endYear) {
        TimeSeries<Double> wH = new TimeSeries<>();
        for (String w : words) {
            wH = wH.plus(weightHistory(w, startYear, endYear));
        }
        return wH;
    }

    /** Returns the summed relative frequency of all WORDS. */
    public TimeSeries<Double> summedWeightHistory(Collection<String> words) {
        TimeSeries<Double> wH = new TimeSeries<>();
        for (String w : words) {
            wH = wH.plus(weightHistory(w));
        }
        return wH;
    }

    /**
     * Provides processed history of all words between STARTYEAR and ENDYEAR as
     * processed by YRP.
     */
    public TimeSeries<Double> processedHistory(int startYear, int endYear,
            YearlyRecordProcessor yrp) {
        TimeSeries<Double> result = new TimeSeries<>();
        for (Integer y : totalCounts.keySet()) {
            if (y >= startYear && y <= endYear) {
                result.put(y, yrp.process(this.getRecord(y)));
            }
        }
        return result;
    }

    /** Provides processed history of all words ever as processed by YRP. */
    public TimeSeries<Double> processedHistory(YearlyRecordProcessor yrp) {
        TimeSeries<Double> result = new TimeSeries<>();
        for (Integer y : totalCounts.keySet()) {
            result.put(y, yrp.process(this.getRecord(y)));
        }
        return result;
    }
}
