package career.datastructure.recurssion.striver.subsetAndCombinnation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    public static void main(String[] args) {
        // normal
        int candidates[] = {2,3,6,7}, target = 7;
        System.out.println(combinationSum(candidates,target));

//        int candidates[] = {2,3,5}, target = 8;
//        System.out.println(combinationSum(candidates,target));
//        int candidates[] = {2}, target = 1;
//        System.out.println(combinationSum(candidates,target));
    // unique
        candidates = new int[]{10, 1, 2, 7, 6, 1, 5};
        target = 8;
        System.out.println(combinationSumUnique(candidates,target));

        // unique + no array + only n and k
        int n = 7,k=3;
        int arr[] = new int[10];
        for(int i=0;i<arr.length;i++){
            arr[i]=i+1;
        }

        System.out.println(combinationSumUniqueLimit(arr,n,k));

        // count of subset
        candidates = new int[]{1,2,3};
        target = 4;
        System.out.println(combinationSumUniqueCnt(candidates,target));

    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        helper(0,target,candidates,new ArrayList<>(),res);
        return res;
    }

    private static void helper(int index,int target,int[] candidates,List<Integer> lst,List<List<Integer>> res){
        if(target == 0){
            res.add(new ArrayList<>(lst));
            return;
        }
        for(int i=index;i<candidates.length;i++){
            if(target >= candidates[i]){
                lst.add(candidates[i]);
                helper(i,target - candidates[i],candidates,lst,res);
                lst.remove(lst.size()-1);
            }
        }
    }

    public static List<List<Integer>> combinationSumUnique(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        helperUnique(0,target,candidates,new ArrayList<>(),res);
        return res;
    }

    private static void helperUnique(int index,int target,int[] candidates,List<Integer> lst,List<List<Integer>> res){
//        if(index == candidates.length){
//            if(target == 0) {
//                res.add(new ArrayList<>(lst));
//            }
//            return;
//        }
        if(target == 0){
            res.add(new ArrayList<>(lst));
            return;
        }
        for(int i=index;i<candidates.length;i++){
            if(i != index && candidates[i] == candidates[i-1]) continue;
            if(target >= candidates[i]){
                lst.add(candidates[i]);
                helperUnique(i+1,target - candidates[i],candidates,lst,res);
                lst.remove(lst.size()-1);
            }
        }
    }


    public static List<List<Integer>> combinationSumUniqueLimit(int[] candidates, int target,int limit) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
//        helperUniqueLimit(0,target,candidates,new ArrayList<>(),res,limit);
        helperUniqueLimit(1,9,target,new ArrayList<>(),res,limit);
        return res;
    }

    private static void helperUniqueLimit(int index,int target,int[] candidates,List<Integer> lst,List<List<Integer>> res,int limit){
        if(target == 0 && limit == 0){
            res.add(new ArrayList<>(lst));
            return;
        }
        for(int i=index;i<candidates.length;i++){
            if(i != index && candidates[i] == candidates[i-1]) continue;
            if(target >= candidates[i]){
                lst.add(candidates[i]);
                helperUniqueLimit(i+1,target - candidates[i],candidates,lst,res,limit-1);
                lst.remove(lst.size()-1);
            }
        }
    }

    private static void helperUniqueLimit(int start,int end,int target,List<Integer> lst,List<List<Integer>> res,int limit){
        if(target == 0 && limit == 0){
            res.add(new ArrayList<>(lst));
            return;
        }
        for(int i=start;i<=end;i++){
//            if(i != start && candidates[i] == candidates[i-1]) continue;
            if(target >= i){
                lst.add(i);
                helperUniqueLimit(i+1,end,target - i,lst,res,limit-1);
                lst.remove(lst.size()-1);
            }
        }
    }


    public static int combinationSumUniqueCnt(int[] candidates, int target) {
        return helperUniqueCnt(target,candidates);
    }

    private static int helperUniqueCnt(int target,int[] candidates){
        if(target < 0){
            return 0;
        }
        if(target == 0){
            return 1;
        }

        int res=0;
        for(int i=0;i<candidates.length;i++){
            res += helperUniqueCnt(target-candidates[i],candidates);
        }
        return res;
    }

}
