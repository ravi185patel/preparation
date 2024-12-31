package career.datastructure.recurssion.subsequence;

import java.util.ArrayList;
import java.util.List;

public class PrintAllPermutations {
    public static void main(String[] args) {
        int nums[]={3,1,2};
        boolean visited[]=new boolean[nums.length];
        List<List<Integer>> res = new ArrayList<>();
        helper(nums,res,new ArrayList<>(),visited);
        System.out.println(res);
        res.clear();
        helper(nums,0,res);
        System.out.println(res);
    }


    public static void helper(int nums[],
                              List<List<Integer>> res,
                              List<Integer> temp,
                              boolean visited[]){
        if(temp.size() == nums.length){
            res.add(new ArrayList<>(temp));
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(visited[i]) continue;
            temp.add(nums[i]);
            visited[i]=true;
            helper(nums,res,temp,visited);
            temp.remove(temp.size()-1);
            visited[i]=false;
        }
    }

    public static void helper(int []nums,
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
            System.out.println(i+" <before> "+index);
            helper(nums,index+1,res);
            System.out.println(i+" <after> "+index);
            swap(i,index,nums);
        }
    }

    public static void swap(int i,int j,int nums[]){
        int temp = nums[i];
        nums[i]=nums[j];
        nums[j] = temp;
    }
}
