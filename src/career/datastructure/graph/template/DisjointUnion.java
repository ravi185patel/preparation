package career.datastructure.graph.template;

import java.util.Arrays;

public class DisjointUnion {
    public static void main(String[] args) {

        int set[]={1,2,3,4,5,6,7};
        DisJoinUnion ds = new DisJoinUnion(set.length+1);

        for(int i=0;i<set.length;i++){
            for(int j=0;j<set.length;j++){
                // it will same for all problems just need to be sure what asked and what will be input
                //
                if(ds.find(set[i]) != ds.find(set[j])){
                    ds.union(set[i],set[j]);
                }
            }
        }
        System.out.println(Arrays.toString(ds.parent));
        System.out.println(Arrays.toString(ds.rank));
        System.out.println(Arrays.toString(ds.size));

        System.out.println("union by rank");
        ds = new DisJoinUnion(set.length+1);

        for(int i=0;i<set.length;i++){
            for(int j=0;j<set.length;j++){
                if(ds.find(set[i]) != ds.find(set[j])){
                    ds.unionByRank(set[i],set[j]);
                }
            }
        }
        System.out.println(Arrays.toString(ds.parent));
        System.out.println(Arrays.toString(ds.rank));
        System.out.println(Arrays.toString(ds.size));

        System.out.println("union by size");
        ds = new DisJoinUnion(set.length+1);

        for(int i=0;i<set.length;i++){
            for(int j=0;j<set.length;j++){
                if(ds.find(set[i]) != ds.find(set[j])){
                    ds.unionBySize(set[i],set[j]);
                }
            }
        }
        System.out.println(Arrays.toString(ds.parent));
        System.out.println(Arrays.toString(ds.rank));
        System.out.println(Arrays.toString(ds.size));
    }
}
