package career.datastructure.sllidingwindow;

public class RescheduleMeetingsMaximumFreeTimeI {
    public static void main(String[] args) {

    }
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int []freqArray = new int[n+1];

        // start time
        freqArray[0] = startTime[0];
        for(int i=1;i<n;i++){
            freqArray[i] = startTime[i] - endTime[i-1];
        }

        // last gap : from endTime[n-1] to eventTime
        freqArray[n] = eventTime - endTime[n-1];

        int maxSum=0,currSum=0,left=0;

        for(int right=0;right < freqArray.length;right++){
            currSum += freqArray[right];

            while(right - left + 1 > k+1 ){
                currSum -=freqArray[left];
                left++;
            }

            maxSum = Math.max(maxSum,currSum);
        }
        return maxSum;
    }
}
