package career.datastructure.swapline;

/*
https://leetcode.com/problems/zero-array-transformation-ii/description/
 */
public class ZeroArrayTransformationII {
    public static void main(String[] args) {

    }

    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length, sum = 0, k = 0;
        int[] differenceArray = new int[n + 1];

        // Iterate through nums
        for (int index = 0; index < n; index++) {
            // Iterate through queries while current index of nums cannot equal zero
            while (sum + differenceArray[index] < nums[index]) {
                k++;

                // Zero array isn't formed after all queries are processed
                if (k > queries.length) {
                    return -1;
                }
                int left = queries[k - 1][0], right = queries[k - 1][1], val =
                        queries[k - 1][2];

                // Process start and end of range
                if (right >= index) {
                    differenceArray[Math.max(left, index)] += val;
                    differenceArray[right + 1] -= val;
                }
            }
            // Update prefix sum at current index
            sum += differenceArray[index];
        }
        return k;
    }

    public int minZeroArray1(int[] nums, int[][] queries) {


        /* it is possible or not to set all zero by decrement at each
        index; second variant

        int length = nums.length;
        int freq[] = new int[length+1];
        for(int query[]:queries){
            freq[query[0]]+= query[2];
            freq[query[1]+1] -= query[2];
        }
        int op = 0;
        for(int i=0;i<nums.length;i++){
            op += freq[i];
            if(op < nums[i]) return false;
        }
        return true;
        */
        int left =0,right = queries.length;
        int ans = -1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(canMakeZero(nums,queries,mid)){
                ans = mid;
                right = mid-1;
            }else{
                left = mid + 1;
            }
        }
        return ans;
    }

    private boolean canMakeZero(int nums[],int [][]queries,int k){
        int length = nums.length;
        int freq[] = new int[length+1];
        for(int i=0;i<k;i++){
            int query[] = queries[i];
            freq[query[0]] += query[2];
            freq[query[1]+1] -= query[2];
        }
        int op=0;
        for(int i=0;i<length;i++){
            op += freq[i];
            if(op < nums[i]) return false;
        }
        return true;
    }
}
