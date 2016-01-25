package ngordnet;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Collection;

public class TestTimeSeries {

    @Test
    public void testConstructor() {
        TimeSeries<Double> ts = new TimeSeries<Double>();
        ts.put(1992, 3.6);
        ts.put(1993, 9.2);
        ts.put(1994, 15.2);
        ts.put(1995, 16.1);
        ts.put(1996, -15.7);
        TimeSeries<Double> ts2 = new TimeSeries<Double>(ts, 1993, 1995);
        assertEquals(ts.get(1993), ts2.get(1993));
        assertEquals(ts.get(1994), ts2.get(1994));
        assertEquals(ts.get(1995), ts2.get(1995));
        assertEquals(false, ts2.containsKey(1992));
        assertEquals(false, ts2.containsKey(1996));
    }

    @Test
    public void testYearAndData() {
        TimeSeries<Double> ts = new TimeSeries<Double>();
        ts.put(1992, 3.6);
        ts.put(1993, 9.2);
        ts.put(1994, 15.2);
        ts.put(1995, 16.1);
        ts.put(1996, -15.7);
        Collection<Number> years = ts.years();
        Collection<Number> data = ts.data();
        assertEquals(5, years.size());
        assertEquals(true, years.contains(1992));
        assertEquals(true, years.contains(1993));
        assertEquals(true, years.contains(1994));
        assertEquals(5, data.size());
        assertEquals(true, data.contains(15.2));
        assertEquals(true, data.contains(16.1));
        assertEquals(true, data.contains(-15.7));
    }

    @Test
    public void testPlusAndDivideBy() {
        TimeSeries<Double> ts = new TimeSeries<Double>();
        ts.put(1992, 3.6);
        ts.put(1993, 9.2);
        ts.put(1994, 15.2);
        ts.put(1995, 16.1);
        ts.put(1996, -15.7);
        TimeSeries<Integer> ts2 = new TimeSeries<Integer>();
        ts2.put(1991, 10);
        ts2.put(1992, -5);
        ts2.put(1993, 1);
        TimeSeries<Double> tSum = ts.plus(ts2);
        assertEquals(10, tSum.get(1991), 1e-5);
        assertEquals(-1.4, tSum.get(1992), 1e-5);

        TimeSeries<Double> ts3 = new TimeSeries<Double>();
        ts3.put(1991, 5.0);
        ts3.put(1992, 1.0);
        ts3.put(1993, 100.0);

        TimeSeries<Double> tQuotient = ts2.dividedBy(ts3);
        assertEquals(2.0, tQuotient.get(1991), 1e-5);
    }

    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TimeSeries.class);
    }
}
