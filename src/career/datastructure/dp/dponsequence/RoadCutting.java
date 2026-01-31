package career.datastructure.dp.dponsequence;

public class RoadCutting {
    public static void main(String[] args) {
        System.out.println(roadCutting(8,new int[]{1, 6, 8, 9, 10, 19, 7, 20}));
    }

    public static int roadCutting(int N,int prices[]){
//        return solve(prices.length-1,N,prices);
        return solveDp(prices.length,prices);
    }


    public static int solve(int index,int N,int prices[]){
        if(index == 0){
            return N*prices[0]; // prune branches
        }
        if(index < 0){
            return Integer.MIN_VALUE;
        }

        int notCut = solve(index-1,N,prices);
        int cut=Integer.MIN_VALUE;
        int roadLength = index + 1; // main point as you
        // cut you need to increase by 1 as you need to starr from index+1
        if(N >= roadLength){
            cut = prices[index] + solve(index-1,N-roadLength,prices);
        }
        return Math.max(notCut,cut);
    }

    public static int solveDp(int n,int prices[]){
            int[] prev = new int[n + 1];
            int[] cur = new int[n + 1];
            for (int length = 0; length <= n; length++) {
                prev[length] = prices[0] * length;
            }

            for (int ind = 1; ind < n; ind++) {
                for (int length = 1; length <= n; length++) {
                    int notTaken = prev[length];
                    int taken = Integer.MIN_VALUE;
                    int rodLength = ind + 1;
                    if (rodLength <= length) {
                        taken = prices[ind] + prev[length - rodLength];
                    }
                    cur[length] = Math.max(notTaken, taken);
                }
                prev = cur.clone();
            }
            return prev[n];
        }
}
