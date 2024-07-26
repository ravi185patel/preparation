//package career.website.codeforce.under100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StonesOnTable {
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }


    public static void main(String[] args) {

        StonesOnTable.FastScanner fs = new StonesOnTable.FastScanner();
        int length = fs.nextInt();
        String input = fs.next();
        int start =0;
        int count =0;
        for(int i=1;i<length;i++){
            if(input.charAt(start) != input.charAt(i)){
               start=i;
            }else{
                count++;
            }
        }
        System.out.println(count);
        ////////////// another solution
        int remove=0;
        for(int i=1;i<length;i++){
            if(input.charAt(i-1)==input.charAt(i)){
                remove++;
            }
        }
//        return remove;

        System.out.println(remove);


    }
}
