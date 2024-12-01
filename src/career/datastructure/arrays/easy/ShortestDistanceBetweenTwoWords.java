package career.datastructure.arrays.easy;

public class ShortestDistanceBetweenTwoWords {
    public static void main(String[] args) {
        String wordsDict[] = {"practice", "makes", "perfect", "coding", "makes"}, word1 = "coding", word2 = "practice";
//        String wordsDict[] = {"practice", "makes", "perfect", "coding", "makes"}, word1 = "makes", word2 = "coding";
        System.out.println(shortestDistance(wordsDict,word1,word2));
    }

    public static int shortestDistance(String[] wordsDict, String word1, String word2) {
        // Initialize the minimum distance to a very high value
        int minDistance = Integer.MAX_VALUE;

        // These will hold the last seen positions of word1 and word2
        int lastPosWord1 = -1;
        int lastPosWord2 = -1;

        // Loop through the words dictionary to find the words
        for (int index = 0; index < wordsDict.length; ++index) {
            // If the current word equals word1, update lastPosWord1
            if (wordsDict[index].equals(word1)) {
                lastPosWord1 = index;
            }
            // If the current word equals word2, update lastPosWord2
            if (wordsDict[index].equals(word2)) {
                lastPosWord2 = index;
            }
            // If both last positions are set and not -1, calculate the distance
            if (lastPosWord1 != -1 && lastPosWord2 != -1) {
                // Update the minimum distance if a new minimum is found
                minDistance = Math.min(minDistance, Math.abs(lastPosWord1 - lastPosWord2));
            }
        }
        // Return the minimum distance found
        return minDistance;
    }
}
