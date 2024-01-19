package career.interview.google;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MinimumFallingPathsum {
    public static void main(String[] args) {
//        int matrix[][]={{2,1,3},{6,5,4},{7,8,9}};
        int matrix[][]={{-19,57},{-40,-5}};
        int res = minFallingPathSum(matrix);
        System.out.println(res);
    }

    public static int minFallingPathSum(int[][] matrix) {

        int min = Integer.MAX_VALUE;


        /*for(int i=0;i<matrix.length;i++){
//            System.out.println(matrix[0][i]);
//            min = Math.min(min,bfs(matrix,0,i));  //-- [i]
//            min = Math.min(min,bfs1(matrix,0,i,matrix[0][i])); //-- [ii]
            int dp[][]=new int[matrix.length][matrix[0].length];
//            for(int j[]:dp) {
//                Arrays.fill(j,-1);
//            }
//            min = Math.min(min,bfsMemo(matrix,0,i,dp)); // //-- [iii]
        }*/


        int dp[][]=new int[matrix.length][matrix[0].length];
        for(int j[]:dp) {
            Arrays.fill(j,-1);
        }

        min = bfsDp(matrix,dp);


        return min;
    }


    public static int bfsDp(int[][]matrix,int dp[][]){

        for(int j=0;j<matrix[0].length;j++){
            dp[0][j] = matrix[0][j];
        }


        for(int i=1;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){

                int up = matrix[i][j] + dp[i-1][j];
                int ldi = Integer.MAX_VALUE,rdi = Integer.MAX_VALUE;
                if(j-1 >=0){
                    ldi = matrix[i][j] + dp[i-1][j-1];
                }

                if(j+1 < matrix[i].length){
                    rdi = matrix[i][j] + dp[i-1][j+1];
                }

                dp[i][j] = Math.min(rdi,Math.min(ldi,up));


            }
        }

        int min = Integer.MAX_VALUE;
        for(int j=0;j<matrix[0].length;j++){
            min = Math.min(min,dp[matrix.length-1][j]);
        }

        return min;

    }

    static int directions[][]={{1,-1},{1,0},{1,1}};

    public static int bfsMemo(int[][]matrix,int x,int y,int dp[][]){
        if(y < 0 || y >= matrix[x].length){
            return 100000;
        }

        if(dp[x][y] != -1){
            return dp[x][y];
        }
        if(x == matrix.length-1){
            return matrix[x][y];
        }

        int i=matrix[x][y] + bfsMemo(matrix,x+1,y+1,dp);
        int j=matrix[x][y] + bfsMemo(matrix,x+1,y-1,dp);
        int k=matrix[x][y] + bfsMemo(matrix,x+1,y,dp);
        return dp[x][y] = Math.min(i,Math.min(j,k));
    }

    public static int bfs1(int[][]matrix,int x,int y,int w){
        if(y < 0 || y >= matrix[x].length){
            return 100000;
        }

        if(x == matrix.length-1){
            return matrix[x][y];
        }

        int i=matrix[x][y] + bfs1(matrix,x+1,y+1,w+matrix[x][y]);
        int j=matrix[x][y] + bfs1(matrix,x+1,y-1,w+matrix[x][y]);
        int k=matrix[x][y] + bfs1(matrix,x+1,y,w+matrix[x][y]);
        return Math.min(i,Math.min(j,k));
    }

    public static int bfs(int[][]matrix,int x,int y){

        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{x,y,matrix[x][y]});
        int min = Integer.MAX_VALUE;
        while(!queue.isEmpty()){
            int point[]=queue.poll();
            int px = point[0];
            int py = point[1];
            int pw = point[2];
            if(px == matrix.length-1){
                min = Math.min(pw,min);
            }
            for(int direction[]:directions){
                int nPx = px + direction[0];
                int nPy = py + direction[1];

                if(nPx < 0 || nPx >= matrix.length || nPy < 0 || nPy >= matrix[px].length){
                    continue;
                }

                int nW = pw + matrix[nPx][nPy];
                queue.add(new int[]{nPx,nPy,nW});
            }
        }

        return min;

    }
}
