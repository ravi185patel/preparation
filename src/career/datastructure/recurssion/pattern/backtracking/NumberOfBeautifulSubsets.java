package career.datastructure.recurssion.pattern.backtracking;

import java.util.*;

public class NumberOfBeautifulSubsets {
    public static void main(String[] args) {
//        System.out.println(beautifulSubsets(new int[]{1},1));
//        System.out.println(beautifulSubsets(new int[]{1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000},1));
//        System.out.println(beautifulSubsets(new int[]{20,14,22,1,4,11,21,19,29,25,12,18,9,15,23,6,27,16,26,5},1));

        System.out.println(beautifulSubsets(new int[]{2,4,6},2));
        System.out.println(beautifulSubsets(new int[]{10,4,5,7,2,1},3)); // 23
        System.out.println(beautifulSubsets(new int[]{4,2,5,9,10,3},1)); //23
        System.out.println(beautifulSubsets(new int[]{942,231,247,267,741,320,844,276,578,659,96,697,801,892,752,948,176,92,469,595},43)); //23
    }
    public static int beautifulSubsets(int[] nums, int k) {
        Arrays.sort(nums);
        /*List<List<Integer>> combination = new ArrayList<>();
        solveLst(0,nums,k,combination,new ArrayList<>());
        System.out.println(combination);
        return combination.size();*/
//        return solveIt(0,nums,k);

//        freq = new HashMap<Integer, Integer>();
//        return beautifulSubsets(nums, k, 0) - 1; // -1 for empty subset

        int memo[][]=new int[nums.length+1][1<<nums.length+1];
        for(int m[]:memo){
            Arrays.fill(m,-1);
        }
        // consumer too much memory so use map instead of array
        /*
                    Example
            If: n = 4 Then: index → 4, mask → 2⁴ = 16

            Memo size: 4 × 16 = 64 states
            Memory usage (approx)
            Using Integer (object):
            Each cell ≈ 16 bytes (depends on JVM)
            For n = 15: 15 × 32768 ≈ 491,520 cells ≈ ~8 MB
            For n = 20: 20 × 1,048,576 ≈ 20 million cells ❌ (too large)
         */
//        return solveDfsPickNotPickMask(0,k,nums,memo,0);
        return solveDfsPickNotPickMask(0,k,nums,new HashMap<>(),0);
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

    public static int solveDfsPickNotPick(int index, int k, int nums[], Set<Integer> lst){

        if(index == nums.length) return lst.size()>= 1 ? 1:0;

        int noTake = solveDfsPickNotPick(index+1,k,nums,lst);
        int take=0;
        if(index == 0 || (!(lst.contains(nums[index]-k) || lst.contains(nums[index]+k)))) {
            lst.add(nums[index]);
            take = solveDfsPickNotPick(index + 1, k, nums, lst);
            lst.remove(nums[index]);
        }

        return take + noTake;

    }

    public static int solveDfsPickNotPickMask(int index, int k, int nums[], int memo[][],int mask){

        if(index == nums.length) return mask == 0 ? 0 : 1;

        if (memo[index][mask] != -1) {
            return memo[index][mask];
        }

        int noTake = solveDfsPickNotPickMask(index + 1, k, nums,memo,mask);

        int take = 0;
        boolean valid = true;

        for (int i = 0; i < index; i++) {
            if ((mask & (1 << i)) != 0 &&
                    Math.abs(nums[i] - nums[index]) == k) {
                valid = false;
                break;
            }
        }


        if (valid) {
            take = solveDfsPickNotPickMask(index + 1, k, nums,memo, mask | 1 << index);
        }

        memo[index][mask] = take + noTake;
        return memo[index][mask];

    }

    public static int solveDfsPickNotPickMask(int index, int k, int nums[], Map<Long,Integer> memo,int mask){

        if(index == nums.length) return mask == 0 ? 0 : 1;

        long key = (((long) index) << 32) | (mask & 0xffffffffL);

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int noTake = solveDfsPickNotPickMask(index + 1, k, nums,memo,mask);

        int take = 0;
        boolean valid = true;

        /*

        What each condition means
        1️⃣ (mask & (1 << i)) != 0
        👉 Checks whether index i is already picked
        1 << i → creates a bitmask with only bit i set
        mask & (1 << i):
                != 0 → bit i is ON → nums[i] is already in the subset
                == 0 → bit i is OFF → nums[i] is not chosen
        So this ensures we only compare against chosen elements.

        2️⃣ Math.abs(nums[i] - nums[index]) == k
        👉 Checks the problem constraint
            Compares the current number with an already chosen number
            If their absolute difference is exactly k, the subset is invalid
         */

        for (int i = 0; i < index; i++) {
            if ((mask & (1 << i)) != 0 &&
                    Math.abs(nums[i] - nums[index]) == k) {
                valid = false;
                break;
            }
        }


        if (valid) {
            take = solveDfsPickNotPickMask(index + 1, k, nums,memo, mask | 1 << index);
        }

        int result = take + noTake;
        memo.put(key, result);
        return result;

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
