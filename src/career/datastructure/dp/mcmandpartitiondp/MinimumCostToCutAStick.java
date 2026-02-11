package career.datastructure.dp.mcmandpartitiondp;

import java.util.Arrays;

public class MinimumCostToCutAStick {
    public static void main(String[] args) {
        System.out.println(minCost(7,new int[]{1,3,4,5}));
    }

    public static int minCost(int n, int[] cuts) {
        int sum= Arrays.stream(cuts).sum();
        int length = cuts.length;
        int temp[] = new int[length+2];
        temp[0]=0;
        temp[length+1]=n;
        for(int i=1;i<=length;i++){
            temp[i] = cuts[i-1];
        }
        Arrays.sort(temp);
        System.out.println(Arrays.toString(temp));
        return solveRec(1,temp.length-2,temp);
    }

    public static int solveRec(int i,int j,int[]cuts){
        if(i > j) return 0;

        int min = Integer.MAX_VALUE;
        for(int k=i;k<=j;k++){
            int cost = cuts[j+1] - cuts[i-1] + solveRec(i,k-1,cuts) + solveRec(k+1,j,cuts);
            min = Math.min(min,cost);
        }
        return min;
    }
}
