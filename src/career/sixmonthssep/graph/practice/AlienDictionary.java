package career.sixmonthssep.graph.practice;

import java.util.*;

public class AlienDictionary {
    public static void main(String[] args) {
        System.out.println(getAlienLanguage(new String[]{"a","aa","aaa"},3));
        System.out.println(getAlienLanguage(new String[]{"abc","bcd","cde","def"},9));
    }
    public static String getAlienLanguage(String []dict, int K) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            adj.add(new ArrayList<>());
        }

        for(int i=0;i<dict.length-1;i++){
            String s1 = dict[i];
            String s2 = dict[i + 1];
            int len = Math.min(s1.length(), s2.length());
            for (int ptr = 0; ptr < len; ptr++) {
                if (s1.charAt(ptr) != s2.charAt(ptr)) {
                    adj.get(s1.charAt(ptr) - 'a').add(s2.charAt(ptr) - 'a');
                    break;
                }
            }
        }

        String res=topoSort(adj,K);

        return res;
    }

    public static String topoSort(List<List<Integer>> adj,int K){
        int inDegree[] = new int[K];

        for(int i=0;i<K;i++) {
            for(int j:adj.get(i)){
                inDegree[j]++;
            }
        }

        StringBuffer sb=new StringBuffer("");

        Queue<Integer> queue=new LinkedList<>();


        for(int i=0;i<K;i++){
            if(inDegree[i]==0){
                queue.add(i);
            }
        }

        while(!queue.isEmpty()){
            int parent=queue.remove();
            sb.append((char)(parent + (int)('a')));
            for(int child:adj.get(parent)){
                inDegree[child]--;
                if(inDegree[child] == 0){
                    queue.add(child);
                }
            }
        }

        return sb.toString();
    }
}
