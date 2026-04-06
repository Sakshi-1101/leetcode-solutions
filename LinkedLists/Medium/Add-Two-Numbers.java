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
12    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
13        ListNode temp1 = l1;
14        ListNode temp2 = l2;
15
16        int carry = 0;
17
18        ListNode head = new ListNode(-1, null);
19        ListNode dummy = head;
20
21        while(temp1 != null || temp2 != null || carry != 0) {
22            int sum = 0;
23
24            if(temp1 != null) {
25                sum += temp1.val;
26                temp1 = temp1.next;
27            }
28
29            if(temp2 != null) {
30                sum += temp2.val;
31                temp2 = temp2.next;
32            }
33
34            sum += carry;
35            int digit = sum % 10;
36            carry = sum / 10;
37
38            ListNode temp = new ListNode(digit, null);
39            dummy.next = temp;
40            dummy = temp;
41        }
42
43        return head.next;
44        
45    }
46}