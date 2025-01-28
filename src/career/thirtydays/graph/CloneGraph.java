package career.thirtydays.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://leetcode.com/problems/clone-graph/
 */
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
    static Map<Node,Node> map;

    public static void main(String[] args) {

    }

    public static Node cloneGraph(Node node) {
        map = new HashMap<>();
        return dfs(node,map);
    }

    private static Node dfs(Node node,Map<Node,Node> map){
        if(node == null) return null;

        if(map.containsKey(node)){
            return map.get(node);
        }

        Node newNode = new Node(node.val);
        map.put(node,newNode);
        for(Node nbNode :node.neighbors){
            newNode.neighbors.add(dfs(nbNode,map));
        }
        return newNode;
    }
}
