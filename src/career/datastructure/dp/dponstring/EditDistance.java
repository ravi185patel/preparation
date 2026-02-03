package career.datastructure.dp.dponstring;

public class EditDistance {
    public static void main(String[] args) {
        System.out.println(minDistance("horse","ros"));
        System.out.println(minDistance("intention","execution"));
        System.out.println(minDistance("dinitrophenylhydrazine","benzalphenylhydrazone"));
    }
    public static int minDistance(String word1, String word2) {
//        return solve(word1.toCharArray(),word2.toCharArray(),word1.length()-1,word2.length()-1);
        return solveDp(word1.toCharArray(),word2.toCharArray());
    }

    public static int solve(char []w1,char w2[],int ind1,int ind2){
        if(ind2 < 0){
            return ind1+1;
        }

        if(ind1 < 0){
            return ind2+1;
        }

        if(w1[ind1] == w2[ind2]){
            return solve(w1,w2,ind1-1,ind2-1);
        }else {
            int insert = 1 + solve(w1, w2, ind1, ind2 - 1);
            int delete = 1 + solve(w1, w2, ind1 - 1, ind2);
            int replace = 1 + solve(w1, w2, ind1 - 1, ind2 - 1);
            return Math.min(insert,Math.min(delete,replace));
        }
    }

    public static int solveDp(char []w1,char w2[]){
        int n = w1.length;
        int m = w2.length;
        int dp[][]=new int[n+1][m+1];
        for(int i=0;i<=n;i++){
            dp[i][0]=i;
        }
        for(int j=0;j<=m;j++){
            dp[0][j]=j;
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(w1[i-1]==w2[j-1]){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = 1 + (Math.min(dp[i-1][j],Math.min(dp[i-1][j-1],dp[i][j-1])));
                }
            }
        }

        return dp[n][m];
    }
}
