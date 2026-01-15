package career.datastructure.recurssion.pattern.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BraceExpansion {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(expand("{a,b}c{d,e}f")));
    }
    static List<List<Character>> allOptions = new ArrayList<>();

    static void storeAllOptions(String s) {
        for (int pos = 0; pos < s.length(); pos++) {
            List<Character> currOptions = new ArrayList<>();

            // If the first character is not '{', it means a single character
            if (s.charAt(pos) != '{') {
                currOptions.add(s.charAt(pos));
            } else {
                // Store all the characters between '{' and '}'
                while (s.charAt(pos) != '}') {
                    if (s.charAt(pos) >= 'a' && s.charAt(pos) <= 'z') {
                        currOptions.add(s.charAt(pos));
                    }
                    pos++;
                }
                // Sort the list
                Collections.sort(currOptions);
            }

            allOptions.add(currOptions);
        }
    }

    static  void generateWords(StringBuilder currString, List<String> expandedWords) {
        // If the currString is complete, we can store and return
        if (currString.length() == allOptions.size()) {
            expandedWords.add(currString.toString());
            return;
        }

        // Fetch the options for the current index
//        List<Character> currOptions = allOptions.get(currString.length());

        // Add the character and go into recursion
        for (char c : allOptions.get(currString.length())) {
            currString.append(c);
            generateWords(currString, expandedWords);
            // Backtrack to previous state
            currString.deleteCharAt(currString.length() - 1);
        }
    }

    public static String[] expand(String s) {
        // Store the character options for different indices
        storeAllOptions(s);
        System.out.println(allOptions);

        List<String> expandedWords = new ArrayList<>();
        generateWords(new StringBuilder(), expandedWords);
        return expandedWords.toArray(new String[0]);
    }
}
