package career.thirtydays.dp.subsequence;

import java.util.Arrays;

/*
Assume you are an awesome parent and want to give your children some cookies.
But, you should give each child at most one cookie.

Each child i has a greed factor g[i],
which is the minimum size of a cookie that the child will be content with;
and each cookie j has a size s[j]. If s[j] >= g[i],
we can assign the cookie j to the child i, and the child i will be content.
Your goal is to maximize the number of your content children and output the maximum number.

 */

public class AssignCookies {
    public static void main(String[] args) {
        int []g = {1,2,3}, s = {1,1};

        System.out.println(findContentChildren(g,s));
    }

    public static int findContentChildren(int[] g, int[] s) {
        int l = 0,m=s.length;
        int r = 0,n=g.length;

        Arrays.sort(g);
        Arrays.sort(s);
        while(l < m){
            if(r < n && s[l] >= g[r]){
                r = r+1;
            }
            l = l+1;
        }
        return r;
    }
}
