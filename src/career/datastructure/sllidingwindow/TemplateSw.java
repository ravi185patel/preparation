package career.datastructure.sllidingwindow;

/*
| Problem Type          | Window Type | Key Data Structure |
| --------------------- | ----------- | ------------------ |
| Max sum size K        | Fixed       | Running sum        |
| K distinct            | Variable    | HashMap            |
| No repeat             | Variable    | HashMap + shrink   |
| Subarray with product | Variable    | multiplication     |
| Min window substring  | Variable    | 2 maps             |
| Count subarrays       | Variable    | prefix or hash     |

In problem asked for fixed window by providing K( size or element of window)
Or asked find elements sum or other <= or >=k call variable size of window.

✅ Pattern 1: FIXED-SIZE WINDOW

Use this when:
window size K is given
you need max/min/average/count for every K-length window

✅ Pattern 2: VARIABLE-SIZE WINDOW

Window expands AND shrinks based on a condition.


📌 CHEATSHEET Summary
Pattern A: Fixed
j moves always
i moves only when window hits size K
no while-loop
O(n)

Pattern B: Variable
j moves
i moves based on condition
has a while-loop
O(n) amortized

 */
public class TemplateSw {
    public static void main(String[] args) {

    }

//    Fixed Window (Size K) -> sub array/continues character/subsequence
// integer array or string or sentence
// integer arr -> Only +, only -. mix of + and -
// some time asked for count of sub array

    public static void fixedWindowTemplate(int n  ,int arr[],int K){
        int i = 0, sum = 0;

        for (int j = 0; j < n; j++) {
            // EXPAND
            sum += arr[j];

            // When window hits size K
            if (j - i + 1 == K) {

                // SHRINK
                sum -= arr[i];
                i++;
            }
        }
    }

    public static void variableWindowTemplate(int n  ,int arr[],int K){
        int i = 0;

//        for (int j = 0; j < n; j++) {
//            ADD(arr[j]);
//
//            while (condition is violated) {
//                REMOVE(arr[i]);
//                i++;
//            }
//
//            // valid window
////            update result;
//        }
    }

}
