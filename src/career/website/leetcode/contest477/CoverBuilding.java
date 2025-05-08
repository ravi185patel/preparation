package career.website.leetcode.contest477;

import java.util.HashSet;
import java.util.Set;

public class CoverBuilding {
    public static void main(String[] args) {
//        int n = 3, buildings[][] = {{1,2},{2,2},{3,2},{2,1},{2,3}};
        int n = 5, buildings[][] = {{1,3},{3,2},{3,3},{3,5},{5,3}};
        System.out.println(countCoveredBuildings(n,buildings));
    }

    public static int countCoveredBuildings(int n, int[][] buildings) {
       int left[]=new int[n+1];
       int right[]=new int[n+1];
       int up[]=new int[n+1];
       int down[]=new int[n+1];

        for(int i=1;i<=n;i++){
            left[i]=n+1;
            right[i]=0;
            up[i] = n+1;
            down[i]=0;
        }

        for (int[] b : buildings) {
            int x = b[0], y = b[1];
            left[y]=Math.min(left[y],x);
            right[x]=Math.max(right[x],y);
            up[y]=Math.min(up[y],x);
            down[x]=Math.max(down[x],y);
        }
        int coverage=n;
        for (int[] b : buildings) {
            int x = b[0], y = b[1];
            if(x > left[y] && x <right[y] && y > up[x] && y < down[x]){
                continue;
            }else{
                coverage--;
            }
        }

        return coverage;
    }

    public static int countCoveredBuildings1(int n, int[][] buildings) {
        int matrix[][]=new int[n+1][n+1];
        for(int building[]:buildings){
            int x=building[0];
            int y= building[1];
            matrix[x][y]=1;
        }

        int covered=0;
        for (int[] b : buildings) {
            int x = b[0], y = b[1];
            boolean upFlag =false,downFlag=false,leftFlag=false,rightFlag=false;
            int upX=x-1;
            while(upX >= 0){
                if(matrix[upX][y]==1){
                    upFlag=true;
                    break;
                }
                upX--;
            }
            int downX=x+1;
            while(downX <= n){
                if(matrix[downX][y]==1){
                    downFlag=true;
                    break;
                }
                downX++;
            }

            int upY=y-1;
            while(upY >= 0){
                if(matrix[x][upY]==1){
                    leftFlag=true;
                    break;
                }
                upY--;
            }
            int downY=y+1;
            while(downY <= n){
                if(matrix[x][downY]==1){
                    rightFlag=true;
                    break;
                }
                downY++;
            }

            if(upFlag && downFlag && rightFlag && leftFlag){
                covered++;
            }

        }

        return covered;
    }
}
