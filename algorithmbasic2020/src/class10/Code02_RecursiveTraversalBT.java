package class10;

/**
 * 递归遍历二叉树
 */
public class Code02_RecursiveTraversalBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	public static void find(Node head) {
		if (head == null) {
			return;
		}
		// 先序输出
//		System.out.println(head);
		find(head.left);
		// 中序遍历
		find(head.right);
		// 后序遍历
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		find(head);
		System.out.println("========");
	}
}
