package career.takeuforward.atoz.array.medium;

import java.util.HashMap;

public class CountSubArraySumEqK {
    public static void main(String[] args) {
        int N = 4,
//                array[] = {3, 1, 2, 4} , k = 6
        array[] = {-3, 1, 2, 3} , k=3

                ; // output The subarrays that sum up to 6 are [3, 1, 2] and [2, 4]. cnt =2;
        // if arrays contains negative value
        System.out.println(findCntSubArraySumEqK(array,k,N));
        System.out.println(findCntSubArrayPosAndNegSumEqK(array,k,N));
    }

    public static int findCntSubArraySumEqK(int arr[],int k,int N){
        int start=0,end=0;
        int cnt=0;
        int sum=0;
        while(end < N){
            sum+=arr[end];
            if(sum == k){
                cnt++;
            }else if(sum > k){
                while(sum > k && start <= end){
                    sum-=arr[start];
                    start++;
                }
                if(sum == k){
                    cnt++;
                }
            }
            end++;
        }
        return cnt;
    }

    public static int findCntSubArrayPosAndNegSumEqK(int arr[],int k,int N){
        HashMap<Integer,Integer> hmap = new HashMap<>();
        int sum=0,cnt=0;
        hmap.put(0,1);
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];

            int remove = sum - k;

            cnt += hmap.getOrDefault(remove,0);

            hmap.put(sum,hmap.getOrDefault(sum,0)+1);
        }


        return cnt;
    }
}
