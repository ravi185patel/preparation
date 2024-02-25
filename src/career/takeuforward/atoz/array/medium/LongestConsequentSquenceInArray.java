package career.takeuforward.atoz.array.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConsequentSquenceInArray {
    public static void main(String[] args) {
        int arr[]={100, 200, 1, 3, 2, 4};
//        int arr[]= {3, 8, 5, 7, 6};
//        int arr[]={1,3,5,7};
        int res = findMaxIncreaseSequence(arr);
        res = findMaxIncreaseSequenceMemo(arr);
        System.out.println(res);
    }

    public static int findMaxIncreaseSequence(int arr[]){
        Arrays.sort(arr);
        int max=0,cnt=1;

        for(int i=0;i<arr.length-1;i++){
            if(arr[i]+1 == arr[i+1]){
                cnt++;
            }else{
                cnt=1;
            }
            max = Math.max(max,cnt);
        }

        return max;
    }


    public static int findMaxIncreaseSequenceMemo(int arr[]){
        Set<Integer> set= new HashSet<>();
        for(int i:arr){
            set.add(i);
        }

//        Arrays.sort(arr);
        int max=0;

        for(int i=0;i<arr.length-1;i++){
            int start = arr[i]+1;
            int cnt =1;
            while(set.contains(start)){
                cnt++;
                start++;
            }
            max = Math.max(max,cnt);
        }

        return max;
    }


}
