package class30;

public class Code01_MorrisTraversal {

	public static class Node {
		public int value;
		Node left;
		Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	//输的递归顺序的结果的结果的结果的，
	public static void process(Node root) {
		if (root == null) {
			return;
		}
		// 1
		process(root.left);
		// 2
		process(root.right);
		// 3
	}

	public static void morris(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
			//343434344566有数的有的话又饿的有数据有序的 但有的护额 7778677
				//有左子树
				//544354676789988990899889887
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				//mostRight变成了cur的左子树上，最右的节点，这是第一次来到cur节点，

				if (mostRight.right == null) {
					mostRight.right = cur;//这是第一次的来到的most的节点；
					//这是第一次的节点的所有的节点，必看刘哦子树的事务哦那个时代家
					cur = cur.left;
					continue;
				} else {
					//mostRight。right == null
					mostRight.right = null;
				}
			}
			cur = cur.right;
		}
	}

	public static void morrisPre(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					System.out.print(cur.value + " ");
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			} else {
				//没有左子树的情况
				System.out.print(cur.value + " ");
			}
			cur = cur.right;
		}
		System.out.println();
	}

	public static void morrisIn(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {//开始6555665554454
			//mostRight是cur的左孩子
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			}
			System.out.print(cur.value + " ");
			cur = cur.right;
		}
		System.out.println();
	}

	public static void morrisPos(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
					printEdge(cur.left);
				}
			}
			cur = cur.right;
		}
		printEdge(head);
		System.out.println();
	}

	public static void printEdge(Node head) {
		Node tail = reverseEdge(head);
		Node cur = tail;
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.right;
		}
		reverseEdge(tail);
	}

	public static Node reverseEdge(Node from) {
		Node pre = null;
		Node next = null;
		while (from != null) {
			next = from.right;
			from.right = pre;
			pre = from;
			from = next;
		}
		return pre;
	}

	// for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static boolean isBST(Node head) {
		if (head == null) {
			return true;
		}
		Node cur = head;
		Node mostRight = null;
		Integer pre = null;
		boolean ans = true;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			}
			if (pre != null && pre >= cur.value) {
				ans = false;
			}
			pre = cur.value;
			cur = cur.right;
		}
		return ans;
	}

	public static void main(String[] args) {
		Node head = new Node(4);
		head.left = new Node(2);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(5);
		head.right.right = new Node(7);
		printTree(head);
		morrisIn(head);
		morrisPre(head);
		morrisPos(head);
		printTree(head);

	}

}
