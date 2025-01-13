package career.thirtydays.dp.onedimensional_fibonaccistyle;

/*
https://www.naukri.com/code360/problems/frog-jump_3621012

There is a frog on the '1st' step of an 'N' stairs long staircase.
The frog wants to reach the 'Nth' stair. 'HEIGHT[i]' is the height of the '(i+1)th' stair.
If Frog jumps from 'ith' to 'jth' stair,
the energy lost in the jump is given by absolute value of ( HEIGHT[i-1] - HEIGHT[j-1] ).
If the Frog is on 'ith' staircase, he can jump either to '(i+1)th' stair or to '(i+2)th' stair.
Your task is to find the minimum total energy used by the frog to reach from '1st' stair to 'Nth' stair.


For Example
If the given ‘HEIGHT’ array is [10,20,30,10],
the answer 20 as the frog can jump from 1st stair to 2nd stair (|20-10| = 10 energy lost)
and then a jump from 2nd stair to last stair (|10-20| = 10 energy lost).
So, the total energy lost is 20.


Apprach
1] recursion
2] memo
3] dp
i     i+1      i+2
10 -> 20-10 -> (30-20,30-10)
10 -> 20-10=10 -> (30-20)+10 = 20 -> (30-10)+20 =30
10 -> 0     -> (30-10) = 20  -> out 20 ans min
 */
public class FrogJump {
    public static void main(String[] args) {
        int n=4,heights[]={10,20,30,10};
//        int n=3,heights[]={10,50,10};
//        int n=8,heights[]={7,4,4,2,6,6,3,4};
//        int n=6,heights[]={4,8,3,10,4,4};
//        int n=9,heights[]={7,5,5,8,4,9,1,1,10};

    //    Arrays.fill(memo,-1);
    //    return frogJumpRecMemo(n-1,heights,memo);
        System.out.println(frogJumpRecDp(n-1,heights));
}

    public static int frogJumpRec(int n,int arr[]){
        if(n == 0){
            return 0;
        }

        int jumpOne = frogJumpRec(n-1,arr) + Math.abs(arr[n]-arr[n-1]);
        int jumpTwo=Integer.MAX_VALUE;
        if(n > 1){
            jumpTwo=frogJumpRec(n-2,arr) + Math.abs(arr[n]-arr[n-2]);
        }
        return Math.min(jumpOne,jumpTwo);
    }

    public static int frogJumpRecMemo(int n,int arr[],int memo[]){
        if(n == 0){
            return 0;
        }

        if(memo[n] != -1){
            return memo[n];
        }

        int jumpOne = frogJumpRecMemo(n-1,arr,memo) + Math.abs(arr[n]-arr[n-1]);
        int jumpTwo=Integer.MAX_VALUE;
        if(n > 1){
            jumpTwo=frogJumpRecMemo(n-2,arr,memo) + Math.abs(arr[n]-arr[n-2]);
        }
        return memo[n]=Math.min(jumpOne,jumpTwo);
    }

    public static int frogJumpRecDp(int n,int heights[]){
        int dp[]=new int[n+1];
        dp[0]=0;
        for(int i=1;i<=n;i++){
            int one = dp[i-1] + Math.abs(heights[i]-heights[i-1]);
            int two = Integer.MAX_VALUE;
            if(i > 1) {
                two = dp[i-2] + Math.abs(heights[i] - heights[i - 2]);
            }
            dp[i]=Math.min(one,two);
        }
        return dp[n];
    }
}
