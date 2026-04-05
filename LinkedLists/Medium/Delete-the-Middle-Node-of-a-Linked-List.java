1/**
2 * Definition for singly-linked list.
3 * public class ListNode {
4 *     int val;
5 *     ListNode next;
6 *     ListNode() {}
7 *     ListNode(int val) { this.val = val; }
8 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
9 * }
10 */
11class Solution {
12    public ListNode deleteMiddle(ListNode head) {
13        if (head == null || head.next == null) {
14            return null; // single node case
15        }
16
17        ListNode slow = head;
18        ListNode fast = head;
19        ListNode prev = null;
20
21        while(fast != null && fast.next != null) {
22            prev = slow;
23            slow = slow.next;
24            fast = fast.next.next;
25        }
26
27        prev.next = slow.next;
28
29        return head;
30        
31    }
32}