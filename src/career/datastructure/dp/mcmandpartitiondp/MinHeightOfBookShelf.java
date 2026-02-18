package career.datastructure.dp.mcmandpartitiondp;

import java.util.Arrays;

/*
🔹 Core MCM Pattern

Matrix Chain Multiplication (classic)

Minimum Score Triangulation of Polygon – similar to MCM on polygon chain

Burst Balloons – canonical MCM-like DP contraction problem

Minimum Cost to Merge Stones (or merging slimes) – partition DP

Palindrome Partitioning – min cuts to make palindromes

Optimal BST / Optimal Binary Search Tree – weighted partitioning

Evaluate Expression to True / Boolean Parenthesization – MCM on expression trees

Word Wrap / Text Justification – MCM/interval DP pattern

Partition Array For Maximum Sum – contiguous partition DP

Filling Bookcase Shelves (the one you worked on) – contiguous partition DP

Whenever you see:

Fixed order

Grouping contiguous elements

Cost per group

Minimize total cost
 */
public class MinHeightOfBookShelf {
    public static void main(String[] args) {
        System.out.println(minHeightBookShelf(new int[][]{
                {1,1},{1,2},{2,3},{2,3},{1,1},{1,1},{1,1}
        },4));
    }

    public static int minHeightBookShelf(int books[][],int shelfWidth){
//        return solveRec(0,books,0,0,0,shelfWidth);
        return solveRecMCM(books,shelfWidth,0);
    }

    public static int solveRec(int index,int books[][],int currentHeight,int totalHeight,int currentWidth,int shelfWidth){
        if(index == books.length) return currentHeight + totalHeight;

        int thik = books[index][0];
        int height = books[index][1];
        int currentShelf = Integer.MAX_VALUE;
        if(thik+currentWidth <= shelfWidth){
            currentShelf = solveRec(index+1,books,Math.max(height,currentHeight),totalHeight,
                    currentWidth+thik,shelfWidth);
        }
        int nextShelf = solveRec(index+1,books,height,totalHeight+currentHeight,thik,shelfWidth);
        return Math.min(currentShelf,nextShelf);
    }


    private static int solveRecMCM(int[][] books, int shelfWidth, int i) {

        // Base case
        if (i == books.length) {
            return 0;
        }

        int width = 0;
        int maxHeight = 0;
        int min = Integer.MAX_VALUE;

        // MCM-style partition loop
        for (int j = i; j < books.length; j++) {

            width += books[j][0];

            if (width > shelfWidth) {
                break;
            }

            maxHeight = Math.max(maxHeight, books[j][1]);

            int cost = maxHeight + solveRecMCM(books, shelfWidth, j + 1);

            min = Math.min(min, cost);
        }

        return min;
    }

    private static int solveRecMCMDp(int[][] books, int shelfWidth) {

        int n = books.length;
        int[] dp = new int[n + 1];

        dp[0] = 0; // no books

        for (int i = 1; i <= n; i++) {

            int width = 0;
            int maxHeight = 0;
            dp[i] = Integer.MAX_VALUE;

            // Try placing books j → i on the last shelf
            for (int j = i; j > 0; j--) {

                width += books[j - 1][0];

                if (width > shelfWidth) {
                    break;
                }

                maxHeight = Math.max(maxHeight, books[j - 1][1]);

                dp[i] = Math.min(dp[i], dp[j - 1] + maxHeight);
            }
        }

        return dp[n];
    }
}
