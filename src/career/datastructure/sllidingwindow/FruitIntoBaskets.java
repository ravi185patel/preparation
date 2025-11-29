package career.datastructure.sllidingwindow;

/*
https://leetcode.com/problems/fruit-into-baskets/description/

 */

import java.util.HashMap;
import java.util.Map;

public class FruitIntoBaskets {
    public static void main(String[] args) {

    }

    public int totalFruit1(int[] fruits) {
        Map<Integer, Integer> count = new HashMap<>();
        int i = 0, j;
        for (j = 0; j < fruits.length; ++j) {
            count.put(fruits[j], count.getOrDefault(fruits[j], 0) + 1);
            if (count.size() > 2) {
                count.put(fruits[i], count.get(fruits[i]) - 1);
                count.remove(fruits[i], 0);
                ++i;
            }
        }
        return j - i;
    }

    public int totalFruit(int[] fruits) {
        // We use a hash map 'basket' to store the number of each type of fruit.
        Map<Integer, Integer> basket = new HashMap<>();
        int left = 0, maxPicked = 0;

        // Add fruit from the right index (right) of the window.
        for (int right = 0; right < fruits.length; ++right) {
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);

            // If the current window has more than 2 types of fruit,
            // we remove fruit from the left index (left) of the window,
            // until the window has only 2 types of fruit.
            while (basket.size() > 2) {
                basket.put(fruits[left], basket.get(fruits[left]) - 1);
                if (basket.get(fruits[left]) == 0)
                    basket.remove(fruits[left]);
                left++;
            }

            // Update maxPicked.
            maxPicked = Math.max(maxPicked, right - left + 1);
        }

        // Return maxPicked as the maximum number of fruits we can collect.
        return maxPicked;
    }
}
