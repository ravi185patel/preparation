package career.sixmonthssep.leecode.leetcodecomp;

import java.util.*;

public class SumOfLargestPrimeNumber {
    public static void main(String[] args) {
        System.out.println(sumOfLargestPrimes("12234"));
        System.out.println(sumOfLargestPrimes("111"));
        System.out.println(sumOfLargestPrimes("57065933"));
    }
    public static long sumOfLargestPrimes(String s) {
        Set<Integer> set = new TreeSet<>(Collections.reverseOrder());
        long sum=0;
        for(int i=0;i<s.length();i++){
            int k= s.charAt(i)-'0';
            for(int j=i;j<s.length();j++){
                if(!isPrime(k)) {
                    set.add(k);
                }
                k= k*10 + (s.charAt(j)-'0');
            }
            if(!isPrime(k)) {
                set.add(k);
            }
        }

        int index=0;
        for(int val:set){
            sum+=val;
            if(++index == 3) break;
        }
        return sum;
    }

    public static boolean isPrime(int num){
        boolean flag = false;

        // 0 and 1 are not prime numbers
        if (num == 0 || num == 1) {
            flag = true;
        }

        for (int i = 2; i <= num / 2; ++i) {

            // condition for nonprime number
            if (num % i == 0) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
