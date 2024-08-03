package career.website.leetcode.string.medium;

public class MaximizeConfusionOfExam {
    public static void main(String[] args) {
        System.out.println(maxConsecutiveAnswers("TTFF",2));
        System.out.println(maxConsecutiveAnswers("TFFT",1));
        System.out.println(maxConsecutiveAnswers("TTFTTFTT",1));
        System.out.println(maxConsecutiveAnswers("FFFTTFTTFT",3));
    }

    public static int maxConsecutiveAnswers(String answerKey, int k) {
        char ch[]=answerKey.toCharArray();
        int tMax = getMax('T',ch,k);
        int fMax = getMax('F',ch,k);
        return Math.max(tMax,fMax);
    }

    private static int getMax(char c,char ch[],int t){
        int start=0,end=0,length=ch.length;
        int max = Integer.MIN_VALUE,count=0;
        while(end < length){
            if(ch[end] == c) count++;
            if(count > t){
                while(start<end && count >0){
                    if(ch[start] == c){
                        start++;
                        count--;
                        break;
                    }
                    start++;
                }
            }
            max = Math.max(max,end-start+1);
            end++;
        }

        return max;
    }
}
