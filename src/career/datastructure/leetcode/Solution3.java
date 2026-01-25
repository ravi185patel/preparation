package career.datastructure.leetcode;

class Solution3 {
    public static void main(String[] args) {
        System.out.println(minimumPrefixLength(new int[]{1,-1,2,3,3,4,5}));
        System.out.println(minimumPrefixLength(new int[]{4,3,-2,-5}));
        System.out.println(minimumPrefixLength(new int[]{1,2,3,4}));
        System.out.println(minimumPrefixLength(new int[]{-1000000000,2,3,4,-1000000000}));
        System.out.println(minimumPrefixLength(new int[]{-1000000000,2,3,4,1000000000}));
        System.out.println(minimumPrefixLength(new int[]{1,1,1}));
        System.out.println(minimumPrefixLength(new int[]{1,2,2}));
        System.out.println(minimumPrefixLength(new int[]{2,2,3}));
    }
    public static int minimumPrefixLength(int[] nums) {
        int count=0;
        for(int i=1;i<nums.length;i++){
            if(nums[i-1] >= nums[i]){
                count=0;
            }else{
                count++;
            }
        }
        return nums.length-1-count;
    }
}