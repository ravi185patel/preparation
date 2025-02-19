package career.sixmonthssep.dp.subsequence.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://www.geeksforgeeks.org/problems/printing-longest-increasing-subsequence/1
 */
public class PrintLongestIncreasingSubSequence {
    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15}));
        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println(lengthOfLIS(new int[]{0,1,0,3,2,3}));
        System.out.println(lengthOfLIS(new int[]{7,7,7,7,7,7,7}));
    }
    public static ArrayList<Integer> lengthOfLIS(int[] nums) {
        return lis(nums);
    }

    private static ArrayList<Integer> lis(int[]nums){

        int parent[]=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            parent[i]=i;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int index = 1; index < nums.length; index++) {
            for (int prevInd = 0; prevInd < index; prevInd++) {
                if (nums[index] > nums[prevInd] && dp[prevInd] + 1 > dp[index]) {
                    dp[index] = dp[prevInd] + 1;
                    parent[index] = prevInd;
                }
            }
        }

        int ans = -1;
        int lastIndex =-1;

        for(int i=0; i<=nums.length-1; i++){
            if(dp[i] > ans){
                ans = dp[i];
                lastIndex = i;
            }
        }

        ArrayList<Integer> temp=new ArrayList<>();
        temp.add(nums[lastIndex]);

        while(parent[lastIndex] != lastIndex){ // till not reach the initialization value
            lastIndex = parent[lastIndex];
            temp.add(0,nums[lastIndex]);
        }
        return temp;
    }

    private static int lisGenSeq(int[]nums){
        List<Integer> lst = new ArrayList<>();
        lst.add(nums[0]);

        for(int i=1;i<nums.length;i++){
            int num = nums[i];
            if(num > lst.get(lst.size()-1)){
                lst.add(num);
            }else{
                int j=0;
                while(num > lst.get(j)){
                    j+=1;
                }
                lst.set(j,num);
            }
        }

        return lst.size();
    }


    private static int lisGenSeqBs(int[]nums){
        List<Integer> lst = new ArrayList<>();
        lst.add(nums[0]);

        for(int i=1;i<nums.length;i++){
            int num = nums[i];
            if(num > lst.get(lst.size()-1)){
                lst.add(num);
            }else{
                int j = binarySearch(lst, num);
                lst.set(j,num);
            }
        }

        return lst.size();
    }

    private static int binarySearch(List<Integer> sub, int num) {
        int left = 0;
        int right = sub.size() - 1;
        int mid = (left + right) / 2;

        while (left < right) {
            mid = (left + right) / 2;
            if (sub.get(mid) == num) {
                return mid;
            }

            if (sub.get(mid) < num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
