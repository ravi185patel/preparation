package career.datastructure.prefixsum;


import java.util.Arrays;

/*

clarification questions
1] is there any negative number in array : only 0/1
2] what will be range of n : 1 to 2000


Approach
1] brute force approach
2] prefix sum approach
 */
public class MaxOpToMoveAlllBallsToEachBox {
    public static void main(String[] args) {
        int res[]=minOperations("001011");
        System.out.println(Arrays.toString(res));
        res=minOperations("110");
        System.out.println(Arrays.toString(res));
    }

    public static int[] minOperations(String boxes) {
//        return findMinOp(boxes.toCharArray());
        return findMinOpPS(boxes.toCharArray());
    }

    private static int[] findMinOp(char []boxes){
        int n = boxes.length;
        int res[]=new int[n];
        for(int i=0;i<n;i++){

            for(int j=i;j>=0;j--){
                if(boxes[j] == '1')
                    res[i] += i-j;
            }

            for(int j=i;j<n;j++){
                if(boxes[j] == '1')
                    res[i] += j-i;
            }

        }
        return res;
    }


    private static int[] findMinOpPS(char []boxes){
        int n = boxes.length;
        int res[]=new int[n];
        int cnt=0,ops=0;
        for(int i=0;i<n;i++){
            res[i]+=ops;
            if(boxes[i] == '1'){
                cnt++;
            }
            ops += cnt;
        }
        ops=cnt=0;

        for(int i=n-1;i>=0;i--){
            res[i]+=ops;
            if(boxes[i] == '1'){
                cnt++;
            }
            ops += cnt;
        }
        return res;
    }
}
