package career.sixmonthssep.math;

/*


 */
public class MissingAndRepeatingNumber {
    public static void main(String[] args) {
        int[] arr = {4, 3, 6, 2, 1, 1};
        find(arr);
    }

    public static void findNormal(int[] nums) {
        int n= nums.length;
        int freq[]=new int[n+1];
        int res[]=new int[2];
        int total = (n*(n+1))/2;
        for(int index=0;index<n;index++){
            int no = nums[index];
            total -=no;
            freq[no]++;
            if(freq[no] > 1){
                res[0]=no;
            }
        }
        total+=res[0];
        res[1]=total;
    }
    public static void findXor(int[] arr) {
        int n = arr.length;
        int xor1 = 0;

        // Step 1: Get the XOR of all array elements
        for (int i = 0; i < n; i++) {
            xor1 ^= arr[i];
        }

        // Step 2: XOR the result with numbers from 1 to n
        for (int i = 1; i <= n; i++) {
            xor1 ^= i;
        }

        // Step 3: xor1 = missing ⊕ repeating
        // Get rightmost set bit
        int setBit = xor1 & ~(xor1 - 1);

        // Step 4: Divide elements into two buckets and XOR separately
        int x = 0, y = 0; // x and y are the two numbers (one missing, one repeating)

        for (int i = 0; i < n; i++) {
            if ((arr[i] & setBit) != 0)
                x ^= arr[i];
            else
                y ^= arr[i];
        }

        for (int i = 1; i <= n; i++) {
            if ((i & setBit) != 0)
                x ^= i;
            else
                y ^= i;
        }

        // Step 5: Determine which is missing and which is repeating
        boolean xIsRepeating = false;
        for (int num : arr) {
            if (num == x) {
                xIsRepeating = true;
                break;
            }
        }

        int repeating = xIsRepeating ? x : y;
        int missing = xIsRepeating ? y : x;

        // Output results
        System.out.println("Repeating number: " + repeating);
        System.out.println("Missing number: " + missing);
    }
    public static void find(int[] arr) {
        int n = arr.length;

        long sumN = (n * (n + 1)) / 2;
        long sumN2 = (n * (n + 1) * (2L * n + 1)) / 6;

        long sum = 0, sumSq = 0;
        for (int num : arr) {
            sum += num;
            sumSq += (long) num * num;
        }

        long diff = sumN - sum;           // x - y
        long diffSq = sumN2 - sumSq;      // x^2 - y^2

        long sumXY = diffSq / diff;       // x + y

        long x = (diff + sumXY) / 2;
        long y = x - diff;

        System.out.println("Missing Number: " + x);
        System.out.println("Repeating Number: " + y);
    }



}
