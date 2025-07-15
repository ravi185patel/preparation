package career.interview.google;

import java.beans.Visibility;
import java.util.*;

public class FindLakesAtGivenPoint {
    public static void main(String[] args) {
        char ocen[][]={
                {'.','.','+','.','.','.','.','.','.'},
                {'.','+','+','+','.','.','.','.','.'},
                {'.','+','.','+','.','.','.','.','.'},
                {'.','+','.','+','.','.','+','+','.'},
                {'.','+','+','+','.','+','+','+','.'},
                {'.','.','.','.','+','+','.','.','+'},
                {'.','+','+','+','+','+','+','+','+'},
                {'.','.','.','.','.','.','+','.','+'},
                {'.','.','.','.','.','.','.','+','.'},
        };

        System.out.println(markAllPointOtherThanLakes(ocen,0,2));
        System.out.println(" bfs = "+findLakesBasedOnGivenPoint(0,2,ocen));
        System.out.println(markAllPointOtherThanLakes(ocen,6,1));
        System.out.println(" bfs = "+findLakesBasedOnGivenPoint(6,1,ocen));

         ocen=new char[][]{
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','+','+','+','+'},
                {'.','.','.','.','.','+','.','.','+'},
                {'.','+','+','+','+','+','.','.','+'},
                {'.','.','.','.','.','+','.','.','+'},
                {'.','+','+','+','+','+','+','+','.'},
                {'.','.','+','.','.','.','.','.','.'},
                {'.','+','+','+','.','.','.','.','.'},
                {'.','+','.','+','.','.','.','.','.'},
                {'.','+','.','+','.','.','+','+','.'},
                {'.','+','+','+','.','+','+','+','.'}
        };

        System.out.println(markAllPointOtherThanLakes(ocen,1,8));
        System.out.println(" bfs = "+findLakesBasedOnGivenPoint(1,8,ocen));
        System.out.println(markAllPointOtherThanLakes(ocen,0,2));
        System.out.println(" bfs = "+findLakesBasedOnGivenPoint(0,2,ocen));
    }

    static Set<String> set = new HashSet<>();
    private static int markAllPointOtherThanLakes(char [][]ocen,int startX,int startY){
        int m = ocen.length;
        int  n = ocen[0].length;
        boolean visited[][]=new boolean[m][n];
        List<int[]> points  = new LinkedList<>();

        int count=0;
        int[][] bounds = new int[4][2];

        if(ocen[startX][startY]=='.') System.out.println("no");
        else dfs(startX,startY,m,n,ocen,visited,points,bounds);

//        points.stream().forEach(i->System.out.print(Arrays.toString(i)));
        visited = new boolean[m][n];

        for(int point[]:points){

            for(int dir[]:new int[][]{{-1,0},{0,-1},{1,0},{0,1}}) { // missed
                int p1 = point[0] + dir[0]; // missed
                int p2 = point[1] + dir[1]; // missed

                if(!validate(p1,p2,m,n,visited,ocen)) { // missed
                    if(dfsFindLakes(p1, p2, m, n, ocen, bounds,visited)) {
                        count++;// missed
                    }
                }
            }
        }
        return count;
    }
//

    private static boolean validate(int x, int y, int m ,int n,boolean visited[][],char[][] ocen){
        return x < 0 || y < 0 || x >= m || y >= n || ocen[x][y] != '.' || set.contains(x+""+y);
    }
    private static boolean dfsFindLakes(int x,int y,int m,int n,char[][]ocen,int bounds[][],boolean visited[][]){
        if( x < 0 || y < 0 || x >= m || y >= n) {
            return false;
        }
        if( (x==0 || y==0 || x == m-1 || y == n-1) && ocen[x][y] =='.'){
            return false;
        }
        char c=ocen[x][y];
        if(ocen[x][y] == '+') return true;
        set.add(x+""+y); // missing
        ocen[x][y] = '+';
        boolean isCovered = dfsFindLakes(x+1,y,m,n,ocen,bounds,visited)
                && dfsFindLakes(x,y+1,m,n,ocen,bounds,visited)
                && dfsFindLakes(x-1,y,m,n,ocen,bounds,visited)
                && dfsFindLakes(x,y-1,m,n,ocen,bounds,visited);
        ocen[x][y] = c;
        return isCovered;
    }

