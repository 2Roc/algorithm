package class04;

// 测试链接：https://leetcode.com/problems/merge-two-sorted-lists
public class Code06_MergeTwoSortedLinkedList {

	// 不要提交这个类
	public static class ListNode {
		public int val;
		public ListNode next;
	}

	public static ListNode mergeTwoLists(ListNode head1, ListNode head2) {
		if (head1 == null || head2 == null) {
			return head1 == null ? head2 : head1;
		}
		ListNode head = head1.val <= head2.val ? head1 : head2;
		ListNode cur1 = head.next;
		ListNode cur2 = head == head1 ? head2 : head1;
		ListNode pre = head;
		while (cur1 != null && cur2 != null) {
			if (cur1.val <= cur2.val) {
				pre.next = cur1;
				cur1 = cur1.next;
			} else {
				pre.next = cur2;
				cur2 = cur2.next;
			}
			pre = pre.next;
		}
		pre.next = cur1 != null ? cur1 : cur2;
		return head;
	}


	//合并两个有序链表
	public static ListNode mergeList(ListNode head1,ListNode head2){
		if(head1 == null || head2 == null){
			return head1==null?head2:head1;
		}
		//存储首节点较小的链表
		ListNode list = head1.val< head2.val?head1:head2;
		//存储上面链表的头节点后面的节点；
		ListNode cur1 = list.next;
		//存储首节点较大的链表
		ListNode cur2 = list == head1?head2:head1;
		//备份list节点，以方便后续的更改
		ListNode pre = list;
		while (cur1!=null&&cur2!=null){
			//直接继续链接上头节点较小的链表
			if(cur1.val<=cur2.val){
				pre.next = cur1;
				//指针后移一位
				cur1 = cur1.next;
			}else {
				pre.next = cur2;
				cur2 = cur2.next;
			}
			//每次连接完，pre指针后移一位
			pre = pre.next;
		}
		//不要忘了，最后连上剩下的链表
		pre.next = cur1==null?cur2:cur1;
		return list;
	}
}
