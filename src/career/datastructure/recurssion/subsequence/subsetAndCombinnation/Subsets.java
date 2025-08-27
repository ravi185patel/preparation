package career.datastructure.recurssion.subsequence.subsetAndCombinnation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {
    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1,2,3}));
        System.out.println(subsets(new int[]{1,2,3,3}));
//        System.out.println(uniqueSubsets(new int[]{1,2,3,3}));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        helper(0,nums,new ArrayList<>(),res);
        return res;
    }

    public static List<List<Integer>> uniqueSubsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        helperUnique(0,nums,new ArrayList<>(),res);
        return res;
    }

    private static void helper(int index,int []nums,List<Integer> lst,List<List<Integer>>res){
        if(index >= nums.length){
            res.add(new ArrayList<>(lst));
            return;
        }

        lst.add(nums[index]);
        helper(index+1,nums,lst,res);
        lst.remove(lst.size()-1);
        helper(index+1,nums,lst,res);

//        res.add(new ArrayList<>(lst));
//        for(int i=index;i<nums.length;i++){
//            lst.add(nums[i]);
//            helper(i+1,nums,lst,res);
//            lst.remove(lst.size()-1);
//        }
    }

    private static void helperUnique(int index,int []nums,List<Integer> lst,List<List<Integer>>res){

        res.add(new ArrayList<>(lst));
        for(int i=index;i<nums.length;i++){
            if(i != index && nums[i] == nums[i-1]) continue;
            lst.add(nums[i]);
            helperUnique(i+1,nums,lst,res);
            lst.remove(lst.size()-1);
        }
    }
}
