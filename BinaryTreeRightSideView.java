// Time Complexity : O(H)
// Space Complexity : O(W) - width of the tree
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

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
// BFS approach
class Solution {
    List<Integer> result;
    public List<Integer> rightSideView(TreeNode root) {
        this.result = new ArrayList<>();

        if(root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()) {
            int size = q.size();

            for(int i=0;i< size;i++) {
                TreeNode curr = q.poll();
                if(i == size-1) {
                    result.add(curr.val);
                }


                if(curr.left != null) {
                    q.add(curr.left);
                }

                if(curr.right != null) {
                    q.add(curr.right);
                }

            }

        }

        return result;
    }


}


// DFS approach using recurssion
class Solution {
    List<Integer> result;
    public List<Integer> rightSideView(TreeNode root) {
        this.result = new ArrayList<>();
        helper(root,0);
        return result;
    }

    private void helper(TreeNode root, int level) {
        if(root == null)  return;

        //logic
        if(level == result.size()) {
            result.add(root.val);
        }
        helper(root.right, level+1);
        helper(root.left, level+1);
    }

}