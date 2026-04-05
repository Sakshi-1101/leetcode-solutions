1/**
2 * Definition for singly-linked list.
3 * class ListNode {
4 *     int val;
5 *     ListNode next;
6 *     ListNode(int x) {
7 *         val = x;
8 *         next = null;
9 *     }
10 * }
11 */
12public class Solution {
13    public ListNode detectCycle(ListNode head) {
14        ListNode slow = head;
15        ListNode fast = head;
16
17        while(fast != null && fast.next != null) {
18            slow = slow.next;
19            fast = fast.next.next;
20
21            // If there is a loop in the LL, means cycle is present 
22            if(slow == fast) {
23               // to find the starting point of the loop, we will move the slow pointer to the head of the LL
24               slow = head;
25
26               while(slow != fast) {
27                slow = slow.next;
28                fast = fast.next;
29               }
30
31               // at the end of the above loop, both slow and fast pointers will be pointing to the starting point of the loop
32               return slow;
33            }
34        } 
35
36        // If there is no loop, then no starting point
37        return null;
38        
39    }
40}