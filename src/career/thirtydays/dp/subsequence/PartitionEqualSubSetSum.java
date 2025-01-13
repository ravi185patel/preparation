package career.thirtydays.dp.subsequence;

/*
https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1
 */
public class PartitionEqualSubSetSum {
    public static void main(String[] args) {

        int  arr[]={1,5,11,5}, target = 10;

        for(int i:arr){
            target+=i;
        }

        if(target%2 != 0){
            System.out.println(false);
        }else{
            System.out.println(findTargetSum(arr,target/2));
        }
    }

    private static boolean findTargetSum(int arr[],int target){
        boolean prev[]=new boolean[target+1];
        prev[0]=true;
        if(arr[0] <= target){
            prev[arr[0]] = true;
        }

        for(int i=1;i<arr.length;i++){
            boolean curr[] = new boolean[target+1];
            curr[0] = true;
            for(int j=1;j<=target;j++){
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
