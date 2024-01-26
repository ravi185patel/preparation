package career.takeuforward.sdesheet.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetMatrixZero {
    public static void main(String[] args) {
//       int matrix[][]={{1,1,1},{1,0,1},{1,1,1}};
       int matrix[][]={{0,1,2,1},{3,4,5,0},{1,3,1,5}};
//        setMatrixZero(matrix);
        setMatrixZero1(matrix);
    }

    public static void setMatrixZero(int matrix[][]){
        List<int[]> lst= new ArrayList<>();

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(matrix[i][j] == 0){
                    lst.add(new int[]{i,j});
                }
            }
        }

        for(int i[]:lst){
            System.out.println(Arrays.toString(i));
        }

        for(int i[]:lst){
            for(int j=0;j<matrix[0].length;j++){ //set each row
                matrix[i[0]][j]=0;
            }

            for(int j=0;j<matrix.length;j++){ // set each col
                matrix[j][i[1]]=0;
            }
        }
        for(int i[]:matrix) {
            System.out.println(Arrays.toString(i));
        }

    }

    // space optimization
    public static void setMatrixZero1(int matrix[][]){

        // here we use first row as a pointer/flag to determine that column have 0 or not, same way for row.
        // only (0,0) cell we can't identify which row or column has 0 or not. so we define col variable which will be set when j == 0 for col and (0,0) point for row.
        int col = 1;

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(matrix[i][j] == 0){
                   matrix[i][0] =0;
                   if(j != 0){
                       matrix[0][j]=0;
                   }else{
                       col=0;
                   }
                }
            }
        }

        for(int i=1;i<matrix.length;i++){
            for(int j=1;j<matrix[i].length;j++){
                if(matrix[i][j] != 0){
                    if(matrix[i][0] == 0 || matrix[0][j]==0){
                        matrix[i][j] = 0;
                    }
                }
            }
        }

        if(matrix[0][0] == 0){
            for(int j =0;j<matrix[0].length;j++){
                matrix[0][j]=0;
            }
        }

        if(col == 0){
            for(int j =0;j<matrix.length;j++){
                matrix[j][0]=0;
            }
        }


        for(int i[]:matrix) {
            System.out.println(Arrays.toString(i));
        }
    }

}

