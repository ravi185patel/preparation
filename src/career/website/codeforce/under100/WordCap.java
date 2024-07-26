//package career.website.codeforce.under100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class WordCap {
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
        WordCap.FastScanner fs=new WordCap.FastScanner();
        String word=fs.next();
        char[] charSeq = word.toCharArray();
        if(charSeq[0] >= 97 && charSeq[0] <= 122){
            int remain = ('A'+(charSeq[0]-'a'));
//            System.out.println(remain);
//            char c=(char)(remain);
            charSeq[0] = (char) (remain);
            word = new String(charSeq);
        }
        System.out.println(word);

    }
}
