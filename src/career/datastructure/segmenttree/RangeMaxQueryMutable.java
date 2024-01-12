package career.datastructure.segmenttree;

import java.util.Arrays;

class RangeMax {

    int[] tree;
    int[] nums;

    public RangeMax(int[] nums) {
        this.nums = nums;
        this.tree = new int[4 * nums.length];
        buildMaxQueryTree(1, 0, nums.length - 1);
    }

    public void buildMaxQueryTree(int node, int lo, int hi) {
        if(lo == hi) {
            tree[node] = nums[lo];
            return;
        }
        int leftNode = 2 * node;
        int rightNode = 2 * node + 1;
        int mid = lo + (hi - lo) / 2;

        buildMaxQueryTree(leftNode, lo, mid);
        buildMaxQueryTree(rightNode, mid + 1, hi);

        tree[node] = Math.max(tree[leftNode], tree[rightNode]);
    }

    public void update(int index, int val) {
        update(1, 0, nums.length - 1, index, val);
    }

    private void update(int node, int lo, int hi, int index, int val) {
        if(lo == hi) {
            nums[lo] = val;
            tree[node] = val;
            return;
        }

        int leftNode = 2 * node;
        int rightNode = 2 * node + 1;
        int mid = lo + (hi - lo) / 2;

        if(index <= mid)
            update(leftNode, lo, mid, index, val);
        else
            update(rightNode, mid + 1, hi, index, val);

        tree[node] = Math.max(tree[leftNode] , tree[rightNode]);

    }

    public int maxRange(int left, int right) {
        return maxRange(1, 0, nums.length - 1, left, right);
    }

    private int maxRange(int node, int lo, int hi, int ql, int qr) {
        if(hi < ql || lo > qr)
            return 0;
        if(lo == hi)
            return tree[node];
        if(lo >= ql && hi <= qr)
            return tree[node];

        int leftNode = 2 * node;
        int rightNode = 2 * node + 1;
        int mid = lo + (hi - lo) / 2;

        int leftMax = maxRange(leftNode, lo, mid, ql, qr);
        int rightMax = maxRange(rightNode, mid + 1, hi, ql, qr);

        return Math.max(leftMax , rightMax);
    }

}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */

public class RangeMaxQueryMutable {
    public static void main(String[] args) {

        RangeMax rangeMax=new RangeMax(new int[]{1, 3, 5});
        System.out.println(Arrays.toString(rangeMax.tree));
        int res=rangeMax.maxRange(0,2);
        System.out.println(res);
//        rangeMax.update(1,2);
        res=rangeMax.maxRange(0,2);
        System.out.println(res);

        res=rangeMax.maxRange(1,1);
        System.out.println(res);

    }
}
