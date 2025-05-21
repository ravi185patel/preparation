package career.datastructure.swapline;

/*
TreeMap: Simpler, good for small input sizes (LeetCode constraints).

Segment Tree: Essential for large-scale systems (e.g., booking systems with millions of events).
 */
public class MyCalenderSegmentTree {
        private static class SegmentTreeNode {
            int start, end;
            SegmentTreeNode left, right;
            int max;
            int lazy;

            public SegmentTreeNode(int start, int end) {
                this.start = start;
                this.end = end;
                this.left = null;
                this.right = null;
                this.max = 0;
                this.lazy = 0;
            }
        }

        private SegmentTreeNode root;
        private final int MAX_RANGE = (int) 1e9;

        public MyCalenderSegmentTree() {
            root = new SegmentTreeNode(0, MAX_RANGE);
        }

        public int book(int start, int end) {
            update(root, start, end - 1, 1); // Interval [start, end)
            return root.max; // Root's max holds the global maximum
        }

        private void update(SegmentTreeNode node, int l, int r, int val) {
            if (node.end < l || node.start > r) return; // No overlap

            // Current node is fully covered
            if (l <= node.start && node.end <= r) {
                node.max += val;
                node.lazy += val;
                return;
            }

            pushDown(node); // Propagate lazy updates

            update(node.left, l, r, val);
            update(node.right, l, r, val);

            node.max = Math.max(node.left.max, node.right.max);
        }

        private void pushDown(SegmentTreeNode node) {
            if (node.start == node.end) return; // Leaf node

            int mid = node.start + (node.end - node.start) / 2;

            // Initialize children if null
            if (node.left == null) {
                node.left = new SegmentTreeNode(node.start, mid);
            }
            if (node.right == null) {
                node.right = new SegmentTreeNode(mid + 1, node.end);
            }

            if (node.lazy > 0) {
                node.left.max += node.lazy;
                node.left.lazy += node.lazy;
                node.right.max += node.lazy;
                node.right.lazy += node.lazy;
                node.lazy = 0;
            }
        }
}
