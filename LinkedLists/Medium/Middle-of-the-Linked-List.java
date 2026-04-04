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
12    public ListNode middleNode(ListNode head) {
13        if(head == null || head.next == null){
14            return head;
15        }
16        
17        ListNode temp = head;
18        int count = 0;
19        
20        while(temp != null){
21            count++;
22            temp = temp.next;
23        }
24        
25        ListNode slow = head;
26        ListNode fast = head;
27        
28        while(fast.next != null && fast.next.next != null){
29            fast = fast.next.next;
30            slow = slow.next;
31        }
32        
33        if(count % 2 == 0){
34            return slow.next;
35        }
36        else{
37            return slow;
38        }
39        
40    }
41}