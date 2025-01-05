package career.datastructure.recurssion.subsequence.subsetAndCombinnation.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
given num array of fixed length 3 and operator array with 3 fixed length
return expression which evalute result is given target.

1] you need to add ( and ) brackets.
2*3+4 = 10 target
result = (2*3)+4  || ((2*3)+4)


Approach
1] DFS - recursive + hashing
 */
public class GoogleQuestionRound1 {
    public static void main(String[] args) {
//        int num[]={2,3,1};
//        int num[]={1,3,2}, target = 7;
//        int num[]={1,1,1}, target=2;
        int num[]={2,2,2}, target=7;
        char op[]={'*','-','+'};

        List<String> res = new ArrayList<>();
        helper(num,op,0,target,0,res,new StringBuilder());
        if(res.isEmpty()) {
            // only helper won't work in this scenario
//            num = new int[]{1, 3, 2}; // helper won't work
            // not getting ans because we are trying to resolve question from left to right side
            // so here where 1*3 | 1-3 | 1+3 | -> calculated first then second part
            // in first part we got 3,-2,4 and 3*2,-2*2,4*2 => 6,-4,8 but not seven
            // so when we reverse it we got ans. it is valid one for 3 nos
            // as asked in google interview.
            for (int i = 0; i < num.length / 2; i++) {
                int temp = num[i];
                num[i] = num[num.length - 1 - i];
                num[num.length - 1 - i] = temp;
            }
            System.out.println(Arrays.toString(num));
            helper(num,op,0,target,0,res,new StringBuilder());
        }



        System.out.println(res);

//        res.clear();
//
//        ArrayList<Integer> lst = new ArrayList<>();
//        for(int no:num){
//            lst.add(no);
//        }
//
//        System.out.println(isReachable(lst,7));
    }


    private static void helperOp(int num[], char op[], int index, int target, int sum, List<String> res, StringBuilder sb){

        for(int i=0;i<num.length;i++){
            if(i==0){

            }else {

            }
        }
    }

    private static void helper(int num[], char op[], int index, int target, int sum, List<String> res, StringBuilder sb){
        //base
        if(index == num.length){
            if(target == sum) res.add(sb.toString());
            return;
        }
        if(index == 0){
            sb.append("(");
            sb.append(num[index]);
            helper(num,op,index+1,target,num[index],res,sb);
        }
        else if(index <= num.length-1){
            int no = num[index];
            int previousSum = sum;
            for(int i=0;i<op.length;i++){
                char operation=op[i];
                sb.append(operation);
                sb.append(no);
                if(index != num.length-1) {
                    sb.append(")");
                }
                if(operation == '*'){
                    sum*=no;
                }else if(operation == '+'){
                    sum+=no;
                }else{
                    sum-=no;
                }
                System.out.println(sum+" "+target+" "+previousSum);
                helper(num,op,index+1,target,sum,res,sb);
                sb.deleteCharAt(sb.length()-1);
                sb.deleteCharAt(sb.length()-1);
                if(index != num.length-1) {
                    sb.deleteCharAt(sb.length()-1);
                }
                System.out.println(sum+" "+target+" "+previousSum);
                sum = previousSum;
            }
        }
    }


    public static boolean isReachable(ArrayList<Integer> list, int target) {
        if (list == null || list.size() == 0)
            return false;

        int i = 0;
        int j = list.size() - 1;

        ArrayList<Integer> results = getResults(list, i, j,new StringBuilder());

        for (int num : results) {
            if (num == target) {
                return true;
            }
        }

        return false;
    }

    public static ArrayList<Integer> getResults(ArrayList<Integer> list,
                                         int left, int right,StringBuilder sb) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        if (left > right) {
            return result;
        } else if (left == right) {
            System.out.println(sb.toString());
            result.add(list.get(left));
            return result;
        }

        for (int i = left; i < right; i++) {

            ArrayList<Integer> result1 = getResults(list, left, i,sb);
            ArrayList<Integer> result2 = getResults(list, i + 1, right,sb);

            for (int x : result1) {
                for (int y : result2) {
                    result.add(x + y);
                    result.add(x - y);
                    result.add(x * y);
                    if (y != 0)
                        result.add(x / y);
                }
            }
        }

        return result;
    }
}
