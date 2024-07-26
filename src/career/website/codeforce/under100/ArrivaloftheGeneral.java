package career.website.codeforce.under100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class ArrivaloftheGeneral {
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
        int noOfEle = fs.nextInt();
        int arr[]=new int[noOfEle];
        int max=Integer.MIN_VALUE,min=Integer.MAX_VALUE,maxIndex=0,minIndex=0;
        IntStream.range(0,noOfEle).forEach(index->{
            arr[index]=fs.nextInt();
        });

        for(int i=0;i<noOfEle;i++){
            if(max < arr[i] && max != arr[i]){
                max = arr[i];
                maxIndex=i;
            }
            else if(min >= arr[i]){
                min = arr[i];
                minIndex=i;
            }
        }

//        System.out.println(" max ="+max+" : min ="+minIndex);

        int minChanges=noOfEle-1-minIndex;
        int maxChanges=maxIndex == noOfEle-1 ? maxIndex-1:maxIndex;

//        System.out.println(" max ="+maxChanges+" : min ="+minChanges);

        System.out.println((minChanges+maxChanges));





    }

}
