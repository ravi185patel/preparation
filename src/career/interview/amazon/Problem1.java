package career.interview.amazon;

import java.util.*;

class Node{
    int sum;
    String eq;

    public Node(int sum, String eq) {
        this.sum = sum;
        this.eq = eq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return sum == node.sum && Objects.equals(eq, node.eq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sum, eq);
    }
}
public class Problem1 {
    public static void main(String[] args) {

        int []nums={1,2,3,5};
        char []ops={'*','+','-'};
        int target = 25;
        findCnt(nums,0,ops,new String(),target,0);
        optimize(nums,ops,target);
    }

    /*

    1,9,6
    1,6,9
    n=3;
    9*3,6*2,1*1  = 27 Y
    9*3,6*1,1*2 = 27

    6*3,9*2,1*1 = 18,18 X
    6*3,9*1,1*2

    1*3,9*2,6*1
    1*3,9*1,6*2


     */
    public static void findCnt(int nums[],int index,char[]ops,String op,int target,int sum){
        if(index == nums.length){
            if(target == sum){
                System.out.println("achieved = "+op+"");
            }
            return;
        }
        System.out.println(index+" "+sum);
        if(index == 0){
            findCnt(nums,index+1,ops,"( ("+nums[index],target,nums[index]);
        }else {
            for (int i = 0; i < ops.length; i++) {
                if (ops[i] == '*') {
                    sum = sum * nums[index];
                    findCnt(nums, index + 1, ops, op + " * " +nums[index]+" )", target, sum);
                    sum = sum / nums[index];
                } else if (ops[i] == '+') {
                    sum = sum + nums[index];
                    findCnt(nums, index + 1, ops, op + " + " +nums[index]+" )", target, sum);
                    sum = sum - nums[index];
                } else if (ops[i] == '-') {
                    sum = sum - nums[index];
                    findCnt(nums, index + 1, ops, op + " - " +nums[index]+" )", target, sum);
                    sum = sum + nums[index];
                }
            }
        }
    }

    private static void optimize(int nums[],char ops[],int target){
        StringBuilder sb = new StringBuilder();

        Map<Integer, Set<Node>> map = new HashMap<>();
        map.put(0,new HashSet<>());
        String open = "";
        for(int i=0;i<nums.length-1;i++){
            open = open +" (";
        }
        map.get(0).add(new Node(nums[0],open+" "+nums[0]));

        for(int i=1;i<nums.length;i++){
            map.put(i,new HashSet<>());
            for (Node prevNode : map.get(i - 1)) {
                for (char c : ops) {
                    int newVal = 0;
                    String newEq = "";
                    if (c == '*') {
                        newEq = prevNode.eq + " * "+(nums[i] +" ) ");
                        newVal = prevNode.sum * nums[i];
                    }else if( c== '+'){
                        newEq = prevNode.eq + " + "+(nums[i] +" ) ");
                        newVal = prevNode.sum + nums[i];
                    }else if(c == '-'){
                        newEq = prevNode.eq + " - "+(nums[i] +" ) ");
                        newVal = prevNode.sum - nums[i];
                    }
                    map.get(i).add(new Node(newVal, newEq));
                }
            }
        }

        for(Node node:map.get(nums.length-1)){
            if(node.sum == target){
                System.out.println("Achieved "+node.eq);
            }
        }
    }
}
