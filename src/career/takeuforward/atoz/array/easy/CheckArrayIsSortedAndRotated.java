package career.takeuforward.atoz.array.easy;

public class CheckArrayIsSortedAndRotated {
    public static void main(String[] args) {
        int nums []={3,4,5,1,2};
        System.out.println(check(nums));
    }
    public static boolean check(int[] nums) {
        int length = nums.length, count=0;

        for(int i=0;i<length;i++){
            if(nums[i] > nums[(i+1)%length]){ // Here ' % ' is mainly used to compare the last and first elements in the last iteration of the FOR loop.
                count++;
            }
            if(count > 1){
                return false;
            }
        }
        return true;
    }
}
