package career.datastructure.dp.twothreedp;

public class NinjaTrainingAndHisFriends {
    public static void main(String[] args) {
        System.out.println(minTraining(3,4,new int[][]
                        {{2, 3, 1, 2},{3, 4, 2, 2},{5, 6, 3, 5}}
                ));
    }

    public static int minTraining(int r,int c,int grid[][]){
//        return solve(0,0,c-1,r,c,grid);
        return solveDp(0,0,c-1,r,c,grid);
    }

    public static int solve(int index,int alice,int bob,int r,int c,int grid[][]){
        if(alice < 0 || bob < 0 || alice >= c|| bob >= c){
            return Integer.MIN_VALUE;
        }


        if(index == r-1){
            return alice == bob ? grid[index][alice]:grid[index][alice]+grid[index][bob];
        }

        int max=0;
        int curr = alice == bob ? grid[index][alice]:grid[index][alice]+grid[index][bob];
        for(int dirAlice=-1;dirAlice<=1;dirAlice++){
            for(int dirBob=-1;dirBob<=1;dirBob++){
                max = Math.max(max,curr + solve(index+1,alice+dirAlice,bob+dirBob,r,c,grid));
            }
        }

        return max;
    }

    public static int solveDp(int index,int alice,int bob,int r,int c,int grid[][]){
        int prev[][]=new int[r+1][c+1];

        for (int j1 = 0; j1 < c; j1++) {
            for (int j2 = 0; j2 < c; j2++) {
                if (j1 == j2) prev[j1][j2] = grid[r-1][j1];
                else prev[j1][j2] = grid[r-1][j1] + grid[r-1][j2];
            }
        }

        for (int i = r - 2; i >= 0; i--) {
            int curr[][]=new int[r+1][c+1];
            for (int j1 = 0; j1 < c; j1++) {
                for (int j2 = 0; j2 < c; j2++) {
                    int maxi = Integer.MIN_VALUE;
                    int currChoco = (j1 == j2) ? grid[i][j1] : grid[i][j1] + grid[i][j2];
                    // Try all 9 moves
                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int newJ1 = j1 + dj1;
                            int newJ2 = j2 + dj2;
                            if (newJ1 >= 0 && newJ1 < c &&
                                    newJ2 >= 0 && newJ2 < c) {
                                maxi = Math.max(maxi, currChoco + prev[newJ1][newJ2]);
                            } else {
                                maxi = Math.max(maxi, (int)-1e9);
                            }
                        }
                    }
                    curr[j1][j2] = maxi;
                }
            }
            // Move current row to next row
            prev = curr.clone();
        }
        // Answer is starting position
        return prev[0][c-1];
    }

}
