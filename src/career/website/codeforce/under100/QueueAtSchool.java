package career.website.codeforce.under100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
6 2
BBGBBG
BGBBGB

GBBGBB

5 2
BGGBG
GGBGB

5 1
BGGBG
GBGGB


 */
public class QueueAtSchool {
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
        QueueAtSchool.FastScanner fs=new QueueAtSchool.FastScanner();
        int n = fs.nextInt();
        int t = fs.nextInt();
        String queue = fs.next();

        System.out.println(findNewQueue(queue.toCharArray(),t));
    }

    public static String findNewQueue(char queue[],int t){
        int second =0;

//        char temp;
        for(int j=0;j<t;j++)
        {
            for(int i=0;i<queue.length-1;i++)
            {
                if(queue[i]=='B' && queue[i+1]!='B')
                {
                    char temp=queue[i];
                    queue[i]=queue[i+1];
                    queue[i+1]=temp;
                    i++;
                }

            }
        }
        return new String(queue);
    }
}
