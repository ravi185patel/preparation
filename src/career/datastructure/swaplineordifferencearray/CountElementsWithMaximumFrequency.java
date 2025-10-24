package career.datastructure.swaplineordifferencearray;
/*
https://leetcode.com/problems/count-elements-with-maximum-frequency/
 */
public class CountElementsWithMaximumFrequency {
    public int maxFrequencyElements(int[] nums) {
        int count=0;
        int maxFreq = 0;
        int freq[]=new int[101];
        for(int i: nums){
            freq[i]++;
            if(maxFreq < freq[i]){
                maxFreq=freq[i];
                count=0;
            }

            if(maxFreq == freq[i]){
                count+= freq[i];
            }
        }

        return count;
    }
}
