package career.datastructure.graph.cycle;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DetectCycleInGraphUndirectedGraph {
    public static void main(String[] args) {
//        int [][]adj = {{1}, {0,2,4}, {1,3}, {2,4}, {1,3}};
        int[][]adj = {{}, {340, 455}, {}, {231}, {}, {}, {},
                {111, 42}, {445}, {}, {}, {}, {}, {}, {368}, {364}, {},
                {150}, {357, 392, 463}, {349, 229}, {}, {405}, {414},
                {152}, {454}, {139}, {205, 484, 382}, {395, 364, 403},
                {90, 307, 210, 100}, {}, {90}, {180, 213}, {}, {145},
                {316}, {431, 87, 166}, {143}, {464}, {354}, {}, {}, {}, {282, 7},
                {134, 262}, {308, 407}, {}, {}, {466, 174}, {391, 444, 287}, {209, 212},
                {486}, {408}, {461}, {}, {56}, {409}, {54, 427, 452}, {323}, {}, {},
                {378}, {}, {235}, {}, {}, {168}, {302}, {454}, {275, 306}, {},
                {422}, {319}, {162}, {310}, {98}, {419}, {}, {464}, {}, {285}, {}, {238}, {151, 350}, {}, {}, {258}, {}, {380, 35}, {365, 146, 102, 477}, {}, {28, 475, 30}, {387}, {345}, {}, {}, {}, {191}, {}, {74}, {343, 108, 189}, {28, 110}, {}, {300, 88, 136}, {169, 462}, {}, {337, 247}, {}, {}, {99}, {219}, {100}, {7}, {}, {}, {314}, {154, 407}, {297}, {}, {224, 171}, {}, {}, {337}, {176}, {392}, {363}, {361}, {}, {}, {409}, {}, {}, {}, {182}, {412}, {43}, {}, {102}, {432}, {326}, {25, 336}, {}, {371, 241}, {}, {36}, {388, 411, 274}, {33}, {88}, {334}, {390, 417}, {}, {17}, {82}, {275, 23}, {295}, {115, 485, 216}, {200}, {}, {365}, {}, {}, {}, {463, 460}, {221, 168, 72}, {}, {}, {}, {480, 35}, {183}, {486, 65, 162, 192}, {103, 378}, {}, {118}, {241}, {}, {47}, {178, 322}, {400, 122}, {}, {219, 175}, {438}, {31}, {}, {132}, {167, 467}, {}, {}, {489, 394}, {434, 422}, {}, {99}, {}, {96}, {168}, {338}, {297}, {}, {}, {}, {}, {400}, {155}, {427}, {486}, {}, {}, {26, 342}, {316}, {417}, {378}, {228, 49}, {28, 254, 372}, {257, 433}, {468, 49}, {31}, {}, {386}, {292, 154}, {454}, {}, {109, 178}, {363}, {162, 422}, {}, {}, {118}, {}, {}, {230}, {209, 310}, {453, 19}, {353, 227}, {3}, {330}, {}, {423}, {62}, {}, {317}, {306, 81, 363}, {}, {288}, {172, 141}, {}, {}, {357}, {468}, {272}, {105}, {}, {}, {}, {}, {305}, {}, {210}, {}, {}, {261, 211}, {85, 456, 443}, {}, {}, {257}, {43}, {}, {}, {}, {425}, {}, {491}, {}, {}, {}, {246, 441}, {}, {144, 377}, {68, 315, 152}, {}, {366, 332}, {}, {}, {}, {}, {42}, {}, {411}, {360, 79}, {}, {48}, {423, 240}, {}, {}, {487}, {216}, {}, {}, {153, 333}, {}, {116, 194}, {}, {}, {102}, {}, {66}, {}, {}, {252, 425}, {68, 238}, {28}, {44}, {356}, {228, 73}, {}, {}, {}, {114}, {456, 275}, {452, 206, 34}, {237}, {}, {71}, {389}, {}, {488, 175}, {57}, {332, 396}, {348}, {138, 359}, {}, {}, {430, 400}, {232}, {350}, {324, 277}, {295}, {147}, {}, {139, 353}, {105, 121}, {394, 193}, {349}, {1}, {}, {205}, {99}, {}, {92}, {}, {}, {444, 325}, {339, 19}, {331, 82}, {}, {}, {230, 336}, {38}, {}, {309}, {18, 244}, {}, {472, 326}, {285, 464}, {125}, {484}, {220, 124, 238}, {27, 15}, {88, 157}, {277}, {486}, {14}, {}, {}, {448, 141}, {399, 210}, {}, {}, {}, {}, {401, 274}, {208, 60, 169}, {}, {87}, {}, {26}, {}, {404}, {}, {215}, {445, 91}, {144}, {320}, {148, 421}, {48}, {123, 18}, {}, {338, 186}, {27}, {324}, {}, {456}, {372}, {176, 199, 329, 422}, {377}, {}, {468, 27}, {384}, {21}, {410}, {115, 44}, {51}, {55, 128, 458}, {406}, {144, 284}, {133}, {}, {22, 474}, {}, {464, 428}, {207, 148}, {}, {75, 488}, {}, {390}, {70, 221, 400, 187, 467}, {288, 234, 443}, {}, {266, 305}, {}, {201, 56}, {416}, {455}, {329}, {35}, {137}, {211}, {187}, {}, {}, {}, {179}, {}, {}, {272, 485}, {}, {258, 423}, {48, 348}, {387, 8}, {}, {}, {371}, {}, {455}, {}, {316, 56, 482}, {229}, {67, 217, 24}, {1, 429, 450}, {315, 258, 398}, {}, {409}, {}, {161}, {52}, {103}, {161, 18}, {77, 416, 360, 37}, {}, {47}, {183, 422}, {212, 403, 245}, {488}, {}, {}, {359}, {}, {414},
                {90}, {}, {88}, {}, {}, {166}, {}, {452}, {}, {26, 362}, {154, 441}, {202, 50, 168, 367}, {291}, {419, 322, 469}, {186}, {}, {268}};
        int length = adj.length+1;
        boolean visited[]=new boolean[length];
//        int index=0;
//        for(int a[]:adj){
//            System.out.println(index+" -> "+Arrays.toString(a));
//            index++;
//        }
        System.out.println(detectCycleUsingDfs(adj,visited));
        Arrays.fill(visited,false);
        System.out.println(detectCycleUsingBfs(adj,visited));

    }

    private static boolean detectCycleUsingDfs(int[][] adj,boolean visited[]){
        for(int i=0;i<adj.length;i++){
            if(visited[i]==false && dfs(i,i,adj,visited)){
                return true;
            }
        }
        return false;
    }

    private static boolean dfs(int node,int parent,int[][]adj,boolean visited[]){
        visited[node]=true;
        for(int nbNode:adj[node]){
            if(visited[nbNode]==false){
//                System.out.println(" < child="+nbNode+" , parent ="+node+" > "+Arrays.toString(adj[node]));
                /*
                below code skip the false branch visited and allow to access other child of node.
                 */
                if(dfs(nbNode,node,adj,visited)){
//                    System.out.println("in if < child="+nbNode+" , parent ="+node+" > "+Arrays.toString(adj[node]));
                    return true;
                }
                /* why below code is not work
                here when reach to leaf node it will return false and due to return statement inside loop
                execution for other child is not run and due to this other is not marked visited
                and when start recursion using same node, encounter other visited node where
                below parent != nbNode become true which is wrong
                Example
                28 -> 90,310,400
                90 -> 28,475,30

                28 -> 90 -> 475 -> false && visited(28,90,475)
                30 -> 90 -> parent(30) and nbNode(90) return true
                 */
//                boolean is = dfs(nbNode,node,adj,visited);
//                System.out.println(visited[nbNode]+" "+is+" < child="+nbNode+" , parent ="+node+" > "+Arrays.toString(adj[node]));
//                return is;
            }else if(parent != nbNode){
                System.out.println(Arrays.toString(adj[node]));
                System.out.println(parent +" "+nbNode+" < "+node);
                return true;
            }
        }
        return false;
    }


    private static boolean detectCycleUsingBfs(int[][] adj,boolean visited[]){
        for(int i=0;i<adj.length;i++){
            System.out.println(i);
            if(visited[i]==false && bfs(i,i,adj,visited)){
                return true;
            }
        }
        return false;
    }

    private static boolean bfs(int node,int parent,int[][]adj,boolean visited[]){
        int parentNode[]=new int[adj.length+1];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{node,node});
        parentNode[node]=node;
        visited[node]=true;

        while(!queue.isEmpty()){
            int visit[] = queue.remove();
            node = visit[0];
            parent = visit[1];
            parent = parentNode[node];
            for(int nbNode:adj[node]){
                if(visited[nbNode]==false){
                    parentNode[nbNode] = node;
                    visited[nbNode] = true;
                    queue.add(new int[]{nbNode,node});
                }else if(parent != nbNode){
                    return true;
                }
            }
        }
        return false;
    }
}
