package career.datastructure.recurssion;

import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) {
        StringBuffer s = new StringBuffer("a");
        TreeSet t = new TreeSet();
        t.add(s);
        StringBuffer s1 = new StringBuffer("a");
        t.add(s1);
//        t.add(1);
//        t.add(1);
        t.add("1");
        System.out.print(t);
    }
}
