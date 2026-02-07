package career.datastructure.dp.twothreedp;

import java.util.Arrays;

public class GridUniquePaths {
    public static void main(String[] args) {
        System.out.println(gridUniquePaths(3,2));
        System.out.println(gridUniquePaths(2,4));
    }
    public static int gridUniquePaths(int n,int m){  // either used matrix and calculate
      return solveDp(n,m);
    }

    public static int solveRec(int m,int n){
        if(n == 0 && m == 0) return 1;
        return solveRec(m-1,n) + solveRec(m,n-1);
    }
    public static int solveDp(int n,int m){
        int prev[]=new int[n]; // how you come with that prev array size
        // as we calculating row wise value so n will be size

        Arrays.fill(prev,1);
        for(int row=1;row<m;row++){
            int curr[] = new int[n+1];
            curr[0]=1;
            for(int col=1;col<n;col++){
                curr[col] =curr[col-1]+prev[col]; // either i take previous or previous row value
            }
            prev = curr.clone();
        }
        return prev[n-1];
    }
}
