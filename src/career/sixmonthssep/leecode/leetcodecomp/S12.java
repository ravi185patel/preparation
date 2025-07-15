package career.sixmonthssep.leecode.leetcodecomp;

public class S12 {
    public static void main(String[] args) {
        System.out.println(checkPrimeFrequency(new int[]{1,2,3,4,5,4}));
        System.out.println(checkPrimeFrequency(new int[]{1,2,3,4,5}));
        System.out.println(checkPrimeFrequency(new int[]{1,2,3,4,5,4}));
    }
    public static boolean checkPrimeFrequency(int[] nums) {

        int freq[] = new int[101];
        for (int i : nums) {
            freq[i]++;
        }


            for (int i : freq) {
                if (i == 0) {
                    continue;
                }
                if (isPrime(i)) {
                    return true;
                }
            }
        return false;
    }

        public static boolean isPrime(int no){
            if(no < 2) return false;
            if(no == 2) return true;
            if(no%2 == 0) return false;

            for(int i=3;i<=Math.sqrt(no);i+=2){
                if(no%i == 0){
                    return false;
                }
            }
            return true;
        }

}
