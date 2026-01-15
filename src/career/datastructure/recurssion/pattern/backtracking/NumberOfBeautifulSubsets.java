package career.datastructure.recurssion.pattern.backtracking;

import java.util.*;

public class NumberOfBeautifulSubsets {
    public static void main(String[] args) {
        System.out.println(beautifulSubsets(new int[]{2,4,6},2));
//        System.out.println(beautifulSubsets(new int[]{1},1));
//        System.out.println(beautifulSubsets(new int[]{1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000},1));
//        System.out.println(beautifulSubsets(new int[]{20,14,22,1,4,11,21,19,29,25,12,18,9,15,23,6,27,16,26,5},1));
        System.out.println(beautifulSubsets(new int[]{10,4,5,7,2,1},3));
    }
    public static int beautifulSubsets(int[] nums, int k) {
        Arrays.sort(nums);
        /*List<List<Integer>> combination = new ArrayList<>();
        solveLst(0,nums,k,combination,new ArrayList<>());
        System.out.println(combination);
        return combination.size();*/
//        return solveIt(0,nums,k);

        freq = new HashMap<Integer, Integer>();
        return beautifulSubsets(nums, k, 0) - 1; // -1 for empty subset
    }

    public static void solveLst(int index,int []nums,int k,List<List<Integer>> combination,List<Integer> lst){

        if(lst.size() >=1) {
            combination.add(new ArrayList<>(lst));
        }

        for(int i=index;i<nums.length;i++){
            if(lst.contains(nums[i]+k) || lst.contains(nums[i]-k)) continue;
            lst.add(nums[i]);
            solveLst(i+1,nums,k,combination,lst);
            lst.remove(lst.size()-1);
        }
    }

    public static int solveIt(int index,int []nums,int k){
        int count=0;
        int dp[]=new int[nums.length];
        Arrays.fill(dp,1);
        for(int i=1;i<nums.length;i++){
            for(int j=i-1;j>=0;j--){
                if(nums[i] - nums[j] == k)continue;
//                System.out.println(nums[i]+" "+nums[j]+" "+(nums[j] - nums[i]));
                dp[i]++;
            }
        }
        System.out.println(Arrays.toString(dp));
        return Arrays.stream(dp).sum();
    }

    static int count = 0;

    private static HashMap<Integer, Integer> freq;

    private static int beautifulSubsets(int[] nums, int k, int i) {
        if (i == nums.length) { // base case
            return 1;
        }
        int result = beautifulSubsets(nums, k, i + 1); // nums[i] not taken
        if (!freq.containsKey(nums[i] - k) && !freq.containsKey(nums[i] + k)) { // check if we can take nums[i]
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
            result += beautifulSubsets(nums, k, i + 1); // nums[i] taken
            freq.put(nums[i], freq.get(nums[i]) - 1);
            if (freq.get(nums[i]) == 0) {
                freq.remove(nums[i]);
            }
        }
        return result;
    }
}
