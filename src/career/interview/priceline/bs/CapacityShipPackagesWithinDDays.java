package career.interview.priceline.bs;

import java.util.Arrays;

public class CapacityShipPackagesWithinDDays {
    public static void main(String[] args) {
        System.out.println(shipWithinDays(new int[]{1,2,3,4,5,6,7,8,9,10},5));
        System.out.println(shipWithinDays(new int[]{3,2,2,4,1,4},3));
        System.out.println(shipWithinDays(new int[]{1,2,3,1,1},4));
    }

    public static int shipWithinDays(int[] weights, int days) {
        int left = Integer.MIN_VALUE,right=0;
        for(int weight:weights){
            left = Math.max(left,weight); // here problem you need to start with max of them
            // and up to total weight
            right += weight;
        }
        int ans=0;
        while(left <= right){
            int mid = left + (right-left)/2;
            int dayConsume = ship(weights,mid);
            if(dayConsume <= days){
                ans = mid;
                right = mid -1;
            }else{
                left = mid + 1;
            }
        }
        return ans;
    }

    public static int ship(int []weights,int maxWeight){
        int totalDay=1;
        int w = 0;
        for(int weight:weights){
            if(w+weight > maxWeight){
                w = weight;
                totalDay++;
            }else{
                w+=weight;
            }
        }
        return totalDay;
    }

}
