package career.website.codeforce.under100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BeautifulYear {
    static class FastScanner {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer("");
        String next() {
            while (!st.hasMoreTokens())
                try {
                    st=new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
        int[] readArray(int n) {
            int[] a=new int[n];
            for (int i=0; i<n; i++) a[i]=nextInt();
            return a;
        }
        long nextLong() {
            return Long.parseLong(next());
        }
    }


    public static void main(String[] args) {

        BeautifulYear.FastScanner fs=new BeautifulYear.FastScanner();

        System.out.println(findOutBeautifulYear(fs.nextInt()));
//        System.out.println(findOutBeautifulYear(2013));
//        System.out.println(findOutBeautifulYear(1888));
//        System.out.println(findOutBeautifulYear(1666));

    }

    public static int findOutBeautifulYear(int year){
        for(int i=year+1;i<=100000;i++){
            if(hasStringUniqueCharacter(String.valueOf(i))){
                return i;
            }
        }
        return year;
    }

    public static boolean hasStringUniqueCharacter(String no){
        Set<Character> set= new HashSet<>();
        for(char ch:no.toCharArray()){
            if(!set.add(ch)){
                return false;
            }
        }
        return true;
    }
}
