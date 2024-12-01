package career.interview.google;

/*

 */

public class MaxFreqNumber {
    public static void main(String[] args) {
        int nums[]={1,2,2,3,3,3,3,4,4,5,6};
//        int nums[]={1,1,1,2,2,4,4,5,6};
//        int nums[]={1,1,1,2,2,4,4,5,6,6,6,6};
//        int nums[]={1};
//        int nums[]={1,1,1};
        System.out.println(findFreqNo(nums));
    }

    private static int findFreqNo(int []nums){
        int count =1,maxCount=0;
        for(int i=1;i<nums.length;i++){
            if(nums[i]==nums[i-1]){
                count++;
            }else{
                count=1;
            }
            maxCount= Math.max(maxCount,count);
        }
        maxCount= Math.max(maxCount,count);
        return maxCount;
    }

}
