package career.datastructure.recurssion;

import java.util.Arrays;

public class PainterProblem {
    public static int min=Integer.MAX_VALUE;
    public static void main(String[] args) {

//        int cost[][]={{1,4,2}
//                ,{2,3,6}
//        };
        int cost[][]={ {1,4,5},{2,3,5},{6,7,5}};
        int min = dfs(cost,cost.length-1,3);
        System.out.println(min);

        int memo[][]=new int[cost.length][4];
        for(int i[]:memo){
            Arrays.fill(i,-1);
        }
        min = dfsMemo(cost, cost.length-1, 3,memo);
        System.out.println(min);
        min = dfsDp(cost,new int[cost.length][3]);
        System.out.println(min);

        min = dfsDp(cost);
        System.out.println(min);
    }


    private static int dfs(int cost[][],int row,int col){
        if(row < 0) return 0;

        int ans = Integer.MAX_VALUE;
        for(int i=cost[0].length-1;i>=0;i--){
            if(col == i) continue;
            ans = Math.min(ans,dfs(cost,row-1,i) + cost[row][i]);
        }
       return ans;
    }

    private static int dfsMemo(int cost[][],int row,int col,int memo[][]){
        if(row < 0) return 0;

        if(memo[row][col] != -1) return memo[row][col];

        int ans = Integer.MAX_VALUE;
        for(int i=cost[0].length-1;i>=0;i--){
            if(col == i) continue;
            ans = Math.min(ans,dfsMemo(cost,row-1,i,memo) + cost[row][i]);
        }
        return memo[row][col] = ans;
    }


    private static int dfsDp(int cost[][],int memo[][]){
        memo[0][0] = cost[0][0];
        memo[0][1] = cost[0][1];
        memo[0][2] = cost[0][2];

        for(int i=1;i<cost.length;i++){
            memo[i][0] = Math.min(memo[i-1][1],memo[i-1][2]) + cost[i][0];
            memo[i][1] = Math.min(memo[i-1][0],memo[i-1][2]) + cost[i][1];
            memo[i][2] = Math.min(memo[i-1][1],memo[i-1][0]) + cost[i][2];
        }

        return Math.min(memo[cost.length-1][1],Math.min(memo[cost.length-1][0],memo[cost.length-1][2]));
    }

    private static int dfsDp(int cost[][]){
        int red = cost[0][0],currentRed=0;
        int green = cost[0][1],currentGreen=0;
        int yellow = cost[0][2],currentYellow=0;

        for(int i=1;i<cost.length;i++){
            currentRed = Math.min(green,yellow) + cost[i][0];
            currentGreen = Math.min(red,yellow) + cost[i][1];
            currentYellow = Math.min(green,red) + cost[i][2];

            red = currentRed;
            yellow = currentYellow;
            green = currentGreen;
        }

        return Math.min(red, Math.min(green,yellow));
    }

}
