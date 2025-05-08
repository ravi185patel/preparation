package career.interview.google;

/*
1 0 0 0 0 0
1 0 1 1 1 0
0 0 1 1 0 0
0 0 1 0 0 1
1 0 0 0 1 1



1 0 0 0 0 0
1 0 1 1 1 0
0 0 1 1 0 0
0 1 1 0 0 1
1 1 0 0 1 1


(x-1,y+1) (x,y+1)  (x+1,y+1)
             |
(x-1,y) -- (x,y) -- (x+1,y)
             |
(x-1,y-1) (x,y-1)  (x+1,y-1)

point(x,y) ->
1] (x,y-1) && (x+1,y-1) -> check full direction
2] (x,y-1) && (x-1,y-1)
3] (x-1,y) && (x-1,y-1)
4] (x-1,y) && (x-1,y+1)
...
max of all these directions

Approaches
1] BFS matrix one
   terminate condition -> if both have zero terminate there
   tc -> O(n*m)
2] dynamic programming



 */
public class FindMaxTriangleInGrid {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 0, 0, 0, 0, 0},
                {1, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 0, 0},
                {0, 0, 1, 0, 0, 1},
                {1, 0, 0, 0, 1, 1}
        };

        int maxSize = findTriangleInGrid(matrix);
        System.out.println("Maximum triangle size: " + maxSize);

    }

    private static int findTriangleInGrid(int grid[][]){
        int rows = grid.length;
        int cols = grid[0].length;
        int maxArea = 0;

        // Try every cell as the right-angled corner
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    // Try all right-angled triangle orientations (up, down, left, right)
                    maxArea = Math.max(maxArea, rightTriangle(grid, i, j));
                }
            }
        }
        return maxArea;
    }

    private static int rightTriangle(int[][] grid, int r, int c) {
        int maxArea = 0;
        int rows = grid.length, cols = grid[0].length;

        // Check downward right triangle
        for (int h = 1; r + h < rows; h++) {
            for (int w = 1; c + w < cols; w++) {
                if (isFilled(grid, r, c, h, w)) {
                    int area = (h * w) / 2;
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    private static boolean isFilled(int[][] grid, int r, int c, int h, int w) {
        // Check if all points within the right triangle are 1
        for (int i = 0; i <= h; i++) {
            for (int j = 0; j <= w; j++) {
                if (i * w + j * h <= h * w) { // Inside triangle
                    if (grid[r + i][c + j] != 1) return false;
                }
            }
        }
        return true;
    }
}
