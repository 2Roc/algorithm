package class02;

/**
 输出链表的倒数k个节点
 1。先赋给快慢指针给定链表的头节点；
 2。然后快指针先走k步
 3。然后快慢指针同时走，每次一步，直到快指针为null，
 4。输出慢指针，就是最后的结果（因为快指针先走了k步）
 */
public class FindKthToTail {
    static class ListNode{
        int val;
        ListNode next;
        public ListNode(int val){
            this.val = val;
        }
    }
     public ListNode findKthToTail(ListNode head,int k){
        ListNode slow = head,fast = head;
            if(head==null)return head;
            int num = 0;
            for(int m = 0;m<k&&head!=null;m++){
                num++;
                head = head.next;
            }
            if(num<k)return null;//说明链表根本没有k那么长，直接返回null
            while(fast !=null){
                fast = fast.next;
                slow = slow.next;
            }
            return slow;
     }
}
