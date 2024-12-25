package career.datastructure.graph.component.land;

import java.util.ArrayList;
import java.util.List;

class UnionFind {
    int[] parent;
    int[] rank;
    int count;

    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++)
            parent[i] = -1;
        count = 0;
    }

    public void addLand(int x) {
        if (parent[x] >= 0)
            return;
        parent[x] = x;
        count++;
    }

    public boolean isLand(int x) {
        if (parent[x] >= 0) {
            return true;
        } else {
            return false;
        }
    }

    int numberOfIslands() {
        return count;
    }

    public int find(int x) {
        if(x == parent[x]){
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    public void union(int x, int y) {
        int xset = find(x), yset = find(y);
        if (xset == yset) {
            return;
        } else if (rank[xset] < rank[yset]) {
            parent[xset] = yset;
        } else if (rank[xset] > rank[yset]) {
            parent[yset] = xset;
        } else {
            parent[yset] = xset;
            rank[xset]++;
        }
        count--;
    }
}

public class NumberOfIslandII {
    public static void main(String[] args) {
        int m = 3, n = 3, positions[][] = {{0,0}, {0,1}, {1,2}, {2,1}};
        List<Integer> res = numIslands2(m,n,positions);
        System.out.println(res);
    }

    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        int x[] = { -1, 1, 0, 0 };
        int y[] = { 0, 0, -1, 1 };
        UnionFind dsu = new UnionFind(m * n);
        List<Integer> answer = new ArrayList<>();

        for (int[] position : positions) {
            int landPosition = position[0] * n + position[1];
            dsu.addLand(landPosition);

            for (int i = 0; i < 4; i++) {
                int neighborX = position[0] + x[i];
                int neighborY = position[1] + y[i];
                int neighborPosition = neighborX * n + neighborY;
                // If neighborX and neighborY correspond to a point in the grid and there is a
                // land at that point, then merge it with the current land.
                if (neighborX >= 0 && neighborX < m && neighborY >= 0 && neighborY < n &&
                        dsu.isLand(neighborPosition)) {
                    dsu.union(landPosition, neighborPosition);
                }
            }
            answer.add(dsu.numberOfIslands());
        }
        return answer;
    }

    private static int findDistance(int p1[],int p2[]){
        return Math.abs(p1[0]-p2[0])+Math.abs(p1[1]-p2[1]);
    }
}
