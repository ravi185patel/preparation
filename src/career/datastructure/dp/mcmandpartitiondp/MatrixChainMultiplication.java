package career.datastructure.dp.mcmandpartitiondp;

public class MatrixChainMultiplication {
    public static void main(String[] args) {
        System.out.println(minMultiply(new int[]{40, 20, 30, 10, 30}));
    }

    public static int minMultiply(int nums[]){
        return solve(nums,1,nums.length-1);
    }

    public static int solve(int[] arr, int i, int j) {
        // Base case: if only one matrix is present, no multiplication needed
        if (i == j) return 0;

        // Initialize minimum cost as very large
        int minCost = Integer.MAX_VALUE;

        // Try all possible partitions
        for (int k = i; k < j; k++) {
            // Cost of multiplying matrices from i to k
            int cost1 = solve(arr, i, k);

            // Cost of multiplying matrices from k+1 to j
            int cost2 = solve(arr, k + 1, j);

            // Cost of multiplying the two resulting matrices
            int costMultiply = arr[i - 1] * arr[k] * arr[j];

            // Total cost for this partition
            int total = cost1 + cost2 + costMultiply;

            // Update minimum
            minCost = Math.min(minCost, total);
        }

        return minCost;
    }

    public static int solveDp(int[] nums) {
        int n = nums.length;
        int dp[][]=new int[n+1][n+1];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                dp[i][j]=Integer.MAX_VALUE;
            }
        }

        for(int i=1;i<n;i++) {
            dp[i][i] = 0;
        }

        int minCost=Integer.MAX_VALUE;
        for(int length=2;length<n;length++){ // matrix length
            for(int i=1;i<=n-length;i++){ // starting point
                int j = i + length-1; // ending point
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + nums[i - 1] * nums[k] * nums[j];
                    // Take the minimum cost
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                    }
                }
            }
        }

        return dp[1][n-1];
    }
}
