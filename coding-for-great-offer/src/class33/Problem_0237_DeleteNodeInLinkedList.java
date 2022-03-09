package class33;

public class Problem_0237_DeleteNodeInLinkedList {

	public static class ListNode {
		int val;
		ListNode next;
	}

	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}


	public ListNode deleteNode (ListNode head, int val) {
		ListNode cur = head;
		ListNode pre = null;

		while(cur!=null){
			if(cur.val == val){
				if(pre==null)return cur.next;
				else
					pre.next = cur.next;
			}
			pre = cur;
			cur = cur.next;
		}
		return head;
	}

}
