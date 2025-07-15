package career.sixmonthssep.leetcode76.twopointer;

public class ContainerWithMostWater {
    public static void main(String[] args) {
        int height[] = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }
    public static int maxArea(int[] height) {
        int left=0,right=height.length-1,n=height.length;
        int maxArea=0;
        while(left < right){
            maxArea = Math.max((right-left)*Math.min(height[left],height[right]),maxArea);
            if(height[left] == height[right]){
                left++;
                right--;
            }else if(height[left] > height[right]){
                right--;
            }else{
                left++;
            }
        }
        return maxArea;
    }
}
