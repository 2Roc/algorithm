package class032;

/**
 * 任意两个结点路径长度中的最大值就是二叉树的直径长度
 * https://leetcode.com/problems/diameter-of-binary-tree
 */
public class Code04_DiameterOfBinaryTree {

	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	public static int diameterOfBinaryTree(TreeNode root) {
		return process(root).maxDistance;
	}

	public static class Info {
		public int maxDistance;
		public int height;

		public Info(int m, int h) {
			maxDistance = m;
			height = h;
		}
	}

	public static Info process(TreeNode x) {
		if (x == null) {
			return new Info(0, 0);
		}
		Info leftInfo = process(x.left);
		Info rightInfo = process(x.right);
		int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance),
				leftInfo.height + rightInfo.height);
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		return new Info(maxDistance, height);
	}


	int maxd = 0;
	public int diameterOfBinaryTree2(TreeNode root){
		process1(root);
		return maxd;
	}

	public int process1(TreeNode head){
		if(head==null)return 0;
		int leftHeight = process1(head.left);
		int rightHeight = process1(head.right);

		maxd = Math.max(leftHeight+rightHeight,maxd);
		//返回该节点为根的子树的深度
		return Math.max(leftHeight,rightHeight)+1;
	}

}
