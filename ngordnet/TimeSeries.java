package ngordnet;

import java.util.Collection;
import java.util.TreeMap;

public class TimeSeries<T extends Number> extends TreeMap<Integer, T> {
    /** Constructs a new empty TimeSeries. */
    public TimeSeries() {
        super();
    }

    /**
     * Returns the years in which this time series is valid. Doesn't really need
     * to be a NavigableSet. This is a private method and you don't have to
     * implement it if you don't want to.
     */
    // private NavigableSet<Integer> validYears(int startYear, int endYear)

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR. inclusive
     * of both end points.
     */
    public TimeSeries(TimeSeries<T> ts, int startYear, int endYear) {
        for (Integer k : ts.keySet()) {
            if (k >= startYear && k <= endYear) {
                this.put(k, ts.get(k));
            }
        }
    }

    /** Creates a copy of TS. */
    public TimeSeries(TimeSeries<T> ts) {
        for (Integer k : ts.keySet()) {
            this.put(k, ts.get(k));
        }
    }

    /**
     * Returns the quotient of this time series divided by the relevant value in
     * ts. If ts is missing a key in this time series, return an
     * IllegalArgumentException.
     */
    public TimeSeries<Double> dividedBy(TimeSeries<? extends Number> ts) {
        TimeSeries<Double> result = new TimeSeries<Double>();
        for (Integer k : this.keySet()) {
            if (ts.containsKey(k)) {
                result.put(k, (this.get(k).doubleValue() / ts.get(k)
                        .doubleValue()));
            } else {
                throw new IllegalArgumentException();
            }
        }
        return result;
    }

    /**
     * Returns the sum of this time series with the given ts. The result is a a
     * Double time series (for simplicity).
     */
    public TimeSeries<Double> plus(TimeSeries<? extends Number> ts) {
        TimeSeries<Double> result = new TimeSeries<Double>();
        for (Integer k : this.keySet()) {
            if (ts.containsKey(k)) {
                result.put(k, (this.get(k).doubleValue() + ts.get(k)
                        .doubleValue()));
            } else {
                result.put(k, this.get(k).doubleValue());
            }
        }
        for (Integer j : ts.keySet()) {
            if (!this.containsKey(j)) {
                result.put(j, ts.get(j).doubleValue());
            }
        }
        return result;
    }

    /** Returns all years for this time series (in any order). */
    public Collection<Number> years() {
        return (Collection) this.keySet();
    }

    /** Returns all data for this time series (in any order). */
    public Collection<Number> data() {
        return (Collection<Number>) values();
    }
}
