package career.datastructure.dp.dponlongestIncreasingSubsequence;

import java.util.*;
import java.util.stream.Collectors;

public class LongestStringChain {
    public static void main(String[] args) {
        System.out.println(LSC(new String[]{"a", "ab", "abc", "abcd", "abcde"}));
        System.out.println(LSC(new String[]{"dog", "dogs", "dots", "dot", "d", "do"}));
        System.out.println(LSC(new String[]{"m","nm","mmm"}));
    }
    public static int LSC(String[]words){
        return solveDp(words,words.length);
    }

    public static int solveDp(String[] arr,int n){
        Arrays.sort(arr,(s1,s2)-> s1.length() - s2.length());
           System.out.println(Arrays.toString(arr));
        int res[]=new int[n+1];
        Arrays.fill(res, 1);
        int maxi=0;
        for(int ind=0;ind<n;ind++){
            for(int prevInd=0;prevInd<ind;prevInd++){
                if( compare(arr[ind],arr[prevInd] )
                        && 1+res[prevInd] > res[ind]){
                    res[ind]=1+res[prevInd];
                }
            }
            if(res[ind] > maxi){
                maxi=res[ind];
            }
        }
//           System.out.println(Arrays.toString(res));
        return maxi;
    }

    public static boolean compare(String src,String des){
        // System.out.println(src+" <> "+des);
        if(src.length()!=des.length()+1)
            return false;

        int first=0,second=0;
        while(first < src.length()){
            if(second < des.length() && src.charAt(first) == des.charAt(second)){
                second++;
            }
            first++;
        }
        //  System.out.println(first+" <> "+second);
        return second==des.length();
//          if(first==src.length() && second==des.length())
//              return true;
//          else
//              return false;
    }

    public static int LSCMap(String words[]){
        Arrays.sort(words,(s1,s2) ->{
            int len = s1.length() - s2.length();
            if(len == 0){
                return s1.compareTo(s2);
            }
            return len;
        });

        Map<String,Integer> dp = new HashMap<>();
        int res=0;
        for(String word:words){
            int best =0;
            for(int i=0;i<word.length();i++){
                String prev = word.substring(0,i) + word.substring(i+1);
                best = Math.max(best,dp.getOrDefault(prev,0)+1);
            }
            dp.put(word,best);
            res = Math.max(res,best);
        }

        return res;
    }
}
