package career.sixmonthssep.dp.twodiamensional;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/triangle/description/?envType=study-plan-v2&envId=dynamic-programming
 */
public class Triangle {
    public static void main(String[] args) {
        int triangle[][] = {{2},{3,4},{6,5,7},{4,1,8,3}};
        List<List<Integer>> lst = new ArrayList<>();
        for(int i=0;i<triangle.length;i++){
            List<Integer> inner = new ArrayList<>();
            for(int j=0;j<triangle[i].length;j++){
                inner.add(triangle[i][j]);
            }
            lst.add(inner);
        }
        System.out.println(lst);

        System.out.println(minimumTotal(lst));
    }

    public static int minimumTotal(List<List<Integer>> triangle) {

        List<Integer> prev = new ArrayList<>();

        for(int i=0;i<triangle.size();i++){
            List<Integer> curr = new ArrayList<>();
            for(int j=0;j<triangle.get(i).size();j++){
                if(i==0){
                    curr.add(triangle.get(i).get(j));
                    continue;
                }
                int min = Integer.MAX_VALUE;
                if(j-1 >= 0 && prev.size()-1 >= j-1){
                    min = prev.get(j-1);
                }
                if(prev.size()-1 >= j) {
                    min = Math.min(min, prev.get(j));
                }
                curr.add(min + triangle.get(i).get(j));
            }
            System.out.println(curr);
            prev = new ArrayList<>(curr);
        }

        int min = Integer.MAX_VALUE;
        for(int val:prev){
            min = Math.min(val,min);
        }

        return min;
    }
}
