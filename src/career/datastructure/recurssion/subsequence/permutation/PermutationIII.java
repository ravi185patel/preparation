package career.datastructure.recurssion.subsequence.permutation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermutationIII {
    public static void main(String[] args) {
        List<List<Integer>> res = new ArrayList<>();
        int n=4;
        helper(0,n,new ArrayList<>(),res, new boolean[n]);
        System.out.println(res);
    }
    public static void helper(int index,int n,List<Integer> lst, List<List<Integer>> res,boolean [] visited) {
        if(index == n){
            res.add(new ArrayList<>(lst));
            return;
        }

        for(int i=0;i<n;i++){
            if(visited[i]) continue;
            if(index != 0 && (i)%2 == (index-1)%2) continue;
            visited[i]=true;
            lst.add(i+1);
            helper(index+1,n,lst,res,visited);
            lst.remove(lst.size()-1);
            visited[i]=false;
        }

    }
}
