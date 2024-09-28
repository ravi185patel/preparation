package career.datastructure.arrays.easy;

public class LongestSubArrayWithSumK {
    public static void main(String[] args) {
        int arr[] = {10, 5, 2, 7, 1, 9}, k = 15;
//        int arr[] = {-1, 2, 3}, k = -1;
        System.out.println(lenOfLongSubarr(arr,arr.length,k));
    }


    // Function for finding maximum and value pair
    public static int lenOfLongSubarr (int A[], int N, int K) {
        //Complete the function

        int left =0,right =0,sum=A[0],maxLen=0;

        while(right < N){
            while(left <= right && sum > K){
                sum -= A[left];
                left++;
            }

            if(sum == K){
                maxLen = Math.max(maxLen,right-left+1);
            }

            right++;
            if(right < N){
                sum +=A[right];
            }
        }
        return maxLen;
    }

}
