package career.sixmonthssep.graph.practice;

import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class CloneGraph {
    static Map<Node,Node> map ;
    public static void main(String[] args) {

    }

    public static Node cloneGraph(Node node) {
        map = new HashMap<>();
        return bfs(node);
    }

    public static Node dfs(Node node){
        if(node == null) return null;

        if(!map.containsKey(node)){
            return map.get(node);
        }

        Node newNode = new Node(node.val);
        map.put(node,newNode);
        for(Node nb:node.neighbors){
            map.get(node).neighbors.add(dfs(nb));
        }
        return map.get(node);
    }

    public static Node bfs(Node node){
        if(node == null) return null;

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        map.put(node,new Node(node.val));
        while(!queue.isEmpty()){
            Node n = queue.poll();
            for(Node nb:n.neighbors){
                if(!map.containsKey(nb)){
                    map.put(nb,new Node(nb.val));
                    queue.add(nb);
                }
                map.get(n).neighbors.add(map.get(nb));
            }
        }
        return map.get(node);
    }
}
