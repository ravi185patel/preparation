package career.datastructure.arrays.medium;

import java.util.Arrays;

/*
1 2 3    1  4  7     7 4 1
4 5 6 => 2  5  8  => 8 5 2
7 8 9    3  6  9     9 6 3

7 4 1
8 5 2
9 6 3

[
 [5,1,9,11],
 [2,4,8,10],
 [13,3,6,7],
 [15,14,12,16]
]

Output:
[  j
i[15,13,2,5],
 [14,3,4,1],
 [12,6,8,9],
 [16,7,10,11]
]

 */
public class RotateImageBy90Degree {
    public static void main(String[] args) {
        int arr[][]={{1,2,3},{4,5,6},{7,8,9}};
        rotateImage(arr);
        rotateImageWithoutMemo(arr);
    }

    public static void rotateImage(int arr[][]){
        int res[][]=new int[arr.length][arr[0].length];
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                res[j][arr.length-i-1]=arr[i][j];
            }
        }

        for(int i=0;i<arr.length;i++){
            System.out.println(Arrays.toString(res[i]));
        }
    }


    public static void rotateImageWithoutMemo(int arr[][]){

        for(int i=0;i<arr.length;i++){
            for(int j=i;j<arr[0].length;j++){
                int temp = arr[i][j];
                arr[i][j]=arr[j][i];
                arr[j][i]=temp;
            }
        }


        for(int i=0;i<arr.length;i++){
            System.out.println(Arrays.toString(arr[i]));
        }

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length/2;j++){
                int temp = arr[i][j];
                arr[i][j] = arr[i][arr.length-1-j];
                arr[i][arr.length-1-j] = temp;
            }
        }


        for(int i=0;i<arr.length;i++){
            System.out.println(Arrays.toString(arr[i]));
        }
    }
}
