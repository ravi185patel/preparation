package career.datastructure.graph.template;

public class DisJoinUnion {
        int parent[];
        public int size[];
        int rank[];

        public DisJoinUnion(int cap){
            parent = new int[cap];
            size = new int[cap];
            rank = new int[cap];
            for(int i=0;i<cap;i++){ // based on problem i = 1 i<=cap
                parent[i]=i;
                size[i]=1;
                rank[i]=1;
            }
        }

        public int find(int x){
            if(x == parent[x]){
                return x;
            }
            return parent[x] = find(parent[x]);
        }

        public void union(int x,int y){
            int xParent = find(x);
            int yParent = find(y);
            if(xParent != yParent){
                parent[y]=x;
            }
            //etc code will be added based on problem condition
        }

        public void unionByRank(int x,int y){
            int xParent = find(x);
            int yParent = find(y);
            if(xParent == yParent){
                return;
            }
            if(rank[xParent] >= rank[yParent]){
                // why assign higher rank parent to lower rank parent:- to reduce height of tree as much as possible.
                rank[xParent] += rank[yParent];
                parent[yParent]=xParent;
            }else{
                rank[yParent] += rank[xParent];
                parent[xParent]=yParent;
            }
            //etc code will be added based on problem condition
        }

        public void unionBySize(int x,int y){
            int xParent = find(x);
            int yParent = find(y);
            if(xParent == yParent){
                return;
            }
            if(size[xParent] >= size[yParent]){
                // why assign higher rank parent to lower rank parent:- to reduce height of tree as much as possible.
                size[xParent] += size[yParent];
                parent[yParent]=xParent;
            }else{
                size[yParent] += size[xParent];
                parent[xParent]=yParent;
            }
            //etc code will be added based on problem condition
        }
}
