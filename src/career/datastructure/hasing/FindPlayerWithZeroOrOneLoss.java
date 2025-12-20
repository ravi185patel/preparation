package career.datastructure.hasing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*

 */
public class FindPlayerWithZeroOrOneLoss {
    public static void main(String[] args) {
        int [][]matches = 
//        {
//                {1,3}, {2,3}, {3,6}, {5,6}, {5,7}, {4,5}, {4,8},
//                {4,9}, {10,4}, {10,9}
//        };
                {{2,3}, {1,3}, {5,4}, {6,4}};
        System.out.println(findWinners(matches));
    }

    public static List<List<Integer>> findWinners(int[][] matches) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>()); // won;
        res.add(new ArrayList<>()); // loss;

        // zero or one loss;
        int freq[]=new int[1000001];
        for(int match[]:matches){
            freq[match[1]]++;
        }

        for(int match[]:matches){
            int won = match[0];
            int loss = match[1];

            if(freq[loss] == 1){
                res.get(1).add(loss);
                freq[loss]=-1;
            }
            if(freq[won] == 0){
                res.get(0).add(won);
                freq[won] = -1;
            }
        }

        Collections.sort(res.get(0));
        Collections.sort(res.get(1));

        return res;

    }
}
