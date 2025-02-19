package career.sixmonthssep.dp.matrix.mcm;

public class PartitionArrayForMaxSum {
    public static void main(String[] args) {
        System.out.println(maxSumAfterPartitioning(new int[]{1,15,7,9,2,5,10},3));
    }

    public static int maxSumAfterPartitioning(int[] arr, int k) {
        return msp(arr,k,0);
    }

    private static int mspDp(int[] arr,int k,int index) {
        int n = arr.length;
        int[] dp = new int[n + 1];

        // Traverse the input array from right to left
        for (int ind = n - 1; ind >= 0; ind--) {
            int len = 0;
            int maxi = Integer.MIN_VALUE;
            int maxAns = Integer.MIN_VALUE;

            // Iterate through the next 'k' elements or remaining elements if less than 'k'.
            for (int j = ind; j < Math.min(ind + k, n); j++) {
                len++;
                maxi = Math.max(maxi, arr[j]);
                int sum = len * maxi + dp[j + 1];
                maxAns = Math.max(maxAns, sum);
            }
            dp[ind] = maxAns;
        }
        return dp[0];
    }
    private static int msp(int[] arr,int k,int index){
        if(index == arr.length){
            return 0;
        }

        int len = 0;
        int maxi = Integer.MIN_VALUE;
        int maxAns = Integer.MIN_VALUE;

        for(int j=index;j< Math.min(arr.length,index+k);j++){
            len++;
            maxi = Math.max(maxi, arr[j]);
            int sum = len * maxi + msp(arr, k, j+1);
            maxAns = Math.max(maxAns, sum);
        }

        return maxAns;
    }
}
