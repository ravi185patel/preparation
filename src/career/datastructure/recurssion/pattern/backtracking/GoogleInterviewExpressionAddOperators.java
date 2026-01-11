package career.datastructure.recurssion.pattern.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
| Constraint                      | Best Approach                          |
| ------------------------------- | -------------------------------------- |
| 3 numbers only                  | Explicit enumeration                   |
| No parentheses                  | Backtracking with `lastValue`          |
| Unlimited numbers + parentheses | **Expression generation + evaluation** |


Because the input size is fixed to three numbers,
I enumerate the only two valid parenthesization forms: (a op b) op c and a op (b op c).
This gives a constant-time solution

 */

public class GoogleInterviewExpressionAddOperators {
    public static void main(String[] args) {
//        int arr[]={1,2,3},target=6;
        solve(6, new int[]{1,2,3});
    }

    public static void solve(int target,int nums[]){
        List<String> noOfPossibility=new ArrayList<>();
        char ops[]={'*','+','-'};
        for(char op1:ops){
            for(char op2:ops){
                int first = apply(nums[0],nums[1],op1);
                int result =  apply(first,nums[2],op2);
                if(result == target){
                    noOfPossibility.add("(("+nums[0]+" "+op1+" "+nums[1]+") "+op2+" "+nums[2]+")");
                }

                int second = apply(nums[1],nums[2],op2);
                result = apply(nums[0],second,op1);
                if(result == target){
                    noOfPossibility.add("("+nums[0]+" "+op1+" ("+nums[1]+" "+op2+" "+nums[2]+"))");
                }

            }
        }
        System.out.println(noOfPossibility);
    }

    public static int apply(int no1,int no2,char op){
        if(op == '*') return no1*no2;
        else if(op=='+') return no1+no2;
        else  return no1-no2;
    }
}
