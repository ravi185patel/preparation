package career.datastructure.hasing;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
https://leetcode.com/problems/hand-of-straights/

 */
public class HandOfStraights {
    public static void main(String[] args) {

    }
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;

        if (n % groupSize != 0) {
            return false;
        }

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int card : hand) {
            map.put(card, map.getOrDefault(card, 0) + 1);
        }

        while (!map.isEmpty()) {
            int curr = map.firstKey();

            for (int i = 0; i < groupSize; i++) {
                if (!map.containsKey(curr + i)) {
                    return false;
                }

                map.put(curr + i, map.get(curr + i) - 1);
                if (map.get(curr + i) == 0) {
                    map.remove(curr + i);
                }
            }
        }

        return true;
    }
    public boolean isNStraightHand1(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        }

        // HashMap to store the count of each card value
        HashMap<Integer, Integer> cardCount = new HashMap<>();
        for (int card : hand) {
            int count = cardCount.getOrDefault(card, 0);
            cardCount.put(card, count + 1);
        }

        for (int card : hand) {
            int startCard = card;
            // Find the start of the potential straight sequence
            while (cardCount.getOrDefault(startCard - 1, 0) > 0) {
                startCard--;
            }

            // Process the sequence starting from startCard
            while (startCard <= card) {
                while (cardCount.getOrDefault(startCard, 0) > 0) {
                    // Check if we can form a consecutive sequence
                    // of groupSize cards
                    for (
                            int nextCard = startCard;
                            nextCard < startCard + groupSize;
                            nextCard++
                    ) {
                        if (cardCount.getOrDefault(nextCard, 0) == 0) {
                            return false;
                        }
                        cardCount.put(nextCard, cardCount.get(nextCard) - 1);
                    }
                }
                startCard++;
            }
        }

        return true;
    }
}
