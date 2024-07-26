package career.website.codeforce.under100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PanoramixPrediction {
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

        PanoramixPrediction.FastScanner fs=new PanoramixPrediction.FastScanner();
        int x = fs.nextInt();
        int y = fs.nextInt();
        String ans ="NO";
        for(int i=x+1;i<=y;i++){
            boolean isOdd = true;
            for(int j=2;j<=Math.sqrt(i);j++){
                if(i%j == 0){
                    isOdd = false;
                    break;
                }
            }
            if(isOdd){
                if(y == i){
                    ans ="YES";
                }
                break;
            }
        }
        System.out.println(ans);

    }
}
