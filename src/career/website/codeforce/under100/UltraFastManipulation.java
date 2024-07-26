//package career.website.codeforce.under100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UltraFastManipulation {
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

        UltraFastManipulation.FastScanner fs=new UltraFastManipulation.FastScanner();
        char[] x = fs.next().toCharArray();
        char[] y = fs.next().toCharArray();
        StringBuffer sb= new StringBuffer();
        for(int i=0;i<x.length;i++){
            if(x[i] != y[i]){
               sb.append("1");
            }else{
                sb.append("0");
            }
        }

        System.out.println(sb.toString());

    }
}
