package career.datastructure.dp.dponsequence;

import java.util.Arrays;

public class MinimumCostForTickets {
    public static void main(String[] args) {
        System.out.println(mincostTickets(new int[]{1,4,6,7,8,20},new int[]{2,7,15}));
        System.out.println(mincostTickets(new int[]{1,2,3,4,5,6,7,8,9,10,30,31},new int[]{2,7,15}));
    }
    public static int mincostTickets(int[] days, int[] costs) {
     return dfs(0, days.length, days, costs);
}

    private static int dfs(int i, int n, int[] days, int[] costs) {
        if (i == n) return 0;

        int res = costs[0] + dfs(i + 1, n, days, costs);
        int j = i;
        while (j < n && days[j] < days[i] + 7) {
            j++;
        }
        res = Math.min(res, costs[1] + dfs(j, n, days, costs));

        j = i;
        while (j < n && days[j] < days[i] + 30) {
            j++;
        }
        res = Math.min(res, costs[2] + dfs(j, n, days, costs));

        return res;
    }
}
