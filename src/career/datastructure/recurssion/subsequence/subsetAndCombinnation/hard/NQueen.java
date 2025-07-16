package career.datastructure.recurssion.subsequence.subsetAndCombinnation.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NQueen {
    public static void main(String[] args) {
//        String[][] board = {{".Q..","...Q","Q...","..Q."},{"..Q.","Q...","...Q",".Q.."}};
        NQueen nQueen = new NQueen();
        System.out.println(nQueen.solveNQueens(4));
        System.out.println(nQueen.solveNQueens1(4));
        System.out.println(nQueen.solveNQueens1(9));
    }
    /*
   max 9 row and columns

   1] place queen
   2] validate queen placement
   3] yes -> next queen placement
   4] no -> backtrack choose other placement for queen
   5] repeate it

   VERY VERY important point
   1] Here you don't need recursive for column but for row
   here column should be sequential if you placed at 1
   try for 2 to n col and 1 to n row.
   Row need recursive call which means
   for 1 column you need to try all 0 to n row position.

   */
    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> res = new ArrayList<>();
        boolean placed[][]=new boolean[n][n];
        placedQeuen(n,0,res,placed);
        // System.out.println(res);
        return res;
    }

    private static void placedQeuen(int n,int col,List<List<String>> grid,boolean placed[][] ){

        if(n == col){
            List<String> girdRow = new ArrayList<>();
            for(int i=0;i<n;i++){
                StringBuilder sb= new StringBuilder();
                for(int j=0;j<n;j++){
                    if(placed[i][j]){
                        sb.append("Q");
                    }else{
                        sb.append(".");
                    }
                }
                girdRow.add(sb.toString());
                sb.setLength(0);
            }
            grid.add(girdRow);
            return;
        }
        for(int row = 0;row<n;row++){
            if(safe(placed,row,col)){
                placed[row][col]=true;
                placedQeuen(n,col+1,grid,placed);
                placed[row][col]=false;
            }
        }
    }
    public static boolean safe(boolean placed[][], int row, int col) {
        int r = row, c = col;

        // Check upper left diagonal
        while (r >= 0 && c >= 0) {
            if (placed[r][c]) return false;
            r--;
            c--;
        }

        // Reset to the original position
        r = row;
        c = col;

        // Check left side
        while (c >= 0) {
            if (placed[r][c]) return false;
            c--;
        }

        // Reset to the original position
        r = row;
        c = col;

        // Check lower left diagonal
        while (r < placed.length && c >= 0) {
            if (placed[r][c]) return false;
            r++;
            c--;
        }

        // If no queens are found, it's safe
        return true;
    }
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                board[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<>();
        int leftRow[]=new int[n];
        int upperDaigonal[] = new int[n*2 - 1];
        int lowerDaigonal[] = new int[n*2 - 1];

        dfs(0,board,res,leftRow,upperDaigonal,lowerDaigonal);
        return res;
    }

    private void dfs(int col,char[][]board,List<List<String>> res,int leftRow[],
                     int upperDaigonal[],int lowerDaigonal[]){
        if(col == board.length){
            res.add(construct(board));
            return;
        }

        for(int row = 0;row<board.length;row++){
            if(leftRow[row] == 0 && upperDaigonal[row+col]==0 && lowerDaigonal[board.length- 1 + col- row] == 0){
                leftRow[row] = 1;
                upperDaigonal[row+col]=1;
                lowerDaigonal[board.length- 1 + col- row] = 1;
                board[row][col] = 'Q';
                dfs(col+1,board,res,leftRow,upperDaigonal,lowerDaigonal);
                board[row][col] = '.';
                leftRow[row] = 0;
                upperDaigonal[row+col]=0;
                lowerDaigonal[board.length- 1 + col- row] = 0;
            }
        }
    }

    private List < String > construct(char[][] board) {
        List < String > res = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }


    public boolean safe(List<String> board, int row, int col) {
        int r = row, c = col;

        // Check upper left diagonal
        while (r >= 0 && c >= 0) {
            if (board.get(r).charAt(c) == 'Q') return false;
            r--;
            c--;
        }

        // Reset to the original position
        r = row;
        c = col;

        // Check left side
        while (c >= 0) {
            if (board.get(r).charAt(c) == 'Q') return false;
            c--;
        }

        // Reset to the original position
        r = row;
        c = col;

        // Check lower left diagonal
        while (r < board.size() && c >= 0) {
            if (board.get(r).charAt(c) == 'Q') return false;
            r++;
            c--;
        }

        // If no queens are found, it's safe
        return true;
    }

    // Function to place queens on the board
    public void func(int col, List<List<String>> ans, List<String> board) {
        // If all columns are filled, add the solution to the answer
        if (col == board.size()) {
            ans.add(new ArrayList<>(board));
            return;
        }

        // Try placing a queen in each row for the current column
        for (int row = 0; row < board.size(); row++) {
            // Check if it's safe to place a queen
            if (safe(board, row, col)) {
                // Place the queen
                char[] charArray = board.get(row).toCharArray();
                charArray[col] = 'Q';
                board.set(row, new String(charArray));

                // Recursively place queens in the next columns
                func(col + 1, ans, board);

                // Remove the queen and backtrack
                charArray[col] = '.';
                board.set(row, new String(charArray));
            }
        }
    }

    // Solve the N-Queens problem
    public List<List<String>> solveNQueens1(int n) {
        // List to store the solutions
        List<List<String>> ans = new ArrayList<>();
        // Initialize the board with empty cells
        List<String> board = new ArrayList<>();
        String s = ".".repeat(n);
        for (int i = 0; i < n; i++) {
            board.add(s);
        }

        // Start placing queens from the first column
        func(0, ans, board);
        return ans;
    }

}
