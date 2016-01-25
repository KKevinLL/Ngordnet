package ngordnet;

public class WordLengthProcessor implements YearlyRecordProcessor {
    @Override
    public double process(YearlyRecord yearlyRecord) {
        double totalvalue = 0;
        double totalcount = 0;
        for (String y : yearlyRecord.words()) {
            totalvalue += y.length() * yearlyRecord.count(y);
            totalcount += yearlyRecord.count(y);
        }
        return totalvalue / totalcount;
    }
}
