package career.datastructure.binarysearch;

import java.util.Arrays;

/*
https://leetcode.com/problems/successful-pairs-of-spells-and-potions/description/
You are given two positive integer arrays spells and potions, of length n and m respectively,
where spells[i] represents the strength of the ith spell and potions[j] represents the strength of the jth potion.

You are also given an integer success. A spell and potion pair is considered successful if the product of their strengths is at least success.

Return an integer array pairs of length n where pairs[i] is the number of potions that will form a successful pair with the ith spell.



Example 1:

Input: spells = [5,1,3], potions = [1,2,3,4,5], success = 7
Output: [4,0,3]
Explanation:
- 0th spell: 5 * [1,2,3,4,5] = [5,10,15,20,25]. 4 pairs are successful.
- 1st spell: 1 * [1,2,3,4,5] = [1,2,3,4,5]. 0 pairs are successful.
- 2nd spell: 3 * [1,2,3,4,5] = [3,6,9,12,15]. 3 pairs are successful.
Thus, [4,0,3] is returned.
Example 2:

Input: spells = [3,1,2], potions = [8,5,8], success = 16
Output: [2,0,2]
Explanation:
- 0th spell: 3 * [8,5,8] = [24,15,24]. 2 pairs are successful.
- 1st spell: 1 * [8,5,8] = [8,5,8]. 0 pairs are successful.
- 2nd spell: 2 * [8,5,8] = [16,10,16]. 2 pairs are successful.
Thus, [2,0,2] is returned.


Constraints:

n == spells.length
m == potions.length
1 <= n, m <= 105
1 <= spells[i], potions[i] <= 105
1 <= success <= 1010
 */
public class SuccessfulPairsSpellsAndPotions {
    public static void main(String[] args) {

        int resp[]=successfulPairs(new int[]{5,1,3},new int[]{1,2,3,4,5},7);
        System.out.println(Arrays.toString(resp));
        resp=successfulPairs(new int[]{3,1,2},new int[]{8,5,8},16);
        System.out.println(Arrays.toString(resp));
    }
    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int length = spells.length;
        int ans[]= new int[length];
        for(int i=0;i<length;i++){
           ans[i] = findNoOfPotionsMoreThenSuccess(spells[i],potions,success);
        }
        return ans;
    }

    public static int findNoOfPotionsMoreThenSuccess(int spell,int[]potions,long success){
        int count=0;
        int left =0,right = potions.length-1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(potions[mid]*spell >= success){
                count =right - mid + 1;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return count;
    }

    public static int findNoOfPotionsMoreThenSuccessTP(int spell,int[]potions,long success){
        int count=0;
        int left =0,right = potions.length-1;
        while(left <= right){
            long leftVal = potions[left]*spell;
            long rightVal =potions[right]*spell;
            if(leftVal >= success){
                return right;
            }else if(leftVal < success){
                left++;
            }
            if(rightVal < success){
                return 0;
            }else if(rightVal >= success){
                rightVal--;
            }
        }
        return Math.abs(right-left);
    }
}
