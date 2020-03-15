import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    private static final String USAGE_STRING =
            "usage: App input_text keyword_1 [keyword_2 ... ]\n" +
                    " input_text: the source text in which to highlight the matched keywords\n" +
                    " keyword_i:  the keyword to search\n";

    public static void main(String[] args) {
        String errorMsg = validateInput(args);
        if (errorMsg != null) {
            System.out.println(errorMsg + USAGE_STRING);
            return;
        }
        String highlightedText = Highlighter.highlightMatches(args);
        if (highlightedText == null) {
            System.out.println("No matching keywords were found.");

        } else {
            System.out.println(highlightedText);
        }
    }

    private static String validateInput(String[] args) {
        if (args.length < 2) {
            return "Please specify the input text and the keywords to search.\n";
        }

        if (args[0].isEmpty()) {
            return "Input text cannot be empty.\n";
        }

        for (int i = 1; i < args.length; ++i) {
            if (!args[i].isEmpty()) {
                //Return if at least one keyword is not empty
                return null;
            }
        }
        return "Please specify at least one keyword to search for.\n";
    }
}