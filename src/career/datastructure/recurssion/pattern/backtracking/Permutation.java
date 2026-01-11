package career.datastructure.recurssion.pattern.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Permutation {
    public static void main(String[] args) {
        System.out.println(permute(new int[]{1,2,3}));
    }
    public static List<List<Integer>> permute(int[] nums) {
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
            System.out.println(index+" ,>"+permutation);
            permutations.add(permutation);
            return;
        }

        for(int i=index;i<nums.length;i++){ // if we use it will create duplicate sets.
            swap(nums,index,i);
            solve(i+1,nums,permutations);
            swap(nums,index,i);

        }
    }

    public static void swap(int nums[],int i,int j){
        int temp = nums[i];
        nums[i]= nums[j];
        nums[j]=temp;
    }

}
