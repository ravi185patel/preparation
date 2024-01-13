package career.datastructure.segmenttree;

import java.util.Arrays;

class NumArrayLazy1 {

    int[] tree;
    int[] nums;

    int[] lazy;

    public NumArrayLazy1(int[] nums) {
        this.nums = nums;
        this.tree = new int[4 * nums.length];
        this.lazy = new int[4 * nums.length];
        buildSumQueryTree(1, 0, nums.length - 1);
    }

    private void propagate(int node,int lo,int hi){
        if(lo == hi){
            tree[node] +=lazy[node];
        }else{
            tree[node] += ((hi-lo + 1) * lazy[node]); // update segment
            lazy[node*2] += lazy[node]; // propagate parent to child -> set child = child + parent and parent=0;
            lazy[node*2+1] += lazy[node];
        }
        lazy[node] = 0;
    }

    public void buildSumQueryTree(int node, int lo, int hi) {
        if(lo == hi) {
            tree[node] = nums[lo];
            return;
        }
        int leftNode = 2 * node;
        int rightNode = 2 * node + 1;
        int mid = lo + (hi - lo) / 2;

        buildSumQueryTree(leftNode, lo, mid);
        buildSumQueryTree(rightNode, mid + 1, hi);

        tree[node] = tree[leftNode] + tree[rightNode];
    }

    public void update(int start,int end, int val) {
        update(1, 0, nums.length - 1, start,end, val);
    }
    private void update(int node, int lo, int hi, int l,int r, int val) {
        propagate(node,lo,hi);
        if(hi < l || r< lo) return;
        if(lo==hi){
            nums[lo]=val;
            tree[node]=val; // replace with value instead of add during replacement of val in given range.
        }else if(l <= lo && hi<=r){
            lazy[node]=val; // replace with value instead of add during replacement of val in given range.
            propagate(node,lo,hi);
        }else{
            int mid=(lo+hi)/2;
            update(node*2,lo,mid,l,r,val);
            update(node*2+1,mid+1,hi,l,r,val);

            tree[node]  = tree[node*2] + tree[node*2+1];
        }
    }

    public int sumRange(int left, int right) {
        return sumRange(1, 0, nums.length - 1, left, right);
    }

    private int sumRange(int node, int lo, int hi, int ql, int qr) {
        propagate(node,lo,hi);
        if(hi < ql || lo > qr)
            return 0;
        if(lo == hi)
            return tree[node];
        if(lo >= ql && hi <= qr)
            return tree[node];

        int leftNode = 2 * node;
        int rightNode = 2 * node + 1;
        int mid = lo + (hi - lo) / 2;

        int leftSum = sumRange(leftNode, lo, mid, ql, qr);
        int rightSum = sumRange(rightNode, mid + 1, hi, ql, qr);

        return leftSum + rightSum;
    }

}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */

public class RangeSumQueryLazyMutableReplaceValueInGivenRange {
    public static void main(String[] args) {
//["NumArray", "sumRange", "update", "sumRange"] -> output = [null, 9, null, 8]
//[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
        NumArrayLazy1 numArray=new NumArrayLazy1(new int[]{1, 3, 5});
        System.out.println(Arrays.toString(numArray.nums));
        System.out.println(Arrays.toString(numArray.tree));
        System.out.println(Arrays.toString(numArray.lazy));
        int res=numArray.sumRange(0,2);

        System.out.println(res);
        numArray.update(0,1,2);

        System.out.println(Arrays.toString(numArray.nums));
        System.out.println(Arrays.toString(numArray.tree));
        System.out.println(Arrays.toString(numArray.lazy));

        res=numArray.sumRange(0,2);
        System.out.println(res);

    }
}
