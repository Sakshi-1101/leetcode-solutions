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
12    public ListNode removeNthFromEnd(ListNode head, int n) {
13        ListNode prev = head;
14        ListNode curr = head;
15
16        for(int i = 1 ; i <= n ; i ++) {
17            curr = curr.next;
18        }
19
20        if(curr == null) {
21            return head.next;
22        }
23
24        while(curr.next != null) {
25            prev = prev.next;
26            curr = curr.next;
27        }
28
29        prev.next = prev.next.next;
30
31        return head;
32        
33    }
34}