    private static void dfs(int x,int y,int m,int n,char[][]ocen,boolean visited[][],List<int[]> points,int[][] bounds){
        if(x >= m || y >= n || x < 0 || y < 0 || visited[x][y] || ocen[x][y] != '+'){
            return;
        }
        visited[x][y] = true;
        bounds[0][0] = Math.min(bounds[0][0],x);
        bounds[0][1] = Math.min(bounds[0][1],y);
        bounds[1][0] = Math.max(bounds[1][0],x);
        bounds[1][1] = Math.min(bounds[1][1],y);
        bounds[2][0] = Math.min(bounds[2][0],x);
        bounds[2][1] = Math.max(bounds[2][1],y);
        bounds[3][0] = Math.max(bounds[3][0],x);
        bounds[3][1] = Math.max(bounds[3][1],y);
        /*

        */
        points.add(new int[]{x,y});
        dfs(x+1,y,m,n,ocen,visited,points,bounds);
        dfs(x,y+1,m,n,ocen,visited,points,bounds);
        dfs(x-1,y,m,n,ocen,visited,points,bounds);
        dfs(x,y-1,m,n,ocen,visited,points,bounds);
//        visited[x][y] = false;
//        points.remove(points.size()-1);
    }



    public static int findLakesBasedOnGivenPoint(int px,int py,char[][]ocen){
        coverNonIslandArea(ocen);

        if(ocen[px][py] == 'X'){
            return 0;
        }

        if(ocen[px][py] == '.'){
            return 1;
        }else{
            return findNoOfLakes(px,py,ocen);
        }

    }

    public static void coverOcen(int x,int y,char[][]ocen){
        int m = ocen.length;
        int n = ocen[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        ocen[x][y]='X';
        while(!queue.isEmpty()){
            int point[]=queue.poll();
            int px = point[0];
            int py = point[1];
            for(int dir[]:dirs){
                int newX = px + dir[0];
                int newY = py + dir[1];
                if(newX < 0 || newX >= m || newY < 0 || newY >= n || ocen[newX][newY]!= '.'){
                    continue;
                }
                ocen[newX][newY]='X';
                queue.add(new int[]{newX,newY});
            }
        }

    }
    public static int findNoOfLakes(int x,int y,char[][]ocen){
        int m = ocen.length;
        int n = ocen[0].length;
        Queue<int[]> queue = new LinkedList<>();
        ocen[x][y]='X';
        queue.add(new int[]{x,y});
        int noOfLakes=0;
        while(!queue.isEmpty()){
            int point[]=queue.poll();
            int px = point[0];
            int py = point[1];
            for(int dir[]:dirs){
                int newX = px + dir[0];
                int newY = py + dir[1];
                if(newX < 0 || newX >= m || newY < 0 || newY >= n
                        || ocen[newX][newY] == 'X' || ocen[newX][newY] == 'O'){
                    continue;
                }
                if(ocen[newX][newY]=='.'){
                    coverOcen(newX,newY,ocen);
                    noOfLakes++;
                }
                ocen[newX][newY]='X';
                queue.add(new int[]{newX,newY});
            }

        }
        return noOfLakes;
    }

    public static void coverNonIslandArea(char [][]ocen){
        int m = ocen.length;
        int n = ocen[0].length;
        Queue<int[]> queue = new LinkedList<>();

        for(int i=0;i<m;i++){
            if(ocen[i][0] == '.'){
                queue.add(new int[]{i,0});
            }
            if(ocen[i][n-1] == '.'){
                queue.add(new int[]{i,n-1});
            }
        }
        for(int i=0;i<n;i++){
            if(ocen[0][i] == '.'){
                queue.add(new int[]{0,i});
            }
            if(ocen[m-1][i] == '.'){
                queue.add(new int[]{m-1,i});
            }
        }

        coverNonIslandArea(ocen,queue);
    }

    public static int dirs[][]={{-1,0},{0,-1},{1,0},{0,1}};

    public static void coverNonIslandArea(char[][]ocen,Queue<int[]> queue){
        int m = ocen.length;
        int n = ocen[0].length;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int point[]=queue.poll();
                int px = point[0];
                int py = point[1];
                ocen[px][py]='O';
                for(int dir[]:dirs){
                    int newX = px + dir[0];
                    int newY = py + dir[1];
                    if(newX < 0 || newX >= m || newY < 0 || newY >= n || ocen[newX][newY]!= '.'){
                        continue;
                    }
                    ocen[newX][newY]='O';
                    queue.add(new int[]{newX,newY});
                }
            }
        }
    }


}
