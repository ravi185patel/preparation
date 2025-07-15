package career.interview.amazon;

import java.util.Arrays;

public class Problem1 {
    public static void main(String[] args) {

        int []nums={1,9,6};
    }

    /*

    1,9,6
    1,6,9
    n=3;
    9*3,6*2,1*1  = 27 Y
    9*3,6*1,1*2 = 27

    6*3,9*2,1*1 = 18,18 X
    6*3,9*1,1*2

    1*3,9*2,6*1
    1*3,9*1,6*2


     */
    public static void findCnt(int nums[]){
        Arrays.sort(nums);

    }
}
