package career.website.codeforce.under100;
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BeautifulMatrix {
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
        FastScanner fs=new FastScanner();
        int matrix[][]=new int[5][5];
        int ans=0;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                matrix[i][j]=fs.nextInt();
                if(matrix[i][j] == 1){
                    ans= Math.abs(2-i)+Math.abs(2-j);
                    i=5;j=5;
                }
            }
        }
        System.out.println(ans);
//                findDistanceFromCenter(matrix)
    }

    public static int findDistanceFromCenter(int mat[][]){
        int x=0,y=0;

        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[i].length;j++){
                if(mat[i][j] == 1){
                   return Math.abs(2-i)+Math.abs(2-j);
                }
            }
        }
        return -1;
    }


}
