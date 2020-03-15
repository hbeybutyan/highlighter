/**
 * Class representing the substrings as intervals, i.e. pair of start and end indices of substring.
 */
public class Interval implements Comparable<Interval> {
    private int start;
    private int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int compareTo(Interval interval) {
        return Integer.compare(interval.end - interval.start, this.end - this.start);
    }

    public boolean overlaps(Interval interval) {
        if (this.start <= interval.start) {
            return interval.start < this.end;
        } else {
            return interval.end > this.start;
        }
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
