package class10;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 非递归遍历树
 */
public class Code03_UnRecursiveTraversalBT {

	public static class TreeNode {
		public int value;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int v) {
			value = v;
		}
	}

	/**
	 * 1）从栈中弹出一个节点，打印cur
	 * 2）先压右再压左（如果有）
	 * 3）周而复始
	 * @param head
	 */
	public static void pre(TreeNode head) {
		System.out.print("pre-order: ");
		if (head != null) {//这个条件不能少，否则空指针
			Stack<TreeNode> stack = new Stack<>();
			stack.add(head);
			while (!stack.isEmpty()) {
				head = stack.pop();
				System.out.print(head.value + " ");
				if (head.right != null) {
					stack.push(head.right);
				}
				if (head.left != null) {
					stack.push(head.left);
				}
			}
		}
		System.out.println();
	}

	public static void in(TreeNode cur) {
		System.out.print("in-order: ");
		if (cur != null) {
			Stack<TreeNode> stack = new Stack<>();
			while (!stack.isEmpty() || cur != null) {
				if (cur != null) {
					//先把所有左节点入栈
					stack.push(cur);
					cur = cur.left;
				} else {
					cur = stack.pop();
					System.out.print(cur.value + " ");
					cur = cur.right;
				}
			}
		}
		System.out.println();
	}

	public static void pos1(TreeNode head) {
		System.out.print("pos-order: ");
		if (head != null) {
			Stack<TreeNode> s1 = new Stack<>();
			Stack<TreeNode> s2 = new Stack<>();
			s1.push(head);
			while (!s1.isEmpty()) {
				head = s1.pop(); // 头 右 左
				s2.push(head);
				if (head.left != null) {
					s1.push(head.left);
				}
				if (head.right != null) {
					s1.push(head.right);
				}
			}
			// 左 右 头
			while (!s2.isEmpty()) {
				System.out.print(s2.pop().value + " ");
			}
		}
		System.out.println();
	}

	public static void pos2(TreeNode root) {
		System.out.print("pos-order: ");
		if (root != null) {
			Stack<TreeNode> stack = new Stack<>();
			stack.push(root);
			TreeNode cur;
			while (!stack.isEmpty()) {
				cur = stack.peek();
				if (cur.left != null && root != cur.left && root != cur.right) {
					stack.push(cur.left);
				} else if (cur.right != null && root != cur.right) {
					stack.push(cur.right);
				} else {
					System.out.print(stack.pop().value + " ");
					root = cur;
				}
			}
		}
		System.out.println();
	}
	public int[] postorderTraversal (TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		ArrayList<Integer> list = new ArrayList<>();
		if(root!=null) stack.add(root);
		while(!stack.isEmpty()){
			TreeNode node = stack.pop();
			list.add(node.value);
			if(node.left!=null)stack.push(node.left);
			if(node.right!=null)stack.push(node.right);
		}
		int size = list.size();
		int[] resu = new int[size];
		for(int n=size-1;n>=0;n--){
			resu[size-1-n] = list.get(n);
		}
		return resu;
	}
	
	public static void main(String[] args) {
		//构造一个复杂的树
		TreeNode head = new TreeNode(5);
		head.left = new TreeNode(3);
		head.right = new TreeNode(8);
		head.left.left = new TreeNode(2);
		head.left.right = new TreeNode(4);
		head.left.left.left = new TreeNode(1);
		head.right.left = new TreeNode(7);
		head.right.left.left = new TreeNode(6);
		head.right.right = new TreeNode(10);
		head.right.right.left = new TreeNode(9);
		head.right.right.right = new TreeNode(11);

		//TreeNode head = new TreeNode(1);
		//head.left = new TreeNode(2);
		//head.right = new TreeNode(3);
		//head.left.left = new TreeNode(4);
		//head.left.right = new TreeNode(5);
		//head.right.left = new TreeNode(6);
		//head.right.right = new TreeNode(7);

		pre(head);
		System.out.println("========");
		in(head);
		System.out.println("========");
		pos1(head);
		System.out.println("========");
		pos2(head);
		System.out.println("========");
	}
}
