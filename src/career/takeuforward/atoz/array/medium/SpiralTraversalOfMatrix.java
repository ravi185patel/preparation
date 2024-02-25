package career.takeuforward.atoz.array.medium;

public class SpiralTraversalOfMatrix {
    public static void main(String[] args) {
        int Matrix[][] = { { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };

        spiralOrder(Matrix);
    }

    public static void spiralOrder(int matrix[][]){
        int right=matrix[0].length-1,bottom=matrix.length-1,left=0,top=0;

        while(top <= bottom && left <= right){
            for(int j=left;j<=right;j++){
                System.out.print(matrix[top][j]+" ");
            }
            top++;

            for(int j=top;j<=bottom;j++){
                System.out.print(matrix[j][right]+" ");
            }
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--)
                    System.out.print(matrix[bottom][i]+" ");
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--)
                    System.out.print(matrix[i][left]+" ");
                left++;
            }
        }
    }
}
