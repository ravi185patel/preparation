package career.datastructure.recurssion.subsequence.subsetAndCombinnation.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueen {
    public static void main(String[] args) {
//        String[][] board = {{".Q..","...Q","Q...","..Q."},{"..Q.","Q...","...Q",".Q.."}};
        int n = 4;

        char[][]board =new char[n][n];
        for(char row[]:board){
            Arrays.fill(row,'.');
        }
        List<List<String>> res = new ArrayList<>();
        int leftRow[]=new int[n];
        int upperDaigonal[] = new int[n*2 - 1];
        int lowerDaigonal[] = new int[n*2 - 1];

        helper(0,board,res,leftRow,upperDaigonal,lowerDaigonal);
        System.out.println(res);
    }

    private static void helper(int col,char[][]board,List<List<String>>res, int leftRow[],int upperDiagonal[],int lowerDiagonal[]){


    }

}
