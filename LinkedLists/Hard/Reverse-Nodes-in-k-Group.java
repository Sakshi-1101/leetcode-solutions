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
12    public ListNode reverseKGroup(ListNode head, int k) {
13        if(head == null || head.next == null) {
14            return head;
15        }
16
17        int len = findLen(head);
18
19        ListNode dummyHead = new ListNode(-1, null);
20        dummyHead.next = head;
21
22        ListNode prev = dummyHead;
23
24        while(len >= k) {
25            ListNode curr = prev.next;
26            ListNode nex = curr.next;
27
28            for(int i = 1; i < k ; i ++) {
29                curr.next = nex.next;
30                nex.next = prev.next;
31                prev.next = nex;
32                nex = curr.next;
33            }
34
35            prev = curr;
36            len -= k;
37        }
38
39        return dummyHead.next;
40    }
41
42    private static int findLen(ListNode head)  {
43        ListNode temp = head;
44        int len = 0;
45
46        while(temp != null) {
47            len++;
48            temp = temp.next;
49        }
50
51        return len;
52    }
53}