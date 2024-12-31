//package career.website.leetcode.string.medium;
//
//public class MiniParser {
//    public static void main(String[] args) {
//
//    }
//
//    {
//        /**
//         * // This is the interface that allows for creating nested lists.
//         * // You should not implement it, or speculate about its implementation
//         * public interface NestedInteger {
//         *     // Constructor initializes an empty nested list.
//         *     public NestedInteger();
//         *
//         *     // Constructor initializes a single integer.
//         *     public NestedInteger(int value);
//         *
//         *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
//         *     public boolean isInteger();
//         *
//         *     // @return the single integer that this NestedInteger holds, if it holds a single integer
//         *     // Return null if this NestedInteger holds a nested list
//         *     public Integer getInteger();
//         *
//         *     // Set this NestedInteger to hold a single integer.
//         *     public void setInteger(int value);
//         *
//         *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
//         *     public void add(NestedInteger ni);
//         *
//         *     // @return the nested list that this NestedInteger holds, if it holds a nested list
//         *     // Return empty list if this NestedInteger holds a single integer
//         *     public List<NestedInteger> getList();
//         * }
//         */
//        class Solution {
//            public NestedInteger deserialize(String s) {
//                NestedInteger zero = new NestedInteger(0);
//                helper(s,zero);
//                return zero.getChild(0);
//            }
//
//            private void helper(String s,NestedInteger parent){
//                if(s.length() == index){
//                    return;
//                }
//
//                int index = s.indexOf("[");
//                if(index != -1){
//                    int commaInd = s.indexOf(",");
//                    int value = Integer.parseInt(s.substring(index+1,commaInd));
//                    NestedInteger nestedInteger = new NestedInteger(value);
//                    helper(s.substring(commaInd+1),nestedInteger);
//                }else{
//                    int closeBr = s.indexOf("]");
//                    int commaInd = s.indexOf(",");
//                    int value = Integer.parseInt(s.substring(0,closeBr-1));
//                    NestedInteger child = new NestedInteger(value);
//                    parent.getList().add(child);
//                    res.add(child);
//                    helper(s.substring(commaInd),parent);
//                }
//            }
//        }
//    }
//}
