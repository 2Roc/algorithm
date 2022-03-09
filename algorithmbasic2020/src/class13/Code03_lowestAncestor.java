package class13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 最低公共祖先
 */
public class Code03_lowestAncestor {

	public static class TreeNode {
		public int value;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int data) {
			this.value = data;
		}
	}

	public static TreeNode lowestAncestor1(TreeNode head, TreeNode o1, TreeNode o2) {
		if (head == null) {
			return null;
		}
		// key的父节点是value
		HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
		parentMap.put(head, null);
		fillParentMap(head, parentMap);
		HashSet<TreeNode> o1Set = new HashSet<>();
		TreeNode cur = o1;
		o1Set.add(cur);
		while (parentMap.get(cur) != null) {
			cur = parentMap.get(cur);
			o1Set.add(cur);
		}
		cur = o2;
		while (!o1Set.contains(cur)) {
			cur = parentMap.get(cur);
		}
		return cur;
	}

	public static void fillParentMap(TreeNode head, HashMap<TreeNode, TreeNode> parentMap) {
		if (head.left != null) {
			parentMap.put(head.left, head);
			fillParentMap(head.left, parentMap);
		}
		if (head.right != null) {
			parentMap.put(head.right, head);
			fillParentMap(head.right, parentMap);
		}
	}

	//树形DP的模版
	public static TreeNode lowestAncestor2(TreeNode head, TreeNode a, TreeNode b) {
		return process(head, a, b).ans;
	}

	public static class Info {
		public boolean findA;
		public boolean findB;
		public TreeNode ans;

		public Info(boolean fA, boolean fB, TreeNode an) {
			findA = fA;
			findB = fB;
			ans = an;
		}
	}

	public static Info process(TreeNode x, TreeNode a, TreeNode b) {
		if (x == null) {
			return new Info(false, false, null);
		}
		Info leftInfo = process(x.left, a, b);
		Info rightInfo = process(x.right, a, b);
		boolean findA = (x == a) || leftInfo.findA || rightInfo.findA;
		boolean findB = (x == b) || leftInfo.findB || rightInfo.findB;
		TreeNode ans = null;
		if (leftInfo.ans != null) {
			ans = leftInfo.ans;
		} else if (rightInfo.ans != null) {
			ans = rightInfo.ans;
		} else {
			if (findA && findB) {
				ans = x;
			}
		}
		return new Info(findA, findB, ans);
	}

	public int lowestCommonAncestor1 (TreeNode root, int o1, int o2) {
		return CommonAncestor(root, o1, o2).value;
	}

	public TreeNode CommonAncestor (TreeNode root, int o1, int o2) {
		if (root == null || root.value == o1 || root.value == o2) { // 如果root为空，或者root为o1、o2中的一个，则它们的最近公共祖先就为root
			return root;
		}
		TreeNode left = CommonAncestor(root.left,o1,o2);    // 递归遍历左子树，只要在左子树中找到了o1或o2，则先找到谁就返回谁
		TreeNode right = CommonAncestor(root.right,o1,o2);  // 递归遍历右子树，只要在右子树中找到了o1或o2，则先找到谁就返回谁
		if (left == null) {  // 如果在左子树中o1和o2都找不到，则o1和o2一定都在右子树中，右子树中先遍历到的那个就是最近公共祖先（一个节点也可以是它自己的祖先）
			return right;
		}else if (right == null) { // 否则，如果left不为空，在左子树中有找到节点（o1或o2），这时候要再判断一下右子树中的情况，
			// 如果在右子树中，o1和o2都找不到，则 o1和o2一定都在左子树中，左子树中先遍历到的那个就是最近公共祖先（一个节点也可以是它自己的祖先）
			return left;
		}else{
			return root; // 否则，当 left和 right均不为空时，说明 o1、o2节点分别在 root异侧, 最近公共祖先即为 root
		}
	}

	//二叉搜索树（中序遍历递增）的最低公共祖先,利用中序遍历的递增特性——》进行加速的特性
	//时间复杂度 O（logN）空间复杂度 O（1）
	public int lowestCommonAncestor(TreeNode root,int p,int q){
		TreeNode cur = root;
		while (true){
			if(p > cur.value && q > cur.value)
				cur = cur.right;
			else if(p < cur.value && q < cur.value)
				cur = cur.left;
			else
				return cur.value;
		}
	}

	// for test
	public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// for test
	public static TreeNode generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		TreeNode head = new TreeNode((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}

	// for test
	public static TreeNode pickRandomOne(TreeNode head) {
		if (head == null) {
			return null;
		}
		ArrayList<TreeNode> arr = new ArrayList<>();
		fillPrelist(head, arr);
		int randomIndex = (int) (Math.random() * arr.size());
		return arr.get(randomIndex);
	}

	// for test
	public static void fillPrelist(TreeNode head, ArrayList<TreeNode> arr) {
		if (head == null) {
			return;
		}
		arr.add(head);
		fillPrelist(head.left, arr);
		fillPrelist(head.right, arr);
	}

	public static void main(String[] args) {
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			TreeNode head = generateRandomBST(maxLevel, maxValue);
			TreeNode o1 = pickRandomOne(head);
			TreeNode o2 = pickRandomOne(head);
			if (lowestAncestor1(head, o1, o2) != lowestAncestor2(head, o1, o2)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}
}
