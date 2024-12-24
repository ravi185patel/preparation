package career.datastructure.graph.other;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AlignDictionary {
    public static void main(String[] args) {
        int N = 5, K = 4;
        String dict[] = {"baa","abcd","abca","cab","cad"};
        String res = findOrder(N,K,dict);
        System.out.println(res);
    }

    private static String findOrder(int N,int K,String dict[]){
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            adj.add(new ArrayList<>());
        }


        for (int i = 0; i < N - 1; i++) {
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

        List<Integer> topo = topoSort(K, adj);
        String ans = "";
        for (int it : topo) {
            ans = ans + (char)(it + (int)('a'));
        }

        return ans;
    }
    private static List<Integer> topoSort(int V, List<List<Integer>> adj) {

        int inDegree[]=new int[V];
        for(int i=0;i<V;i++){
            for(int it:adj.get(i)){
                inDegree[it]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<V;i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }

        List<Integer> topo = new ArrayList<>();
        while(!queue.isEmpty()){
            int node = queue.poll();
            topo.add(node);

            for(int it:adj.get(node)){
                inDegree[it]--;
                if(inDegree[it] == 0){
                    queue.add(it);
                }
            }
        }

        return topo;
    }
}
