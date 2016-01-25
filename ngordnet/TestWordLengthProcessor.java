package ngordnet;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestWordLengthProcessor {

    @Test
    public void testProcess() {
        YearlyRecord yr = new YearlyRecord();
        yr.put("sheep", 100);
        yr.put("dog", 300);
        WordLengthProcessor wlp = new WordLengthProcessor();
        assertEquals(3.5, wlp.process(yr), 1e-5);
    }

    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(WordLengthProcessor.class);
    }
}
