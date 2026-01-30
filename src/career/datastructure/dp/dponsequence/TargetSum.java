package career.datastructure.dp.dponsequence;

public class TargetSum {
    public static void main(String[] args) {
        System.out.println(findTargetSumWays(3,new int[]{1,1,1,1,1}));
        System.out.println(findTargetSumWays(1,new int[]{1}));
    }

    public static int findTargetSumWays(int target,int nums[]){
        return findTargetSumWaysRec(nums.length-1,target,nums);
    }

    public static int findTargetSumWaysRec(int index,int target,int nums[]){

        if(index < 0){
            return target == 0 ?1 :0;
        }

        int plus = findTargetSumWaysRec(index-1,target-nums[index],nums);
        int minus = findTargetSumWaysRec(index-1,target+nums[index],nums);
        return plus + minus;
    }
}
