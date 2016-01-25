package ngordnet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class YearlyRecord {

    private HashMap<String, Integer> rankMap;
    private TreeMap<Integer, ArrayList<String>> countMap;
    private HashMap<String, Integer> wordMap;
    private boolean hasput;

    /** Creates a new empty YearlyRecord. */
    public YearlyRecord() {
        rankMap = new HashMap<String, Integer>();
        countMap = new TreeMap<Integer, ArrayList<String>>();
        wordMap = new HashMap<String, Integer>();
        hasput = false;
    }

    /** Creates a YearlyRecord using the given data. */
    public YearlyRecord(HashMap<String, Integer> otherCountMap) {
        YearlyRecord temp = new YearlyRecord();
        for (String k : otherCountMap.keySet()) {
            temp.put(k, otherCountMap.get(k));
        }
        wordMap = temp.wordMap;
        countMap = temp.countMap;
        rankMap = temp.rankMap;
        hasput = true;
    }

    /** Returns the number of times WORD appeared in this year. */
    public int count(String word) {
        return wordMap.get(word);
    }

    /** Records that WORD occurred COUNT times in this year. */
    public void put(String word, int count) {
        if (wordMap.containsKey(word)) {
            int saved = wordMap.get(word);
            if (countMap.get(saved).size() == 1) {
                countMap.remove(saved);
            } else {
                countMap.get(saved).remove(word);
            }
        }
        wordMap.put(word, count);
        ArrayList<String> value = new ArrayList<>();
        if (countMap.containsKey(count)) {
            countMap.get(count).add(word);
        } else {
            value.add(word);
            countMap.put(count, value);
        }
        hasput = true;
    }

    /** Returns the number of words recorded this year. */
    public int size() {
        return wordMap.size();
    }

    /** Returns all words in ascending order of count. */
    public Collection<String> words() {
        Collection<String> countList = new ArrayList<String>();
        for (Integer k : countMap.keySet()) {
            countList.addAll(countMap.get(k));
        }
        return countList;
    }

    /** Returns all counts in ascending order of count. */
    public Collection<Number> counts() {
        Collection<Number> countList = new TreeSet<Number>();
        for (Integer k : countMap.keySet()) {
            countList.add(k);
        }
        return countList;
    }

    /** Returns rank of WORD. Most common word is rank 1. */
    public int rank(String word) {
        if (!hasput) {
            return rankMap.get(word);
        } else {
            int rank = wordMap.size();
            for (Integer k : countMap.keySet()) {
                for (String w : countMap.get(k)) {
                    rankMap.put(w, rank);
                    rank -= 1;
                }
            }
            hasput = false;
            return rankMap.get(word);
        }
    }
}
