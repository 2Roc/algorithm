package class30;

import java.util.LinkedList;
import java.util.Queue;
/**
 最终结果图，方便理解
 https://uploadfiles.nowcoder.com/images/20200819/59_1597813848148_8FAEFB4C7A0141A9BC46AC13FE3F8D99
 */
public class Problem_0116_PopulatingNextRightPointersInEachNode {

	// 不要提交这个类
	public static class TreeLinkNode {
		public int val;
		public TreeLinkNode left;
		public TreeLinkNode right;
		public TreeLinkNode next;
	}

	// 提交下面的代码
	public static TreeLinkNode connect(TreeLinkNode root) {
		if (root == null) {
			return root;
		}
		MyQueue queue = new MyQueue();
		queue.offer(root);
		while (!queue.isEmpty()) {
			// 第一个弹出的节点
			TreeLinkNode pre = null;
			int size = queue.size;
			for (int i = 0; i < size; i++) {
				TreeLinkNode cur = queue.poll();
				if (cur.left != null) {
					queue.offer(cur.left);
				}
				if (cur.right != null) {
					queue.offer(cur.right);
				}
				if (pre != null) {
					pre.next = cur;
				}
				pre = cur;
			}
		}
		return root;
	}

	public static class MyQueue {
		public TreeLinkNode head;
		public TreeLinkNode tail;
		public int size;

		public MyQueue() {
			head = null;
			tail = null;
			size = 0;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public void offer(TreeLinkNode cur) {
			size++;
			if (head == null) {
				head = cur;
				tail = cur;
			} else {
				tail.next = cur;
				tail = cur;
			}
		}

		public TreeLinkNode poll() {
			size--;
			TreeLinkNode ans = head;
			head = head.next;
			ans.next = null;
			return ans;
		}
	}

	//空间复杂度O(N)
	public void connect2(TreeLinkNode root) {
		if (root == null) return;
		Queue<TreeLinkNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int n = queue.size();
			for (int i = 0; i < n; ++i) {
				TreeLinkNode node = queue.poll();
				if (i < n - 1) {
					node.next = queue.peek();
				}
				if (node.left != null)
					queue.offer(node.left);
				if (node.right != null)
					queue.offer(node.right);
			}
		}
	}

	//空间复杂度为O(1),核心
	public void connect3(TreeLinkNode root) {
		if (root == null) return;
		TreeLinkNode leftmost = root;
		while (leftmost.left != null) {
			//leftmost是每一层的第一个节点
			TreeLinkNode head = leftmost;
			//在同一层中从左向右遍历
			while (head != null) {
				//首先在该结点的左孩子和右孩子连接起来
				head.left.next = head.right;
				//再把该节点的右孩子同该节点的右测节点的左孩子连接起来
				if (head.next != null) {
					head.right.next = head.next.left;
				}
				//从左向右遍历
				head = head.next;
			}
			//当前层遍历完成后转移到下一层
			leftmost = leftmost.left;
		}
	}

}
