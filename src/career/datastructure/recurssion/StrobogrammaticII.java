package career.datastructure.recurssion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given n, return all strobogrammatic numbers of length n.
 */
public class StrobogrammaticII {

    public static void main(String[] args) {
        System.out.println(findStrobogrammatic(2));
    }
    public static List<String> findStrobogrammatic(int n) {
        return build(n, n);
    }

    private static List<String> build(int n, int total) {
        if (n == 0) return Arrays.asList("");
        if (n == 1) return Arrays.asList("0", "1", "8");

        List<String> mids = build(n - 2, total);
        List<String> res = new ArrayList<>();

        for (String mid : mids) {
            if (n != total) res.add("0" + mid + "0");
            res.add("1" + mid + "1");
            res.add("6" + mid + "9");
            res.add("8" + mid + "8");
            res.add("9" + mid + "6");
        }
        return res;
    }
}
