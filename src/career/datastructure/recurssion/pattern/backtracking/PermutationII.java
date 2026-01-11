package career.datastructure.recurssion.pattern.backtracking;

import java.util.ArrayList;
import java.util.List;

public class PermutationII {
    public static void main(String[] args) {

        System.out.println(permuteUnique(new int[]{1,1,2}));
        System.out.println(permuteUnique(new int[]{1,2,3}));
    }
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();

        solve(0,nums,permutations);

        return permutations;

    }

    public static void solve(int index,int nums[],List<List<Integer>> permutations){
        if(index == nums.length){
            List<Integer> permutation = new ArrayList<>();
            for(int i:nums){
                permutation.add(i);
            }
//            System.out.println(permutation);
            permutations.add(permutation);
            return;
        }

        for(int i=index;i<nums.length;i++){ // if we use it will create duplicate sets.
            if(i > index && nums[i] == nums[i-1]) continue;
            swap(nums,index,i);
            solve(index+1,nums,permutations);
            swap(nums,index,i);

        }
    }

    public static void swap(int nums[],int i,int j){
        int temp = nums[i];
        nums[i]= nums[j];
        nums[j]=temp;
    }

}
