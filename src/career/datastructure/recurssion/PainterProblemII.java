package career.datastructure.recurssion;

import java.util.Arrays;
public class PainterProblemII {
    public static int min=Integer.MAX_VALUE;
    public static void main(String[] args) {
        int op=6;
        System.out.println(" "+~op);
//        int cost[][] = {{1, 4, 2}
//                , {2, 3, 6}
//        },n=2,k=3;

        int costs[][]={ {1,4,5},{2,3,5},{6,7,5}},n=3,k=3; // 10
        for(int i[]:costs){
            System.out.println(Arrays.toString(i));
        }
        int memo[][]=new int[costs.length][k+1];
        for(int i[]:memo){
            Arrays.fill(i,-1);
        }
        int min = dfsMemo(costs, costs.length-1,k,memo);
        System.out.println(min);
        min = dfsDp(costs, n, k);
        System.out.println(min);
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

    private static int dfsDp(int cost[][],int n,int k){
        int prev[]=new int[k];
        for(int i=0;i<n;i++){
            prev[i] = cost[0][i];
        }
        for(int i=1;i<n;i++){
            int cur[]=new int[k];
            Arrays.fill(cur,Integer.MAX_VALUE);
            for(int j=0;j<k;j++){
                int min=Integer.MAX_VALUE;
                for(int l=0;l<k;l++){
                    if(l==j) continue;
                    min = Math.min(min,prev[l]);
                }
                cur[j] = min + cost[i][j];
            }
            prev = cur.clone();
        }
        int min=Integer.MAX_VALUE;
        for(int j:prev){
            min = Math.min(min,j);
        }
        return min;
    }

    private static int dfsDpGood(int cost[][],int n,int k){
        int prevColorCost[]=new int[k];
        for(int i=0;i<n;i++){
            prevColorCost[i] = cost[0][i];
        }
        for(int houseIndex=1;houseIndex<n;houseIndex++){
            int curColorCost[]=cost[houseIndex].clone();
            for(int colorIndex=0;colorIndex<k;colorIndex++){
                int minCost=Integer.MAX_VALUE;
                for(int prevColorIndex=0;prevColorIndex<k;prevColorIndex++){
                    if(colorIndex==prevColorIndex) continue;
                    minCost = Math.min(minCost,prevColorCost[prevColorIndex]);
                }
                curColorCost[colorIndex] += minCost;
            }
            prevColorCost = curColorCost;
        }
        int minColorCost=Integer.MAX_VALUE;
        for(int colorCost:prevColorCost){
            minColorCost = Math.min(minColorCost,colorCost);
        }
        return minColorCost;
    }

}

