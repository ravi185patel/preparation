package career.interview.priceline.bs;

import java.util.Arrays;

public class KokoEatingBananas {
    public static void main(String[] args) {
        System.out.println(minEatingSpeed(new int[]{3,6,7,11},8));
    }
    public static int minEatingSpeed(int[] piles, int h) {
        int  min = Integer.MAX_VALUE;
        int  max = Integer.MIN_VALUE;

        for(int pile:piles){
            min = Math.min(min,pile);
            max = Math.max(max,pile);
        }

        int low = 1,high = max;
        int ans=-1;
        while(low < high){
            int mid = low + (high - low)/2;
            int hour = isPossibleToEatAllBanansInH(piles,mid);
            if(hour <= h){
                ans= mid;
                high = mid-1;
            }else{
                low = mid + 1;
            }
        }

        return ans;
    }

    public static int isPossibleToEatAllBanansInH(int[]piles,int k){
        int hours=0;
        for(int i:piles){
            hours+= (i+k-1)/k;
        }
        return hours;
    }
}
