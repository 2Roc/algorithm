package class12;

import util.GenerateRandomBST;
import util.GenerateRandomBST.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Code03_IsBalanced {

	public static boolean isBalanced1(TreeNode head) {
		boolean[] ans = new boolean[1];
		ans[0] = true;
		process1(head, ans);
		return ans[0];
	}

	public static int process1(TreeNode head, boolean[] ans) {
		if (!ans[0] || head == null) {
			return -1;
		}
		int leftHeight = process1(head.left, ans);
		int rightHeight = process1(head.right, ans);
		if (Math.abs(leftHeight - rightHeight) > 1) {
			ans[0] = false;
		}
		return Math.max(leftHeight, rightHeight) + 1;
	}

	public static boolean isBalanced2(TreeNode head) {
		return process(head).isBalanced;
	}
	
	public static class Info{
		public boolean isBalanced;
		public int height;
		
		public Info(boolean i, int h) {
			isBalanced = i;
			height = h;
		}
	}

	/**
	 *
	 * @param x
	 * @return
	 */
	public static Info process(TreeNode x) {
		if(x == null) {
			return new Info(true, 0);
		}
		Info leftInfo = process(x.left);
		Info rightInfo = process(x.right);
		int height = Math.max(leftInfo.height, rightInfo.height)  + 1;
		boolean isBalanced = leftInfo.isBalanced;
		if(!rightInfo.isBalanced) {
			isBalanced = false;
		}
		if(Math.abs(leftInfo.height - rightInfo.height) > 1) {
			isBalanced = false;
		}
		return new Info(isBalanced, height);
	}

	public boolean isBalance(TreeNode root){
		return depth(root) != -1;
	}
	private int depth(TreeNode root){
		if(root == null)return 0;
		int left = depth(root.left);
		if(left==-1)return -1;
		int right = depth(root.right);
		if(right==-1)return -1;
		return Math.abs(left-right) > 1? -1:Math.max(left,right)+1;
	}


	/**
	 * 使用了后序遍历的迭代写法
	 * 加入了一个hashmap记录已经访问过的结点的高度方便提供查找
	 * 从而实现了从叶节点最后到根的由底层到根的遍历
	 * @param root
	 * @return
	 */
	public boolean isBalanced(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		TreeNode lastNode = null;
		TreeNode nowNode = root;
		Map<TreeNode, Integer> map = new HashMap<>();
		while(nowNode != null || !stack.isEmpty()){
			if(nowNode != null){
				stack.push(nowNode);
				nowNode = nowNode.left;
			}else{
				nowNode = stack.peek();
				if(nowNode.right == null || nowNode.right == lastNode){

					int lNum = 0;
					int rNum = 0;
					if(nowNode.left != null) lNum = map.get(nowNode.left);
					if(nowNode.right != null) rNum = map.get(nowNode.right);
					if(Math.abs(lNum - rNum) > 1) return false;
					map.put(nowNode, Math.max(lNum, rNum) + 1);

					lastNode = stack.pop();
					nowNode = null;
				}else{
					nowNode = nowNode.right;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			TreeNode head = GenerateRandomBST.generateRandomBST(maxLevel, maxValue);
			if (isBalanced1(head) != isBalanced2(head)) {
				System.out.println("Error!");
			}
		}
		System.out.println("finish!");
	}
}
