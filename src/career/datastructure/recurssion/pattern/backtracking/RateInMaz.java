package career.datastructure.recurssion.pattern.backtracking;

import java.util.ArrayList;
import java.util.List;

public class RateInMaz {
    public static void main(String[] args) {
        System.out.println(allPossiblePaths(new int[][]{
                {1, 0, 0, 0},{1, 1, 0, 1},{1, 1, 0, 0},{0, 1, 1, 1}
        }));
    }

    public static List<String> allPossiblePaths(int maze[][]){
        List<String> result = new ArrayList<>();
        allPossiblePathsRec(0,0,maze.length,maze[0].length,maze,result,new String());
        return result;
    }

    public static void allPossiblePathsRec(int i,int j,int n,int m,int maze[][],List<String> allPaths,
                                                   String path){
        if(i == n-1 && j == m-1){
            allPaths.add(path);
            return;
        }
        if(i < 0 || j < 0 || i >=n || j >=m) return ;
        if(maze[i][j] == 0) return;
        maze[i][j]=0;

        allPossiblePathsRec(i,j-1,n,m,maze,allPaths,path+("L"));
        allPossiblePathsRec(i-1,j,n,m,maze,allPaths,path+("U"));
        allPossiblePathsRec(i,j+1,n,m,maze,allPaths,path+("R"));
        allPossiblePathsRec(i+1,j,n,m,maze,allPaths,path+("D"));
        maze[i][j]=1;
    }
}
