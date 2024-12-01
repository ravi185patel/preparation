package career.interview.google;

import java.util.*;

public class RemoveEdgeBetweenNodeCountComponent {
    public static void main(String[] args) {
        int tree[]= {1, 2, 3, 4, 5, -1, -1};
        int edges[][]= {{1, 2}, {2, 4}};
        boolean res = getDisconnectedComponent(tree,edges);
        System.out.println(res);
    }

    private static boolean getDisconnectedComponent(int tree[],int edges[][]){
        Map<Integer, List<Integer>> hmap = new HashMap<>();
        for(int i:tree){
            if(i==-1) continue;
            hmap.put(i,new ArrayList<>());
        }

        for(int i=0;i<tree.length;i++){
            if(2*i+2 < tree.length) {
                if(tree[2 * i + 1] != -1)
                    hmap.get(tree[i]).add(tree[2 * i + 1]);
                if(tree[2 * i + 2] != -1)
                    hmap.get(tree[i]).add(tree[2 * i + 2]);
            }
        }

        for(int[] edge:edges){
            List<Integer> nodeEdges = hmap.getOrDefault(edge[0],new ArrayList<>());
            if(nodeEdges.contains(edge[1])){
                nodeEdges.remove(new Integer(edge[1]));
            }
        }
        System.out.println(hmap);
        Queue<Integer> queue = new LinkedList<>();
        int count=0;
        List<Integer> res = new ArrayList<>();
        List<Integer> visited = new ArrayList<>();
        for(int key:hmap.keySet()) {
            if(!visited.contains(key)) {
                queue.add(key);
                count=1;
                while (!queue.isEmpty()) {
                    for (int node : hmap.get(queue.poll())) {
                        visited.add(node);
                        queue.add(node);
                        count++;
                    }
                }
                res.add(count);
            }
        }
        System.out.println(res);
        return false;
    }
}
