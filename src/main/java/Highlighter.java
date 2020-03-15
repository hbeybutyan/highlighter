import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Highlighter {
    private static final String OPENING_BRAKE = "<b>";
    private static final String CLOSING_BRAKE = "</b>";

    /**
     * Highlights found keywords in the text by surrounding matches with <b></b>
     * @param args - An array which consists of input text as first element and the keywords to search.
     * @return decorated text with highlighted matched keywords
     */
    public static String highlightMatches(String[] args) {
        List<Interval> candidates = getCandidates(args);
        if (candidates.isEmpty()) {
            return null;
        }
        List<Interval> result = filterIntervals(candidates);
        return decorateText(result, args[0]);
    }

    /**
     * Returns length based sorted list of all the matches of searched keywords.
     * Matches are represented as intervals of first and last index in the source text.
     * @param args
     * @return
     */
    private static List<Interval> getCandidates(String[] args) {
        //Here we assume that the input text (args[0]) is not empty as well as at least one of the keywords
        List<Interval> candidates = new ArrayList<Interval>();
        for (int i = 1; i < args.length; ++i) {
            if (args[i].isEmpty()) {
                continue;
            }
            Matcher matcher = Pattern.compile(args[i]).matcher(args[0]);
            while(matcher.find()) {
                //Empty matches are note interesting from perspective of highlighting
                if (matcher.start() == matcher.end()) {
                    continue;
                }
                candidates.add(new Interval(matcher.start(), matcher.end()));
            }
        }
        Collections.sort(candidates);
        return candidates;
    }

    /**
     * Filters intervals based on lengths, i.e. in case of overlapping intervals keeps only the longer one.
     * Empty intervals are also removed.
     * @param candidates - The intervals ( see {@link Interval}) indicating start and end indexes of matched keywords in the processed text
     * @return the list of non overlapped intervals.
     */
    private static List<Interval> filterIntervals(List<Interval> candidates) {
        List<Interval> result = new ArrayList<Interval>();
        boolean add = true;
        for (int i = 0; i < candidates.size(); ++i) {
            Interval candidate = candidates.get(i);
            for (Interval interval : result) {
                if (interval.overlaps(candidate)) {
                    add = false;
                    break;
                }
                add = true;
            }
            if (add) {
                result.add(candidate);
            }
        }
        return result;
    }

    /**
     * Adds the decorating strings to original one.
     * @param intervals - the intervals which must be surrounded by decorating strings
     * @param text text which mst be decorated/highlighted
     * @return
     */
    private static String decorateText(List<Interval> intervals, String text) {
        StringBuilder sb = new StringBuilder();
        //Here we need to sort the elements bases on the appearance in the text
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.getStart(), o2.getStart());
            }
        });
        int last = 0;
        sb.append(text, 0, intervals.get(0).getStart());
        for (Interval interval : intervals) {
            sb.append(OPENING_BRAKE);
            last = interval.getEnd();
            sb.append(text, interval.getStart(), last);
            sb.append(CLOSING_BRAKE);
        }
        sb.append(text, last, text.length());
        return sb.toString();
    }
}
