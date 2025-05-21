package career.website.leetcode.contest477.c;

public class sol2 {
    public static void main(String[] args) {
        int grid[][] = {{1,4},{2,3}};
//        int grid[][] = {{65917,79299}};
//        int grid[][] = {{42047},{57775},{99822}};
//        int grid[][] = {{1,3},{2,4}};
        int m = grid.length;
        int n = grid[0].length;
        int row[]=new int[m];
        int sum=0,totalSUm=0;
        for(int i=0;i<m;i++){
            sum=0;
            for(int j=0;j<n;j++){
                sum+=grid[i][j];
            }
            totalSUm+=sum;
            row[i]=sum;
        }
        if(isValid(totalSUm,m,row)){
            System.out.println(true);
            return;
        }
        int col[]=new int[n];
        sum=0;totalSUm=0;
        for(int j=0;j<n;j++){
            sum=0;
            for(int i=0;i<m;i++){
                sum+=grid[i][j];
            }
            totalSUm+=sum;
            col[j]=sum;
        }
        if(isValid(totalSUm,n,col)){
            System.out.println(true);
            return;
        }
    }

    public static boolean isValid(int totalSUm,int len,int arr[]){

        int rightSum=totalSUm,leftSum=0;
        for(int i=0;i<len;i++){
            leftSum+=arr[i];
            rightSum-=arr[i];
            if(leftSum==rightSum){
                return true;
            }
        }

        return false;
    }
}
