1/**
2 * Definition for singly-linked list.
3 * public class ListNode {
4 *     int val;
5 *     ListNode next;
6 *     ListNode(int x) {
7 *         val = x;
8 *         next = null;
9 *     }
10 * }
11 */
12public class Solution {
13    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
14        ListNode temp1 = headA;
15        ListNode temp2 = headB;
16
17        // loop stops if at any point (temp1 == temp2) or (temp1 = temp2 = null)
18        while(temp1 != temp2) {
19            temp1 = (temp1 == null) ? headB : temp1.next;
20            temp2 = (temp2 == null) ? headA : temp2.next;
21        }
22
23        // if no intersection temp1 will be null otherwise value in temp1 will be intersection
24        return temp1;
25    }
26}