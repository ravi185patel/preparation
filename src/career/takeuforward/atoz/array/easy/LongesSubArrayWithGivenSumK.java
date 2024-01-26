package career.takeuforward.atoz.array.easy;

public class LongesSubArrayWithGivenSumK {
    public static void main(String[] args) {
//       int N = 3, k = 5, array[] = {2,3,5};
//       int N = 5, k = 10, array[] = {2,3,5,1,9};

        int N= 7, k = 3, array[]={1,2,3,1,1,1,1};


        int res = longestSubArrayWithGivenSumK(array,N,k);
       System.out.println(res);

    }

    public static int longestSubArrayWithGivenSumK(int arr[],int N,int K){
        int max=0;
        int sum =arr[0]; // why assigned -> if first element is equal to K or it can be more then K
        int start =0, end= 0;
        while(end < N){

            while(start <= end && sum > K){ // decrease sum by substract from starting to make it equal or less then K
                sum -= arr[start];
                start++;
            }

            if(sum == K){
                max = Math.max(max,end-start+1);
            }

            end++;
            if(end < N) sum+=arr[end]; // after inc, is end pointer greater then length of arr.
        }
        return max;
    }
}
