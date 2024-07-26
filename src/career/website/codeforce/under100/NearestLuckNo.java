package career.website.codeforce.under100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NearestLuckNo {
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

        NearestLuckNo.FastScanner fs=new NearestLuckNo.FastScanner();

        String no = fs.next();
        int count=0;
        for(int i=0;i<no.length();i++){
            int startDigit =Integer.parseInt(no.charAt(i)+"");
//            int endDigit =Integer.parseInt(no.charAt(no.length()-i-1)+"");
            if(startDigit == 4 || startDigit == 7){
                count++;
            }
        }
        if(count == 4 || count == 7 ) {
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }
}
