package career.datastructure.recurssion.pattern.backtracking;

import java.util.Arrays;
import java.util.List;

public class WordSearch {
    public static void main(String[] args) {
       System.out.println(exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}},"ABCCED"));
       System.out.println(exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}},"SEE"));
       System.out.println(exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}},"ABCB"));
       System.out.println(exist(new char[][]{{'A'}},"A"));
    }
    public static boolean exist(char[][] board, String word) {
        int m = board.length,n = board[0].length;
        int length = word.length();
        boolean visited[][]=new boolean[m][n];
        char wordChar[]=word.toCharArray();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if (board[i][j] == wordChar[0]) {
                    if (solve(board, wordChar, visited, 1, m, n, length, i, j)) {
                        return true;
                    }
                }
            }
        }
/*
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (search(board, word, i, j, 0)) {
                        return true;
                    }
                }
            }
        }*/
        return false;
    }

    public static int directions[][]={{-1,0},{0,-1},{1,0},{0,1}};
    public static boolean solve(char [][]board,char[] wordChar,boolean visited[][],int index,int m,int n,int length,int x,int y){
        if(index == length) return true;

        visited[x][y]=true;
        for(int dir[]:directions){
            int newX = x + dir[0];
            int newY = y + dir[1];
            if(newX < 0 || newX >= m || newY < 0 || newY >= n
                    || visited[newX][newY] || board[newX][newY] != wordChar[index]){
                continue;
            }

            if(solve(board,wordChar,visited,index+1,m,n,length,newX,newY)){
                return true;
            }
        }
        visited[x][y]=false;
        return false;
    }
    private static boolean search(char[][]board, String word, int i, int j, int index) {
        if (index == word.length()) {
            return true;
        }
        if (i >= board.length || i < 0 || j >= board[i].length || j < 0 || board[i][j] != word.charAt(index) ) {
            return false;
        }
        if (board[i][j] == '#') {
            return false;
        }
        char tmp = board[i][j];
        board[i][j] = '#';

        boolean found =
                search(board, word, i-1, j, index+1) ||
                        search(board, word, i+1, j, index+1) ||
                        search(board, word, i, j-1, index+1) ||
                        search(board, word, i, j+1, index+1);

        board[i][j] = tmp;
        return found;
    }
}
