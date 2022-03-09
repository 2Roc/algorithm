package class30;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/symmetric-tree
public class Problem_0101_SymmetricTree {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	public boolean isSymmetric(TreeNode root) {
		return isMirror(root, root);
	}

	// 一棵树是原始树  head1
	// 另一棵是翻面树  head2
	public static boolean isMirror(TreeNode head1, TreeNode head2) {
		if (head1 == null && head2 == null) {
			return true;
		}
		if (head1 != null && head2 != null) {
			return head1.val == head2.val 
					&& isMirror(head1.left, head2.right) 
					&& isMirror(head1.right, head2.left);
		}
		// 一个为空，一个不为空  false
		return false;
	}

	public  boolean isSymmetricUnRecure(TreeNode root){
		if(root==null)return true;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root.left);
		queue.offer(root.right);
		while(!queue.isEmpty()){
			TreeNode cur1 = queue.poll();
			TreeNode cur2 = queue.poll();
			if(cur1 == null && cur2 ==null)continue;
			if(cur1==null || cur2==null) return false;
			if(cur1.val != cur2.val) return false;
			queue.offer(cur1.left);
			queue.offer(cur2.right);
			queue.offer(cur1.right);
			queue.offer(cur2.left);
		}
		return true;
	}

}
