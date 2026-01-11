package career.datastructure.recurssion.pattern.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueenPlacment {
    public static void main(String[] args) {
        System.out.println(solveNQueens(4));
    }
    public static List<List<String>> solveNQueens(int n) {
        //your code goes here
        List<List<String>> uniquePlacements = new ArrayList<>();
        char board[][]=new char[n][n];
        for(char row[]:board){
            Arrays.fill(row,'.');
        }
        solvePlacement(0,board,uniquePlacements);
        return uniquePlacements;
    }

    public static void solvePlacement(int col,char board[][],List<List<String>> uniquePlacements){
        if(col == board.length){
            List<String> uniquePlacement = new ArrayList<>();
            for(char row[]:board){
                uniquePlacement.add(new String(row));
            }
            uniquePlacements.add(uniquePlacement);
            return;
        }

        for(int row=0;row<board.length;row++){
            if(isQueenSafe(col,row,board)){
                board[row][col] = 'Q';
                solvePlacement(col+1,board,uniquePlacements);
                board[row][col] = '.';
            }
        }
    }


    public static boolean isQueenSafe(int col,int row,char board[][]){
        int r = row, c = col;

        // Check upper left diagonal
        while (r >= 0 && c >= 0) {
            if (board[r][c]=='Q') return false;
            r--;
            c--;
        }

        // Reset to the original position
        r = row;
        c = col;

        // Check left side
        while (c >= 0) {
            if (board[r][c]=='Q') return false;
            c--;
        }

        // Reset to the original position
        r = row;
        c = col;

        // Check lower left diagonal
        while (r < board.length && c >= 0) {
            if (board[r][c]=='Q') return false;
            r++;
            c--;
        }

        // If no queens are found, it's safe
        return true;
    }
}
