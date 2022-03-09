package class03;

import util.GenerateRandomList;
import util.GenerateRandomList.Node;

import java.util.List;

public class Code02_DeleteGivenValue {

	// head = removeValue(head, 2);
	public static Node removeValue(Node head, int num) {
		// head来到第一个不需要删的位置
		while (head != null) {
			if (head.value != num) {
				break;
			}
			head = head.next;
		}
		// 1 ) head == null
		// 2 ) head != null
		Node pre = head;
		Node cur = head;
		while (cur != null) {
			if (cur.value == num) {
				pre.next = cur.next;
			} else {
				pre = cur;
			}
			cur = cur.next;
		}
		return head;
	}
	public static void main(String[] args) {
		int len = 50;
		int value = 100;
		int testTime = 10000;
		System.out.println("test begin!");
		for (int i = 0; i < testTime; i++) {
			Node node1 = GenerateRandomList.generateRandomLinkedList(len, value);
			removeValue(node1,13);
			List<Integer> order = GenerateRandomList.getLinkedListOriginOrder(node1);
			System.out.println(order.stream().iterator().next());
		}
	}
}
