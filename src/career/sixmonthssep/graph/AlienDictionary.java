package career.sixmonthssep.graph;

import java.util.*;

/*
https://leetcode.com/problems/alien-dictionary/description/
There is a new alien language that uses the English alphabet. However, the order of the letters is unknown to you.

You are given a list of strings words from the alien language's dictionary. Now it is claimed that the strings in words are
sorted lexicographically
 by the rules of this new language.

If this claim is incorrect, and the given arrangement of string in words cannot correspond to any order of letters, return "".

Otherwise, return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there are multiple solutions, return any of them.

 */

public class AlienDictionary {

    static Map<Character, List<Character>> map;
    static Set<Character> set;
    static boolean isValid=false;
    public static void main(String[] args) {
//        String words[] = {"wrt","wrf","er","ett","rftt"};
//        String words[] = {"z","x","a","zb","zx"}; // all nodes are not covered.
//        String words[] = {"z","z"}; // all nodes are not covered.
        String words[] = {"abc","ab"}; // edge case
        System.out.println(alienOrder(words));
    }

    public static String alienOrder(String[] words) {
        if(words == null || words.length == 0 || words[0].equalsIgnoreCase("") ){
            return null;
        }

        // directed graph -> topo sort

        createGraph(words);
        if(isValid) return "";
        System.out.println(map);

        int inDegree[]=new int[26];
        int numNodes = map.size(); //To check, is it cover all nodes or not

        for(Character c:map.keySet()){
            for(Character nbC:map.get(c)){
                inDegree[nbC-'a']++;
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for(Character c:map.keySet()){
            if(inDegree[c-'a'] == 0){
                queue.add(c);
            }
        }

        System.out.println(Arrays.toString(inDegree));
        System.out.println(queue);

        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()){
            Character ch = queue.poll();
            sb.append(ch);
            for(Character c:map.get(ch)){
                inDegree[c-'a']--;
                if(inDegree[c-'a'] == 0){
                    queue.add(c);
                }
            }
        }
        if(sb.length() < numNodes) return "";
        return sb.toString();
    }

    private static void createGraph(String[] words){
        map = new HashMap<>();
        int n = words.length;
        for (String word : words) {
            for (Character c : word.toCharArray()) {
                map.put(c, new ArrayList<>()); // c to all its next
            }
        }


        for(int i=1;i<n;i++){
            String prev = words[i-1];
            String curr = words[i];

            if (prev.length() > curr.length() && prev.startsWith(curr)) {
                isValid = true;
                return;
            }

            int length = Math.min(prev.length(),curr.length());

            for(int j=0;j<length;j++){
                if(prev.charAt(j) != curr.charAt(j)){
                    map.get(prev.charAt(j)).add(curr.charAt(j));
                    break;
                }
            }
        }
    }
}
