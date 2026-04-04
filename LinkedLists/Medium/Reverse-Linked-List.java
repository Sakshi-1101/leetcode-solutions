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
12    public ListNode reverseList(ListNode head) {
13        // Base case: If the list is empty or has only one node, return the head as it is already reversed
14        if(head == null || head.next == null) {
15            return head;
16        }
17
18        // Recursive call to reverse the rest of the list starting from the second node
19        ListNode newHead = reverseList(head.next);
20
21        ListNode temp = head.next;
22        temp.next = head;
23        head.next = null; 
24
25        // return the new head
26        return newHead;
27        
28    }
29}