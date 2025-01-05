package career.datastructure.swapline;

import java.util.Arrays;

public class RangeAddition {
    public static void main(String[] args) {
        int length = 10, updates[][] = {{2,4,6}, {5,6,8}, {1,9,-4}};
        int res[] = getModifiedArray(length,updates);
        System.out.println(Arrays.toString(res));
    }

    public static int[] getModifiedArray(int length, int[][] updates) {
        int res[]=new int[length];
        for(int update[]:updates){
            int start=update[0];
            int end = update[1]+1;
            int val = update[2];
            res[start]+=val;
            if(end < length){
                res[end]-=val;
            }
        }

        for(int i=1;i<length;i++){
            res[i]+=res[i-1];
        }

        return res;
    }
}
