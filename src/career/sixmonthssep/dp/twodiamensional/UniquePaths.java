package career.sixmonthssep.dp.twodiamensional;

import java.util.Arrays;

/*

https://leetcode.com/problems/unique-paths/?envType=study-plan-v2&envId=dynamic-programming

Approach
1] recursion
2] memo
3] DP

Dp in matrix
1] initialize bounday line based on given condition or constraint or asking in question
2] start from second index instead of 0 because 0 was calculated you need to calculate
value at index 1 using index 0 value.
3] result will be at n index same like we did for 1 diamensional dp.

How it is different from 1D dp
Here you can have same two choice but your next value depend
previous value(col-1)(row) and previous row value(col)(row-1). // depend on constraint asked in question
 */
public class UniquePaths {
    public static void main(String[] args) {
        System.out.println(uniquePathsRec(3,7));
        System.out.println(uniquePaths(3,7));
    }


    public static int uniquePathsRec(int m, int n) {
        if(m < 1 || n < 1) return 0;
        if(m == 1 && n == 1) return 1;

        return uniquePathsRec(m-1,n) + uniquePathsRec(m,n-1);
    }

    public static int uniquePaths(int m, int n) {
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
