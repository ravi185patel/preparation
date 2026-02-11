package career.datastructure.dp.mcmandpartitiondp;

import java.util.Arrays;

public class PalindromePartitionII {
    public static void main(String[] args) {
        System.out.println(noOfPartition("bababcbadcede"));
        System.out.println(noOfPartition("aab"));
    }

    public static int noOfPartition(String word){
//        return solve(0,word.length()-1,word)-1;
        return solveDp(word);
    }

    public static int solve(int index, int j,String word) {
        if(index >= word.length()) return 0;

        int count=Integer.MAX_VALUE;
        for(int i=index;i<=j;i++){
            if(isPalindrome(word,index,i)){
                count= Math.min(count,1 + solve(i+1,j,word));
            }
        }
        return count;
    }

    public static int solveDp(String word) {
        int length = word.length();
        int dp[]=new int[length+1];
        dp[length]=-1;
        for(int i=length-1;i>=0;i--){
            int cost = Integer.MAX_VALUE;
            for(int j=i;j<length;j++){
                if(isPalindrome(word,i,j)){
                    cost = Math.min(cost,1 + dp[j+1]); // 1 + prev value ( not current)
                }
            }
            dp[i] = cost;
        }

        return dp[0];
    }

    public static boolean isPalindrome(String word,int start,int end){
        while(start < end){
            if(word.charAt(start) != word.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
