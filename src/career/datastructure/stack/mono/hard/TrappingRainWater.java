package career.datastructure.stack.mono.hard;

import java.util.Arrays;

/*
clarification question
1] did array contains negative value
2] did array sorted ?
3] range of n


approach
1] brute force -> O(n2)
2] left and right pointer array max -> O(N) and O(2N)
3] two pointer -> O(N)
 */
public class TrappingRainWater {
    public static void main(String[] args) {
//        int height[] = {0,1,0,2,1,0,1,3,2,1,2,1};
        int height[] = {4,2,0,3,2,5};
        System.out.println(trap(height));
    }

    public static int trap(int[] height) {
//        return trapBf(height);
//        return trapBfOp(height);
        return trapBfOpWS(height);
    }


    private static int trapBf(int[] height){
        int total=0;
        int n = height.length;
        for(int i=0;i<n;i++){
            int curr = height[i];
            int leftMax=0,rightMax=0;
            for(int left=i;left>=0;left--){
                leftMax = Math.max(leftMax,height[left]);
            }
            for(int right = i;right<n;right++){
                rightMax = Math.max(rightMax,height[right]);
            }

            total += Math.min(leftMax,rightMax)-curr;
        }

        return total;
    }

    private static int trapBfOp(int[] height){
        int total=0;
        int n = height.length;
        int leftMaxArr[]=new int[n];
        int rightMaxArr[]=new int[n];
        int leftMax=0,rightMax=0;
        for(int left=n-1;left>=0;left--){
            leftMax = Math.max(leftMax,height[left]);
            leftMaxArr[left] = leftMax;
        }

        for(int right = 0;right<n;right++){
            rightMax = Math.max(rightMax,height[right]);
            rightMaxArr[right]=rightMax;
        }

        for(int i=0;i<n;i++){
            int curr = height[i];
            total += Math.min(leftMaxArr[i],rightMaxArr[i])-curr;
        }

        return total;
    }

    private static int trapBfOpWS(int[] height){
        int total=0;
        int n = height.length;
        int left=0,right=n-1,leftMax=0,rightMax=0;

        while(left <= right){
            if(height[left] <= height[right]){
                leftMax = Math.max(leftMax,height[left]);
                total += leftMax - height[left];
                left++;
            }else{
                rightMax = Math.max(rightMax,height[right]);
                total += rightMax - height[right];
                right--;
            }
        }
        return total;
    }
}
