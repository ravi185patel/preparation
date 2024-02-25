package career.website.codeforce.under100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bronze {
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
        Bronze.FastScanner fs=new Bronze.FastScanner();
        String input = fs.next();
        System.out.println(convertIntoNo(input));
    }

    public static String convertIntoNo(String innput){
        char temp[]=innput.toCharArray();
        StringBuilder sb= new StringBuilder();

        for(int i=0;i<temp.length;i++){
            if(temp[i] == '.'){
                sb.append("0");
            }else{
                if(temp[i+1] == '-'){
                    sb.append("2");
                }else{
                    sb.append("1");
                }
                i++;
            }
        }
        return sb.toString();
    }
}
