package class47;

import java.util.*;

public class Problem_0428_SerializeAndDeserializeNaryTree {

	// 不要提交这个类
	public static class TreeNode {
		public int val;
		public List<TreeNode> children;

		public TreeNode() {
			children = new ArrayList<>();
		}

		public TreeNode(int _val) {
			val = _val;
			children = new ArrayList<>();
		}

		public TreeNode(int _val, List<TreeNode> _children) {
			val = _val;
			children = _children;
		}
	};

	// 提交下面这个类
	public static class Codec {

		public static String serialize(TreeNode root) {
			if (root == null) { // 空树！直接返回#
				return "#";
			}
			StringBuilder builder = new StringBuilder();
			serial(builder, root);
			return builder.toString();
		}

		// 当前来到head
		private static void serial(StringBuilder builder, TreeNode head) {
			builder.append(head.val).append(",");
			if (!head.children.isEmpty()) {
				builder.append("[,");
				for (TreeNode next : head.children) {
					serial(builder, next);
				}
				builder.append("],");
			}
		}

		public static TreeNode deserialize(String data) {
			if (data.equals("#")) {
				return null;
			}
			String[] splits = data.split(",");
			Queue<String> queue = new LinkedList<>();
			for (String str : splits) {
				queue.offer(str);
			}
			return deserial(queue);
		}

		private static TreeNode deserial(Queue<String> queue) {
			TreeNode cur = new TreeNode(Integer.parseInt(Objects.requireNonNull(queue.poll())));
			cur.children = new ArrayList<>();
			if (!queue.isEmpty() && queue.peek().equals("[")) {
				queue.poll();
				while (!Objects.equals(queue.peek(), "]")) {
					TreeNode child = deserial(queue);
					cur.children.add(child);
				}
				queue.poll();
			}
			return cur;
		}
	}

//	int index = -1;
//	// 先序遍历序列化
//	String Serialize(TreeNode root) {
//		if(root == null) return "#,";
//		return root.val + "," + Serialize(root.left) + Serialize(root.right);
//	}
//	// 反序列化
//	TreeNode Deserialize(String str) {
//		index++ ; // str的下标
//		TreeNode result = null;
//		String[] star = str.split(",");
//		if(!"#".equals(star[index]) && !"".equals(star[index])){
//			result = new TreeNode(Integer.parseInt(star[index]));
//			result.left = Deserialize(str);
//			result.right = Deserialize(str);
//		}
//		return result;
//	}

	public static void main(String[] args) {
		// 如果想跑以下的code，请把Codec类描述和内部所有方法改成static的
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(3);
		TreeNode d = new TreeNode(4);
		TreeNode e = new TreeNode(5);
		TreeNode f = new TreeNode(6);
		TreeNode g = new TreeNode(7);
		a.children.add(b);
		a.children.add(c);
		a.children.add(d);
		b.children.add(e);
		b.children.add(f);
		e.children.add(g);
		String content = Codec.serialize(a);
		System.out.println(content);
		TreeNode head = Codec.deserialize(content);
		System.out.println(content.equals(Codec.serialize(head)));
	}

}
