package career.website.leetcode.string.medium;

import java.util.*;

public class EvaluateBracketPairsOfString {
    public static void main(String[] args) {
        String s = "(name)is(age)yearsold";
        List<List<String>> knowledge =new ArrayList<>();
        knowledge.add(new ArrayList<>());
        knowledge.add(new ArrayList<>());
        knowledge.get(0).add("name1");
        knowledge.get(0).add("bob");
        knowledge.get(1).add("age");
        knowledge.get(1).add("two");
        Map<String,String> map = convertIntoMap(knowledge);
        String ans = evaluate(s,map);
        ans = evaluateImp(s,map);

        System.out.println(ans);

    }

    private static Map<String,String> convertIntoMap(List<List<String>> knowledge){
        Map<String,String> map =  new HashMap<>();
        for(List<String> lst: knowledge){
            map.put(lst.get(0),lst.get(1));
        }
        return map;
    }

    public static String evaluate(String s, Map<String,String> knowledge) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> st = new Stack<>();
        boolean flag = false;
        for(char c:s.toCharArray()){
            if(c=='('){
                st.push(c);
                flag = true;
            }else{
                if(c==')'){
                    String newS="";
                    while(!st.isEmpty() && st.peek()!='('){
                        newS = st.pop() + newS;
                    }
                    System.out.println(newS);
                    sb.append(knowledge.getOrDefault(newS,"?"));
                    flag = false;
                }else{
                    st.push(c);
                    if(!flag){
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }


    public static String evaluateImp(String s, Map<String,String> knowledge) {
        StringBuilder sb = new StringBuilder();
        StringBuilder currentKey = new StringBuilder();
        boolean flag = false;
        for(char c:s.toCharArray()){
            if(c=='('){
                flag = true;
            }else{
                if(c==')'){
                    sb.append(knowledge.getOrDefault(currentKey.toString(),"?"));
                    currentKey.setLength(0);
                    flag = false;
                }else{
                    if(flag) {
                        currentKey.append(c);
                    }else{
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }
}
