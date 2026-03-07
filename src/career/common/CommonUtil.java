package career.common;

import java.util.Arrays;

public class CommonUtil {
    public static int [][] dir={{-1,0},{0,-1},{1,0},{0,1}};

    public static void print(int [][] grid){
        System.out.println("-------- out put -------------");
        for(int row[]:grid){
            printRow(row);
        }
    }
    public static void printRow(int row[]){
        System.out.println(Arrays.toString(row));
    }
}
