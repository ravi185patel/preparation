package career.datastructure.sllidingwindow;
/*
https://leetcode.com/problems/maximize-the-confusion-of-an-exam/description/

 */
public class MaximizeConfusionAnExam {
    public static void main(String[] args) {
        System.out.println(maxConsecutiveAnswers("TTFF",2));
        System.out.println(maxConsecutiveAnswers("TFFT",1));
        System.out.println(maxConsecutiveAnswers("TTFTTFTT",1));
    }

    public static int maxConsecutiveAnswers(String answerKey, int k) {
        int left=0;
        int tCnt=0,fCnt=0;
        int longest =0;
        for(int right =0;right<answerKey.length();right++){
            char c =  answerKey.charAt(right);
            tCnt += c == 'T' ? 1:0;
            fCnt += c == 'F' ? 1:0;
            int minFlip = Math.min(tCnt,fCnt);
            while(minFlip > k){ // will define condition when window need to shrink VIMP
                char c1 =  answerKey.charAt(left);
                tCnt -= c1 == 'T' ? 1:0;
                fCnt -= c1 == 'F' ? 1:0;
                minFlip = Math.min(tCnt,fCnt);
                left++;
            }
            longest = Math.max(longest,right-left+1);
        }

        return longest;
    }
}
