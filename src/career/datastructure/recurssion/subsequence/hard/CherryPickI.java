package career.datastructure.recurssion.subsequence.hard;

import java.util.Arrays;

/*

clarification question
1] any value apart from 0 1 and -1 ?
2] source alwasy 0,0 and destination 1,1
3] Max path needed
4] cherry set to zero after pick so never picked again in second call.


Approach
1] single dfs
2] dp
Intuation:-
You are traveling from 0,0 to n-1,n-1 and n-1,n-1 to 0,0
Now think find two path which has max1 and max2 from 0,0 to n-1,n-1
Which means max1 and max2 path both have unique point. which is ans right ? yes
here we are doing same we start from 0,0.
P1	    P2	    r1	    c1	        r2	             c2
Down	Down	r1+1	c1 + 0	r1+1+c1-c2=+1	    c2+0
Down	Right	r1+1	c1 + 0	r1+c1-c2=+0	        c2+1
Right	Down	r1	    c1 + 1	r1+c1+1-c2=+1	    c2+0
Right	Right	r1	    c1 + 1	r1+c1-c2=+0	        c2+1

 */
public class CherryPickI {
    static boolean path[][];
    static boolean visited[][];
    static int maxCherry=0;
    static private Integer[][][] dp2;
    public static void main(String[] args) {
          int grid[][]={{0,1,-1},{1,0,-1},{1,1,1}};
//        int grid [][] ={{1,1,-1},{1,-1,1},{-1,1,1}};
//        int grid [][] ={{1}};
//        int grid [][] ={{0,0},{-1,1}};
//        int [][]grid = {{0,1,-1},{1,1,1}};
        int m = grid.length,n=grid[0].length;
        dfsRightDown(grid,m,n,0,0,0);
        System.out.println(maxCherry);

        dp2  = new Integer[m][n][m];
        int ans=solve2(0,0,0,grid,0,m,n);
        if(ans==Integer.MIN_VALUE) System.out.println(0);
        else System.out.println(ans);
    }

    private static int solve2(int x1, int y1, int x2, int[][] g, int cpsf, int m, int n){
        int y2 = x1+y1-x2;

        if(x1>=m ||x2>=m || y1>=n || y2>=n || g[x1][y1]==-1 || g[x2][y2]==-1){
            return Integer.MIN_VALUE;
        }

        if(x1==m-1 && y1==n-1) return g[x1][y1]; // when reached to end return dp value;

        if(dp2[x1][y1][x2] != null) return dp2[x1][y1][x2];

        int cherries=0;

        if(x1==x2 && y1==y2){ // when both reach at same point take only one time
            cherries+=g[x1][y1];
        }
        else{
            cherries+=g[x1][y1]+g[x2][y2]; // on different point take both point cherries.
        }

        int dd=solve2(x1+1,y1,x2+1,g,cpsf+cherries,m,n);
        int dr=solve2(x1+1,y1,x2,g,cpsf+cherries,m,n);
        int rr=solve2(x1,y1+1,x2,g,cpsf+cherries,m,n);
        int rd=solve2(x1,y1+1,x2+1,g,cpsf+cherries,m,n);

        int max=Math.max(Math.max(dd,dr), Math.max(rr,rd));
        if(max==Integer.MIN_VALUE) return dp2[x1][y1][x2]=max;
        return dp2[x1][y1][x2]=cherries+=max;
    }


    private static int solve1(int x1, int y1, int x2, int[][] g, int cpsf, int m, int n){
        int y2 = x1+y1-x2;

        if(x1>=m ||x2>=m || y1>=n || y2>=n || g[x1][y1]==-1 || g[x2][y2]==-1){
            return Integer.MIN_VALUE;
        }

        if(x1==m-1 && y1==n-1) return g[x1][y1]; // when reached to end return dp value;

        if(dp2[x1][y1][x2] != null) return dp2[x1][y1][x2];

        int cherries=0;

        if(x1==x2 && y1==y2){ // when both reach at same point take only one time
            cherries+=g[x1][y1];
        }
        else{
            cherries+=g[x1][y1]+g[x2][y2]; // on different point take both point cherries.
        }

        int dd=solve2(x1+1,y1,x2+1,g,cpsf+cherries,m,n);
        int dr=solve2(x1+1,y1,x2,g,cpsf+cherries,m,n);
        int rr=solve2(x1,y1+1,x2,g,cpsf+cherries,m,n);
        int rd=solve2(x1,y1+1,x2+1,g,cpsf+cherries,m,n);

        int max=Math.max(Math.max(dd,dr), Math.max(rr,rd));
        if(max==Integer.MIN_VALUE) return dp2[x1][y1][x2]=max;
        return dp2[x1][y1][x2]=cherries+=max;
    }
    private static void dfsRightDown(int[][]grid,int m,int n,int x,int y,int value){
        if(x >= m || y >= n || grid[x][y] == -1) return;
        if(x==m-1 && y == n-1){
            int cherries = grid[x][y];
            grid[x][y] = 0;
            dfsLeftUp(grid,x,y,value+cherries);
            grid[x][y] = cherries;
            return;
        }
        int cherries = grid[x][y];
        grid[x][y] = 0;
        dfsRightDown(grid,m,n,x+1,y,value+cherries);
        dfsRightDown(grid,m,n,x,y+1,value+cherries);
        grid[x][y] = cherries;
    }

    private static void dfsLeftUp(int[][]grid,int x,int y,int value){
        if(x < 0 || y < 0 || grid[x][y] == -1) return;
        if(x==0 && y == 0){
            maxCherry = Math.max(maxCherry,value);
            return;
        }
        int cherries = grid[x][y];
        grid[x][y] = 0;
        dfsLeftUp(grid,x-1,y,value+cherries);
        dfsLeftUp(grid,x,y-1,value+cherries);
        grid[x][y] = cherries;
    }
}
