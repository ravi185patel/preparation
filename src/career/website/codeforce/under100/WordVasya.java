package career.website.codeforce.under100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class WordVasya {
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
        WordVasya.FastScanner fs=new WordVasya.FastScanner();
        System.out.println(findLessChangeString(fs.next()));
    }


    public static String findLessChangeString(String word){
        String upperCaseWord = word.toUpperCase();
        String lowerCaseWord = word.toLowerCase();

        int upperCaseWordDiff=0,lowerCaseWordDiff=0;

        for(int i=0;i<word.length();i++){
            if(word.charAt(i) != upperCaseWord.charAt(i)){
                upperCaseWordDiff++;
            }else{
                lowerCaseWordDiff++;
            }
        }

        if(upperCaseWordDiff >= lowerCaseWordDiff){
            return lowerCaseWord;
        }else{
            return upperCaseWord;
        }


    }
}
