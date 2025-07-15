package career.sixmonthssep.leecode.leetcodecomp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PartitionString {
    public static void main(String[] args) {
     System.out.println(partitionString("abbccccd"));
     System.out.println(partitionString("aaaa"));
     System.out.println(partitionString("a"));
     System.out.println(partitionString("abcd"));


    }
    public static List<String> partitionString(String s) {
        Set<String> set=new HashSet<>();
        List<String> resSet=new ArrayList<>();
        StringBuilder sb= new StringBuilder();
        for(int i=0;i<s.length();i++){
            sb.append(s.charAt(i));
            if(set.contains(sb.toString())){
                continue;
            }
            set.add(sb.toString());
            resSet.add(sb.toString());
            sb.setLength(0);
        }

        return resSet;
    }
}
