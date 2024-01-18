package career.interview.atlassian;

import com.sun.source.tree.Tree;

import java.util.*;

class TreeNode{
    private String path;
    private List<TreeNode> child;
    private  int val;
    private boolean isLeaf;

    public TreeNode() {
        isLeaf = true;
        path = "";
        val=-1;
        child = new ArrayList<>();
    }

    public TreeNode(String path) {
        this.path = path;
        child = new ArrayList<>();
    }

    public TreeNode(String path, int val) {
        this.path = path;
        this.val = val;
        child = new ArrayList<>();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void addChild(TreeNode treeNode){
        this.child.add(treeNode);
    }

    public List<TreeNode> getChild() {
        return child;
    }

    public void setChild(List<TreeNode> child) {
        this.child = child;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return Objects.equals(path, treeNode.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}

class FileSystem {
    private TreeNode root;

    public FileSystem() {
        this.root = new TreeNode("parent", -1);
    }

    private TreeNode isPathExists(String fullPath) {
        String paths[] = fullPath.split("/");
        TreeNode temp = root, res = null;
        int ind = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(temp);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            res = treeNode;

            if(paths.length > ind) {
                for (TreeNode treeNode1 : treeNode.getChild()) {
                    if (treeNode1.getPath().equalsIgnoreCase(paths[ind])) {
                        queue.add(treeNode1);
                    }
                }
            }
            ind++;
        }
        return res;
    }

    boolean createPath(String fullPath, int value) {
        TreeNode treeNode = isPathExists(fullPath);
        String paths[] = fullPath.split("/");
        boolean isDone=false;
        if(paths.length == 1){
            treeNode.addChild(new TreeNode(paths[0],value));
            isDone=true;
        }else{
            int i = 0;
            for (; i < paths.length; i++) {
                if (paths[i].equalsIgnoreCase(treeNode.getPath())) {
                    isDone=true;
                    break;
                }
            }
            i++;
            for (; i < paths.length; i++) {
                TreeNode treeNode1 = new TreeNode(paths[i],value);
                treeNode.addChild(treeNode1);
                treeNode = treeNode1;
            }
        }
        return isDone;
    }

    int getPath(String fullPath) {
        TreeNode treeNode = isPathExists(fullPath);
        if (treeNode == null) {
            return -1;
        } else {
            return treeNode.getVal();
        }
    }

}
/*
Assume path given step by step
example
1] /leetcode , /leetcode/problems
2] /c/d -> path not exists return false (/c not exists);

 */


public class DesignFileSystemDemo {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        boolean res=fileSystem.createPath("a",1);
        System.out.println(res);
        res=fileSystem.createPath("a/b",2);
        System.out.println(res);
        res=fileSystem.createPath("c/d",2);
        System.out.println(res);

        System.out.println(fileSystem.getPath("a"));
        System.out.println(fileSystem.getPath("a/b"));
        System.out.println(fileSystem.getPath("c/d"));

    }
}

