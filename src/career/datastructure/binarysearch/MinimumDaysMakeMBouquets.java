package career.datastructure.binarysearch;

/*

https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/

As days increase, more flowers will bloom.

✅ So if it’s possible to make bouquets on day d,
➡️ it's also possible on day d+1, d+2, ...

And if it’s impossible on day d,
➡️ it remains impossible on day d-1, d-2, ...

💡 This is a monotonic condition — exactly the pattern where binary search works.
| Observation                     | Leads to                            |
| ------------------------------- | ----------------------------------- |
| We need to **minimize a value** | Think binary search on result       |
| Condition is **monotonic**      | ✅ Binary search applies             |
| Each check is **linear time**   | Total is `O(n * log(max bloomDay))` |


 */
public class MinimumDaysMakeMBouquets {
    public static void main(String[] args) {

    }
    public int roseGarden(int n, int[] bloomDay, int k, int m) {
        long totalFlowers = (long) m * k;
        if (bloomDay.length < totalFlowers) return -1; // Not enough flowers

        int low =1,high=0;
        for(int bloom:bloomDay){
            high = Math.max(high,bloom);
        }
        while(low < high){
            int mid = low + ( high - low )/2;
            if(canMake(bloomDay,m,k,mid)){
                high = mid;
            }else{
                low = mid + 1;
            }
        }

        return low;
    }

    private boolean canMake(int []bloomDay,int m,int k,int mid){
        int bouquets =0;
        int flower=0;
        for(int bloom:bloomDay) {
            if(bloom <= mid){
                flower++;
                if(flower >= k){
                    bouquets++;
                    flower=0;
                }
            }else{
                flower=0;
            }
        }
        return bouquets >= m;
    }
}
