package career.sixmonthssep.dp.subsequence;

/*
https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1

 */
public class SubSetSum {
    public static void main(String[] args) {
        int  arr[]={1,5,11,5}, target = 10;
        System.out.println(findTargetSum(arr,target/2));
    }

    private static boolean findTargetSum(int arr[],int target){
        boolean prev[]=new boolean[target+1];
        prev[0]=true;
        if(arr[0] <= target){
            prev[arr[0]] = true;
        }

        for(int i=1;i<arr.length;i++){
            boolean curr[] = new boolean[target+1];
            for(int j=0;j<=target;j++){
                boolean noTaken = prev[j]; // prev means previous calculated row
                boolean taken = false;
                if(j >= arr[i]){
                    taken = prev[j-arr[i]];
                }
                curr[j] = noTaken || taken;
            }
            prev = curr.clone();
        }
        return prev[target];
    }
}
