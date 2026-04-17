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
12    public ListNode rotateRight(ListNode head, int k) {
13        if(head == null || head.next == null || k == 0) {
14            return head;
15        }
16
17        int len = 1;
18        ListNode tail = head;
19
20        while(tail.next != null) {
21            len++;
22            tail = tail.next;
23        }
24
25        k = k % len;
26
27        int remNodes = len - k;
28
29        ListNode temp = head;
30
31        while(temp != null && remNodes > 0) {
32            remNodes--;
33
34            if(remNodes == 0) {
35                break;
36            }
37
38            temp = temp.next;
39        }
40
41        tail.next = head;
42        head = temp.next;
43        temp.next = null;
44
45
46        return head;
47        
48    }
49}