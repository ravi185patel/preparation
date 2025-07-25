package career.datastructure.recurssion.subsequence.permutation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermutationI {
    public static void main(String[] args) {
        List<List<Integer>> res = new ArrayList<>();
//        int nums[]={1,1,2};
        int nums[]={1,1,2,2};
        boolean visited[]=new boolean[nums.length];

        helper(nums,0,new ArrayList<>(),res,visited);
        System.out.println(res);
        res.clear();


        helper1(nums,0,res);
        System.out.println(res);
        res.clear();

        helper2(nums,0,res);
        System.out.println(res);
        res.clear();

        helper3(nums,0,res);
        System.out.println(res);


    }
    public static void helper(int[]nums, int index, List<Integer> lst, List<List<Integer>> res,boolean visited[]){
        if(lst.size() == nums.length){
            res.add(new ArrayList<>(lst));
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(!visited[i]) {
                lst.add(nums[i]);
                visited[i]=true;
                helper(nums, index + 1, lst, res,visited);
                visited[i]=false;
                lst.remove(lst.size() - 1);
            }
        }
    }
    public static void helper1(int []nums,
                               int index,
                               List<List<Integer>> res){
        if(index == nums.length){
            List<Integer> temp = new ArrayList<>();
            for(int i:nums){
                temp.add(i);
            }
            res.add(new ArrayList<>(temp));
            return;
        }

        for(int i=index;i<nums.length;i++){
            swap(i,index,nums);
            helper1(nums,index+1,res);
            swap(i,index,nums);
        }
    }

    public static void helper2(int []nums,
                               int index,
                               List<List<Integer>> res){
        if(index == nums.length){
            List<Integer> temp = new ArrayList<>();
            for(int i:nums){
                temp.add(i);
            }
            res.add(new ArrayList<>(temp));
            return;
        }

        for(int i=index;i<nums.length;i++){
            if(i > index && nums[i] == nums[index]) continue;
//            if(i != index && nums[i] == nums[index]) continue; // work same as i > index as sorted and i alwasy i >= index
            swap(i,index,nums);
            helper2(nums,index+1,res);
            swap(i,index,nums);
        }
    }

    public static void helper3(int []nums,
                               int index,
                               List<List<Integer>> res){
        if(index == nums.length){
            List<Integer> temp = new ArrayList<>();
            for(int i:nums){
                temp.add(i);
            }
            res.add(new ArrayList<>(temp));
            return;
        }
        Set<Integer> seen = new HashSet<>();

        for(int i=index;i<nums.length;i++){
            if(seen.contains(nums[i])) continue;
            seen.add(nums[i]);
            swap(i,index,nums);
            helper3(nums,index+1,res);
            swap(i,index,nums);
        }
    }
    public static void swap(int i,int j,int nums[]){
        int temp = nums[i];
        nums[i]=nums[j];
        nums[j] = temp;
    }
}
