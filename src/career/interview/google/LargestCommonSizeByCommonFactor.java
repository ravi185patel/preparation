package career.interview.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class DisjointSet {

    List<Integer> parent = new ArrayList<>();
    List<Integer> size = new ArrayList<>();
    public DisjointSet(int n) {
        for (int i = 0; i <= n; i++) {
            parent.add(i);
            size.add(1);
        }
    }

    public int findUPar(int node) {
        if (node == parent.get(node)) {
            return node;
        }
        int ulp = findUPar(parent.get(node));
        parent.set(node, ulp);
        return parent.get(node);
    }

    public void unionBySize(int u, int v) {

        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if (ulp_u == ulp_v) return;
        if (size.get(ulp_u) < size.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
        } else {
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
        }
    }


}
public class LargestCommonSizeByCommonFactor {
    public static void main(String[] args) {
            int nums [] =
//                    {2,3,6,7,4,12,21,39};
//                    {20,50,9,63};  //ans = 2
                    {4,6,15,35}; // ans = 3
            int res =largestComponentSize(nums);
            System.out.println(res);
    }
    public static int largestComponentSize(int[] nums) {
        int max=0;
        for (int i : nums) max = Math.max(max, i);
        DisjointSet obj = new DisjointSet(max + 1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (gcd(nums[i], nums[j]) > 1) {
                    obj.unionBySize(nums[i], nums[j]);
                }
            }
        }
//        System.out.println(obj.size);
        Optional<Integer> maxOp = obj.size.stream().max(Integer::compareTo);
        return maxOp.isPresent() ? maxOp.get():-1;
    }



    public static int gcd(int i, int j) {
        if (j == 0) return i;
        return gcd(j, i % j);
    }
}