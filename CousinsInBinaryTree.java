/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// Time Complexity : O(N)
// Space Complexity : O(2N) - using 2 queues
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
class Solution {

    public boolean isCousins(TreeNode root, int x, int y) {

        if(root == null) return false;

        Queue<TreeNode> nodeQ = new LinkedList<>();
        Queue<TreeNode> parentQ = new LinkedList<>();

        nodeQ.add(root);
        parentQ.add(null);

        while(!nodeQ.isEmpty()){
            int size = nodeQ.size();

            boolean x_found = false;
            boolean y_found  = false;
            TreeNode x_parent = null;
            TreeNode y_parent = null;
            for(int i=0;i<size;i++) {
                TreeNode curr = nodeQ.poll();
                TreeNode parent = parentQ.poll();
                if(curr.val == x){
                    x_found = true;
                    x_parent = parent;
                }

                if(curr.val == y){
                    y_found = true;
                    y_parent = parent;
                }

                if( x_found && y_found && (x_parent != y_parent )) {
                    return true;
                }else{
                    if(curr.left != null) {
                        nodeQ.add(curr.left);
                        parentQ.add(curr);
                    }

                    if(curr.right != null) {
                        nodeQ.add(curr.right);
                        parentQ.add(curr);
                    }
                }

            }
        }
        return false;
    }


}

// Time Complexity : O(N)
// Space Complexity : O(N) - using 1 queues
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
class Solution {

    public boolean isCousins(TreeNode root, int x, int y) {

        if(root == null) return false;

        Queue<TreeNode> nodeQ = new LinkedList<>();

        nodeQ.add(root);

        while(!nodeQ.isEmpty()){
            int size = nodeQ.size();

            boolean x_found = false;
            boolean y_found  = false;

            for(int i=0;i<size;i++) {
                TreeNode curr = nodeQ.poll();
                if(curr.val == x){
                    x_found = true;
                }

                if(curr.val == y){
                    y_found = true;
                }

                if(curr.left != null && curr.right != null) {
                    if(curr.left.val == x && curr.right.val ==y){
                        return false;
                    }
                    if(curr.right.val == x && curr.left.val ==y){
                        return false;
                    }
                }

                if(curr.left  != null){
                    nodeQ.add(curr.left);
                }

                if(curr.right  != null){
                    nodeQ.add(curr.right);
                }

            }
            if(x_found && y_found) return true;
        }
        return false;
    }


}


// DFS approach using recurssion
class Solution {

    TreeNode x_parent, y_parent;
    int x_height, y_height;

    public boolean isCousins(TreeNode root, int x, int y) {
        this.x_parent = null;
        this.y_parent = null;
        this.x_height =-1;
        this.y_height=-1;

        helper(root,x,y,0,null);

        if(x_height == y_height && (x_parent != y_parent)) {
            return true;
        }
        return false;
    }

    private void helper(TreeNode root, int x, int y, int level, TreeNode parent) {
        //base case
        if(root == null) return;

        if(root.val == x) {
            x_parent = parent;
            x_height = level;
        }

        if(root.val == y) {
            y_parent = parent;
            y_height = level;
        }

        helper(root.left, x,y, level+1, root);
        helper(root.right,x,y,level+1, root);
    }
}