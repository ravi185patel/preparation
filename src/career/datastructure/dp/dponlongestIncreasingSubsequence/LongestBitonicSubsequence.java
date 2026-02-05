package career.datastructure.dp.dponlongestIncreasingSubsequence;

import java.util.Arrays;

public class LongestBitonicSubsequence {
    public static void main(String[] args) {
        System.out.println(lbs(new int[]{5, 1, 4, 2, 3, 6, 8, 7}));
        System.out.println(lbs(new int[]{10, 20, 30, 40, 50, 40, 30, 20}));
        System.out.println(lbs(new int[]{10, 20, 30, 40, 50}));
        System.out.println(lbs(new int[]{498,482,481,472,467,460,458,445,427,414,409,405,402,392,390,385,370,354,346,335,333,320,313,307,301,299,282,280,275,274,267,262,256,249,235,227,214,194,176,142,138,135,115,113,111,106,96,90,90,86,81,80,79,75,71,33,24,20,5,4,1}));
    }

    public static int lbs(int nums[]){
        return solveDp(nums);
    }

    public static int solveDp(int nums[]){
        int n = nums.length;
        int right[]=new int[n+1];
        int left[]=new int[n+1];
        boolean leftIncrease = false,rightIncrease =false;
        Arrays.fill(right,1);
        Arrays.fill(left,1);
        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                if(left[j]+1 > left[i] && nums[i] > nums[j]){
                    left[i] = left[j] + 1;
                    leftIncrease=true;
                }
            }
        }

//        if(leftIncrease==false) return -1;
        for(int i=n-1;i>=0;i--){
            for(int j=n-1;j>i;j--){
                if(right[j]+1 > right[i] && nums[i] > nums[j]){
                    right[i] = right[j] + 1;
                    rightIncrease = true;
                }
            }
        }

//        if(rightIncrease == false) return -1;

//        System.out.println(Arrays.toString(left));
//        System.out.println(Arrays.toString(right));

        int maxLength =0;
        for(int i=0;i<n;i++){
            maxLength = Math.max(maxLength,left[i]+right[i]);
        }
        return maxLength-1;
    }
}
