package career.datastructure.binarysearch;

import java.util.Arrays;

/*
https://takeuforward.org/plus/dsa-concept-revision/day-6/aggressive-cows

🎯 Goal:
Maximize the minimum distance between any two cows.

If you place cows too close, the minimum distance is small.
The best solution will spread cows apart as much as possible, but still keep them in the stalls.

🔑 Key Insight – Binary Search on Distance
You’re searching for the largest value of minimum distance d such that it's still possible to place all k cows with at least d distance between them.

This is a monotonic condition:

If it’s possible to place cows ≥ d apart
→ it’s also possible for all smaller distances.

If it’s not possible for a distance d,
→ it’s also impossible for any d + 1, d + 2, ...

✅ That’s exactly what makes binary search on the answer a fit.


🚀 In short:
Formula	Bias	When to Use
left + (right - left) / 2	Left	Standard binary search (minimization)
(left + right + 1) / 2	Right	Maximization problems (like Aggressive Cows)


| Goal                                        | Use                               | Bias  |
| ------------------------------------------- | --------------------------------- | ----- |
| Find smallest possible value (minimization) | `mid = left + (right - left) / 2` | Left  |
| Find largest possible value (maximization)  | `mid = (left + right + 1) / 2`    | Right |



What it does:
Computes the middle value between left and right
Biases mid toward the left when the range has an even number of elements
Safe from integer overflow
Very common in problems like standard binary search, or minimization problems

🚫 When It Causes Problems (e.g., Aggressive Cows)
Let's look at this code:

while (left < right) {
    int mid = left + (right - left) / 2;
    if (canPlaceCows(mid)) {
        left = mid;
    } else {
        right = mid - 1;
    }
}
Suppose:

left = 3, right = 4
mid = 3 + (4 - 3) / 2 = 3
Now:

If canPlaceCows(3) returns true, we do left = mid, i.e. left = 3

But left is still 3 and right = 4, so next time we get mid = 3 again

→ Infinite loop or stalls (because left doesn't progress)

✅ The Fix: Right-biased Mid
Change to:

int mid = (left + right + 1) / 2;
Now:

left = 3, right = 4
mid = (3 + 4 + 1) / 2 = 4
Now if canPlaceCows(4) is true:

We set left = 4

Loop condition left < right becomes false, and we terminate

→ The search progresses and terminates cleanly

 */
public class AggressiveCows {
    public static void main(String[] args) {

    }
    public int aggressiveCows(int[] nums, int k) {
        Arrays.sort(nums);

        int low =0,high = nums[nums.length-1]-nums[0];

        while(low < high){
            int mid = (low + high + 1) / 2;
            if(canMake(nums,k,mid)){
                low = mid;
            }else{
                high = mid - 1;
            }
        }

        return low;
    }

    public static boolean canMake(int [] stalls,int k,int distance){
        int count = 1;
        int lastPos = stalls[0];

        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - lastPos >= distance) {
                count++;
                lastPos = stalls[i]; // to make previous point available to get difference
                if (count == k) return true;
            }
        }

        return false;
    }
}