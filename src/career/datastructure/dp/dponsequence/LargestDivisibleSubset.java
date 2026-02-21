package career.datastructure.dp.dponsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LargestDivisibleSubset {
    public static void main(String[] args) {
//        System.out.println(largestDivisibleSubset(new int[]{1,2,3}));
//        System.out.println(largestDivisibleSubset(new int[]{1,2,4,8}));
        System.out.println(largestDivisibleSubset(new int[]{3,4,16,8}));
    }
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
//        System.out.println(solveRec(0,nums));
//        List<Integer> res = new ArrayList<>();
//        List<Integer> path = new ArrayList<>();
//        solveRec(0,nums,res,path);
//        return res;
        return longestDivSeq(nums);
    }

    public static void solveRec(int index,int nums[],List<Integer> finalRes,List<Integer> path){
        if(finalRes.size() < path.size()){
//            System.out.println(finalRes.size()+" "+path.size());
            finalRes.clear();
            finalRes.addAll(new ArrayList<>(path));
        }
        for(int i=index;i<nums.length;i++){
            if(path.size() > 0 && nums[i]%path.get(path.size()-1) != 0 && path.get(path.size()-1)%nums[i] != 0) continue;
            path.add(nums[i]);
            solveRec(i+1,nums,finalRes,path);
            path.remove(path.size()-1);
        }
    }
    public static List<Integer> longestDivSeq(int nums[]){
        int n = nums.length;
        int parent[]=new int[n+1];
        for(int i=0;i<n;i++){
            parent[i]=i;
        }
        int lds[]=new int[n+1];

        Arrays.fill(lds,1);

        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                if(nums[j] % nums[i] == 0 || nums[i] % nums[j] == 0){
                    if(lds[i] < lds[j]+1){
                        parent[i]=j;
                        lds[i]=lds[j]+1;
                    }
                }
            }
        }

        int ans=-1;
        int lastIndex = -1;
        for(int i=0;i<n;i++){
            if(lds[i] > ans){
                ans = lds[i];
                lastIndex = i;
            }
        }
        List<Integer> lst = new ArrayList<>();
        lst.add(nums[lastIndex]);
        while(parent[lastIndex] != lastIndex){
            lastIndex = parent[lastIndex];
            lst.add(0,nums[lastIndex]);
        }

        return lst;
    }

}
