package career.website.leetcode.string.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SimplifiedFraction {
    public static void main(String[] args) {

        List<String> res = simplifiedFractions(3);
        System.out.println(res);
        res = simplifiedFractions(4);
        System.out.println(res);

    }

    public static List<String> simplifiedFractions(int n) {
        List<String> res = new ArrayList<>();
        fractions(n,res);
        return res;
    }

    public static void fractions(int n,List<String> res) {
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (gcd(j, i) == 1) {
                    res.add(j + "/" + i);
                }
            }
        }
    }

    private static int gcd(int x, int y){
        return x==0 ? y : gcd(y%x,x);
    }


}
