package career.datastructure.recurssion.subsequence.subsetAndCombinnation.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/24-game/description/
The problem can be solved as follows:
(a) Choose any two numbers from the array and perform a mathematical operation on them. This will result in a new value.
(b) Remove the two numbers used in step (a), and replace them with the new value.
(c) Repeat steps (a) and (b) with the updated array until the array only contains one number.
(d) If this number is 24, then we found a result. Otherwise, we backtrack and try selecting the numbers in a different order or using a different permutation of mathematical operations.

 */
public class Game24 {
    public static void main(String[] args) {

//        int []cards = {4,1,8,7};
//        int []cards = {1,2,1,2};
//        int []cards = {3,3,8,8};
        int []cards = {8,1,6,6};
//        int []cards = {1,9,1,2};
//        int []cards = {4,1,8,7};

        System.out.println(judgePoint24(cards));
    }

    private static List<Double> generatePossibleResults(double a, double b) {
        List<Double> res = new ArrayList<>();
        res.add(a + b);
        res.add(a - b);
        res.add(b - a);
        res.add(a * b);
        if (b != 0) {
            res.add(a / b);
        }
        if (a != 0) {
            res.add(b / a);
        }
        return res;
    }

    // Check if using current list we can react result 24.
    private static boolean checkIfResultReached(List<Double> list) {
        if (list.size() == 1) {
            // Base Case: We have only one number left, check if it is approximately 24.
            return Math.abs(list.get(0) - 24) <= 0.1;
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                // Create a new list with the remaining numbers and the new result.
                List<Double> newList = new ArrayList<>();
                for (int k = 0; k < list.size(); k++) {
                    if (k != j && k != i) {
                        newList.add(list.get(k));
                    }
                }

                // For any two numbers in our list,
                // we perform every operation one by one.
                for (double res : generatePossibleResults(list.get(i), list.get(j))) {
                    // Push the new result in the list
                    newList.add(res);

                    // Check if using this new list we can obtain the result 24.
                    if (checkIfResultReached(newList)) {
                        return true;
                    }

                    // Backtrack: remove the result from the list.
                    newList.remove(newList.size() - 1);
                }
            }
        }
        return false;
    }

    public static boolean judgePoint24(int[] cards) {
        List<Double> list = new ArrayList<>();
        for (int card : cards) {
            list.add((double) card);
        }

        return checkIfResultReached(list);
    }
}
