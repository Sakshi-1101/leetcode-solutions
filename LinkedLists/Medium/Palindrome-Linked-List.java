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
12    public boolean isPalindrome(ListNode head) {
13        ListNode slow = head;
14        ListNode fast = head;
15
16        // find the mid of the list
17        while(fast.next != null && fast.next.next != null) {
18            slow = slow.next;
19            fast = fast.next.next;
20        }
21
22        ListNode mid = slow;
23
24        ListNode newHead = reverseList(mid.next);
25
26        ListNode temp1 = head;
27        ListNode temp2 = newHead;
28
29        while(temp2 != null) {
30            if(temp1.val != temp2.val) {
31                return false;
32            }
33
34            temp1 = temp1.next;
35            temp2 = temp2.next;
36        }
37
38        return true;
39    }
40
41    private static ListNode reverseList(ListNode head) {
42        ListNode curr = head;
43        ListNode prev = null;
44
45        while(curr != null) {
46            ListNode temp = curr.next;
47            curr.next = prev;
48            prev = curr;
49            curr = temp;
50        }
51
52        return prev;
53    }
54}