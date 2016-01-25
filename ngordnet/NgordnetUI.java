/* Starter code for NgordnetUI (part 7 of the project). Rename this file and 
   remove this comment. */

package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.Stopwatch;

/**
 * Provides a simple user interface for exploring WordNet and NGram data.
 * 
 * @author [Boran Liu]
 */
public class NgordnetUI {
    public static void main(String[] args) {
        Stopwatch sw = new Stopwatch();
        In in = new In("./ngordnet/ngordnetui.config");
        System.out.println("Reading ngordnetui.config...");

        String wordFile = in.readString();
        String countFile = in.readString();
        String synsetFile = in.readString();
        String hyponymFile = in.readString();
        NGramMap wordCount = new NGramMap(wordFile, countFile);
        WordNet wordHypo = new WordNet(synsetFile, hyponymFile);
        YearlyRecordProcessor process = new WordLengthProcessor();
        int start = 0;
        int end = 2000;
        System.out
                .println("\nBased on ngordnetui.config, using the following: "
                        + wordFile + ", " + countFile + ", " + synsetFile
                        + ", and " + hyponymFile + ".");

        System.out
                .println("\nFor tips on implementing NgordnetUI, see ExampleUI.java.");
        System.out.println(sw.elapsedTime());
        while (true) {
            try {
                System.out.print("> ");
                String line = StdIn.readLine();
                String[] rawTokens = line.split(" ");
                String command = rawTokens[0];
                String[] tokens = new String[rawTokens.length - 1];
                System.arraycopy(rawTokens, 1, tokens, 0, rawTokens.length - 1);
                switch (command) {
                case "quit":
                    return;
                case "help":
                    In input = new In("./ngordnet/help.txt");
                    String helpStr = input.readAll();
                    System.out.println(helpStr);
                    break;
                case "range":
                    int startDate = Integer.parseInt(tokens[0]);
                    int endDate = Integer.parseInt(tokens[1]);
                    start = startDate;
                    end = endDate;
                    System.out.println("Start date: " + startDate);
                    System.out.println("End date: " + endDate);
                    break;
                case "count":
                    String word = tokens[0];
                    int year = Integer.parseInt(tokens[1]);
                    System.out.println(wordCount.countInYear(word, year));
                    break;
                case "hyponyms":
                    String w = tokens[0];
                    System.out.println(wordHypo.hyponyms(w));
                    break;
                case "history":
                    Plotter.plotAllWords(wordCount, tokens, start, end);
                    break;
                case "hypohist":
                    Plotter.plotCategoryWeights(wordCount, wordHypo, tokens,
                            start, end);
                    break;
                case "wordlength":
                    Plotter.plotProcessedHistory(wordCount, start, end, process);
                    break;
                case "zipf":
                    Plotter.plotZipfsLaw(wordCount, Integer.parseInt(tokens[0]));
                    break;
                default:
                    System.out.println("Invalid command.");
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.err.println("IllegalArgumentException" + e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("ArrayIndexOutOfBoundsException"
                        + e.getMessage());
            } catch (RuntimeException e) {
                System.err.println("RuntimeException" + e.getMessage());
            }
        }
    }
}
