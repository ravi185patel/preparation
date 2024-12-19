package career.interview.google;

import java.util.*;

public class PrintAllCyclePathInMatrix {
    static Set<List<Integer>> set = new HashSet<>();
    public static void main(String[] args) {
        boolean matrix[][]={
                {false,false,true,false},
                {true,false,false,false},
                {false,true,false,true},
                {false,true,false,false},
                {false,false,true,false},

        };
        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean visited[][] = new boolean[rows][cols];
        List<List<Integer>> res = new ArrayList<>();
//        (0,2) -> (2,1)  -> (1,0) -> (0,2) -> need add -> 0,2,1,0
//        -> (2,3) -> (3,1) -> (1,0) -> (0,2) -> need add  -> 0,2,3,1,0
//
//        (4, 5), (5, 0), (0, 1), (1,2), (2,0)
//        (0,1) -> (1,2) -> (2,0) -> path
//                (4,5) -> (5,0) ->
//        List<String> points = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j]) {
                    System.out.println(i+" "+j);
                    for(boolean rw[]:visited){
                        System.out.println(Arrays.toString(rw));
                    }
                    dfs(i, j, i, j, matrix, visited,new ArrayList<>(),res);
                }
            }
        }
        System.out.println(res);
    }

        public static void dfs(int x,int y,int parentX,int parentY,boolean matrix[][],boolean visited[][],List<Integer> path,List<List<Integer>> res){
            visited[x][y] = true;
            path.add(x);
            for(int i=0;i<matrix[0].length;i++){
                if(matrix[y][i]){
                    if(visited[y][i]== false){
                        System.out.println(y+" "+i);
                        dfs(y,i,parentX,parentY,matrix,visited,path,res);
                    }
                    else{
                        if(parentX == y && parentY == i){
                            List<Integer> lst = new ArrayList<>(path);
                            Collections.sort(lst);
                            if(!set.contains(lst)){
                                res.add(new ArrayList<>(path));
                            }
                            set.add(lst);
                        }
                    }
                }
            }
            visited[x][y] = false;
            path.remove(path.size()-1);
        }

    }
