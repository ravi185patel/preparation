package career.datastructure.binarysearch;

/*

https://leetcode.com/problems/koko-eating-bananas/

✅ If Koko can finish in h hours at speed k, then she can definitely finish in h hours at any speed k+1, k+2, ...

This makes the problem suitable for binary search on the answer — a common technique in algorithmic problems when the solution space is ordered (monotonic).

 */
public class KokoEatingBananas {
    public static void main(String[] args) {

    }
    public int minEatingSpeed(int[] piles, int h) {
        int  max = Integer.MIN_VALUE;

        for(int pile:piles){
            max = Math.max(max,pile);
        }


        // for(int k=1;k<=max;k++){
        //     if(isPossibleToEatAllBanansInH(piles,k) <= h){
        //         return k;
        //     }
        // }

        int low = 1,high = max;
        while(low < high){
            int mid = low + (high - low)/2;
            int hour = isPossibleToEatAllBanansInH(piles,mid);
            if(hour <= h){
                high = mid;
            }else{
                low = mid + 1;
            }
        }

        return low;
    }

    public int isPossibleToEatAllBanansInH(int piles[],int k){
        int hours = 0;
        for (int pile : piles) {
            hours += Math.ceil((double) pile / k);
        }
        return hours;
    }
}
