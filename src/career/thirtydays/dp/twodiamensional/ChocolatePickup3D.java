package career.thirtydays.dp.twodiamensional;

/*
https://www.naukri.com/code360/problems/ninja-and-his-friends_3125885?source=youtube&campaign=striver_dp_videos&leftPanelTab=0
Ninja has a 'GRID' of size 'R' X 'C'. Each cell of the grid contains some chocolates. Ninja has two friends Alice and Bob, and he wants to collect as many chocolates as possible with the help of his friends.

Initially, Alice is in the top-left position i.e. (0, 0), and Bob is in the top-right place i.e. (0, ‘C’ - 1) in the grid. Each of them can move from their current cell to the cells just below them. When anyone passes from any cell, he will pick all chocolates in it, and then the number of chocolates in that cell will become zero. If both stay in the same cell, only one of them will pick the chocolates in it.

If Alice or Bob is at (i, j) then they can move to
(i + 1, j), (i + 1, j - 1) or (i + 1, j + 1).
They will always stay inside the ‘GRID’.

Your task is to find the maximum number of chocolates Ninja can collect with the help of his friends by following the above rules.

Example:
Input: ‘R’ = 3, ‘C’ = 4
       ‘GRID’ = [{2, 3, 1, 2},
                 {3, 4, 2, 2},
                 {5, 6, 3, 5}]
Output: 21

Initially Alice is at the position (0,0) he can follow the path
(0,0) -> (1,1) -> (2,1) and will collect 2 + 4 + 6 = 12 chocolates.

Initially Bob is at the position (0, 3) and he can follow the path
(0, 3) -> (1,3) -> (2, 3) and will collect 2 + 2 + 5 = 9 chocolates.

Hence the total number of chocolates collected will be 12 + 9 = 21. there is no other possible way to collect a greater number of chocolates than 21.

 */
public class ChocolatePickup3D {
    public static void main(String[] args) {
        int grid[][] = {{2, 3, 1, 2},{3, 4, 2, 2},{5, 6, 3, 5}};
    }

    public static int maximumChocolates(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;


        return -1;
    }
}
