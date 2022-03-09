package class12;

import util.GenerateRandomBST;
import util.GenerateRandomBST.TreeNode;

public class Code04_IsFull {
	// 第一种方法
	// 收集整棵树的高度h，和节点数n
	// 只有满二叉树满足 : 2 ^ h - 1 == n
	public static boolean isFull1(TreeNode head) {
		if (head == null) {
			return true;
		}
		Info1 all = process1(head);
		return (1 << all.height) - 1 == all.nodes;
	}

	public static class Info1 {
		public int height;
		public int nodes;

		public Info1(int h, int n) {
			height = h;
			nodes = n;
		}
	}

	public static Info1 process1(TreeNode head) {
		if (head == null) {
			return new Info1(0, 0);
		}
		Info1 leftInfo = process1(head.left);
		Info1 rightInfo = process1(head.right);
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		int nodes = leftInfo.nodes + rightInfo.nodes + 1;
		return new Info1(height, nodes);
	}

	// 第二种方法
	// 收集子树是否是满二叉树
	// 收集子树的高度
	// 左树满 && 右树满 && 左右树高度一样 -> 整棵树是满的
	public static boolean isFull2(TreeNode head) {
		if (head == null) {
			return true;
		}
		return process2(head).isFull;
	}

	public static class Info2 {
		public boolean isFull;
		public int height;

		public Info2(boolean f, int h) {
			isFull = f;
			height = h;
		}
	}

	public static Info2 process2(TreeNode h) {
		if (h == null) {
			return new Info2(true, 0);
		}
		Info2 leftInfo = process2(h.left);
		Info2 rightInfo = process2(h.right);
		boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		return new Info2(isFull, height);
	}

	public static void main(String[] args) {
		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000000;
		System.out.println("测试开始");
		for (int i = 0; i < testTimes; i++) {
			TreeNode head = GenerateRandomBST.generateRandomBST(maxLevel, maxValue);
			if (isFull1(head) != isFull2(head)) {
				System.out.println("出错了!");
			}
		}
		System.out.println("测试结束");
	}
}
