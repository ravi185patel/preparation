package career.datastructure.graph.component;

import career.datastructure.graph.template.DisJoinUnion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SatisfiabilityOfEqualityEq {
    public static void main(String[] args) {
        String [] equations = {"a==b","b!=a"};
//        String [] equations = {"a==b","b==a"};

        System.out.println(equationsPossibleDSU(equations));
        System.out.println(equationsPossible(equations));

    }

    public static boolean equationsPossible(String[] equations) {
        List<Integer>[] graph = new ArrayList[26];
        for(int i=0;i<26;i++){
            graph[i] = new ArrayList<>();
        }

        for(String eq:equations){
            if(eq.charAt(1)=='='){
                int x = eq.charAt(0)-'a';
                int y = eq.charAt(3)-'a';
                graph[x].add(y);
                graph[y].add(x);
            }
        }

        int color[]=new int[26];
        Arrays.fill(color,-1);

        for(int i=0;i<26;i++){
            if(color[i] == -1){
                equationsPossibleDFS(i,i,color,graph);
            }
        }

        for(String eq:equations){
            if(eq.charAt(1)=='!'){
                int x = eq.charAt(0)-'a';
                int y = eq.charAt(3)-'a';
                if(color[x]==color[y]){
                    return false;
                }
            }
        }
        return true;
    }

    public static void equationsPossibleDFS(int node, int c, int[]color, List<Integer> graph[]) {
        if(color[node]==-1){
            color[node]=c;
            for(int nbNode:graph[node]){
                equationsPossibleDFS(nbNode,node,color,graph);
            }
        }
    }
    public static boolean equationsPossibleDSU(String[] equations) {
        DisJoinUnion ds = new DisJoinUnion(26);

        int root[]=new int[26];
        for(int i=0;i<26;i++){
            root[i]=i;
        }

        for(String eq:equations){
            if(eq.charAt(1) == '='){
                int x = eq.charAt(0)-'a';
                int y = eq.charAt(3)-'a';
                ds.union(x,y);
            }
        }

        for(String eq:equations){
            if(eq.charAt(1) == '!'){
                int x = eq.charAt(0)-'a';
                int y = eq.charAt(3)-'a';
                if(ds.find(x)==ds.find(y)){
                    return false;
                }
            }
        }

        return true;
    }
}
