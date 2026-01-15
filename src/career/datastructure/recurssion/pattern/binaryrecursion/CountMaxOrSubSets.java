package career.datastructure.recurssion.pattern.binaryrecursion;

/*
Input: nums = [3,1]
Output: 2
Explanation: The maximum possible bitwise OR of a subset is 3. There are 2 subsets with a bitwise OR of 3:
- [3]
- [3,1]

Example 2:
Input: nums = [2,2,2]
Output: 7
Explanation: All non-empty subsets of [2,2,2] have a bitwise OR of 2. There are 23 - 1 = 7 total subsets.

 */
public class CountMaxOrSubSets {
    public static void main(String[] args) {

    }
    public int countMaxOrSubsets(int[] nums) {
        // maxOr=0;
        // count=0;
        // solve(0,nums,0);
        // return count;

        int bitWiseOr =0;
        for(int i:nums){
            bitWiseOr |= i;
        }

        // return solveM(0,nums,0,bitWiseOr);
        int memo[][]=new int[nums.length+1][bitWiseOr+1];
        // for(int i[]:memo){
        //     Arrays.fill(i,-1);
        // }
        // return solveMMemo(0,nums,0,bitWiseOr,memo);
        // return solveMItr(0,nums,bitWiseOr,memo);
        return solveMBitMaskDp(0,nums,bitWiseOr,memo);
    }

    public static void solveMemo(int index,int[]nums,int bitWiseOr,int memo[][]){
        if(maxOr < bitWiseOr){
            maxOr = bitWiseOr;
            count=1;
        }else if(maxOr == bitWiseOr){
            count++;
        }
        if(index == nums.length){
            return;
        }

        for(int i=index;i<nums.length;i++){
            solve(i+1,nums,bitWiseOr|nums[i]);
        }
    }

    static int maxOr=0,count=0;
    public static void solve(int index,int[]nums,int bitWiseOr){
        if(maxOr < bitWiseOr){
            maxOr = bitWiseOr;
            count=1;
        }else if(maxOr == bitWiseOr){
            count++;
        }
        if(index == nums.length){
            return;
        }

        for(int i=index;i<nums.length;i++){
            solve(i+1,nums,bitWiseOr|nums[i]);
        }
    }

    public static int solveM(int index,int[]nums,int currentOr,int bitWiseOr){
        if(index == nums.length) return currentOr == bitWiseOr ? 1:0;

        int noTake = solveM(index+1,nums,currentOr,bitWiseOr);
        int take = solveM(index+1,nums,currentOr|nums[index],bitWiseOr);
        return noTake + take;
    }

     public static int solveMMemo(int index,int[]nums,int currentOr,int bitWiseOr,int memo[][]){
        if(index == nums.length) return currentOr == bitWiseOr ? 1:0;
        if(memo[index][currentOr] != -1){
            return memo[index][currentOr];
        }
        int noTake = solveMMemo(index+1,nums,currentOr,bitWiseOr,memo);
        int take = solveMMemo(index+1,nums,currentOr|nums[index],bitWiseOr,memo);
        return memo[index][currentOr] = noTake + take;
    }

    public static int solveMItr(int index,int[]nums,int bitWiseOr,int memo[][]){
        memo[0][0]=1;
        for(int i=1;i<=nums.length;i++){
            for(int currentOr=0;currentOr <= bitWiseOr;currentOr++){
                if (memo[i - 1][currentOr] > 0) {
                    memo[i][currentOr] += memo[i-1][currentOr];
                    memo[i][currentOr|nums[i-1]] += memo[i-1][currentOr];
                }
            }
        }
        return memo[nums.length][bitWiseOr];
    }

    public static int solveMBitMask(int index,int[]nums,int bitWiseOr,int memo[][]){
        int totalSubSets = 1 << nums.length;
        int subsetsWithMaxOr=0;
        for(int subsetMask =0; subsetMask < totalSubSets;subsetMask++){
            int currentOrValue=0;
            for(int i=0;i<nums.length;i++){
                if(((subsetMask >> i ) & 1) == 1){
                    currentOrValue |= nums[i];
                }
            }
            if(currentOrValue == bitWiseOr){
                subsetsWithMaxOr++;
            }
        }
        return subsetsWithMaxOr;
    }

    public static int solveMBitMaskDp(int index,int[]nums,int bitWiseOr,int memo[][]){
        int max=0;
        int dp[]=new int[1<<17];
        dp[0]=1;

        for(int num:nums){
            for(int i=max;i>=0;i--){
                dp[i|num] +=dp[i];
            }
            max |=num;
        }
        return dp[max];
    }
}